package com.jyh.scm.service.sys;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.jyh.scm.base.AppConst;
import com.jyh.scm.base.SessionManager;
import com.jyh.scm.dao.sys.CodeItemMapper;
import com.jyh.scm.dao.sys.CodeMapper;
import com.jyh.scm.entity.code.ProductCatalog;
import com.jyh.scm.entity.sys.Code;
import com.jyh.scm.entity.sys.CodeItem;
import com.jyh.scm.util.TimeUtil;

import tk.mybatis.mapper.entity.Condition;

/**
 * @author jiangyonghua
 * @date 2018年3月5日 下午2:43:23
 */
@Service
public class CodeService {

    @Autowired
    private CodeMapper codeMapper;

    @Autowired
    private CodeItemMapper codeItemMapper;

    public List<Code> loadCode() {
        Condition c = new Condition(Code.class);
        c.createCriteria().andEqualTo(AppConst.APPID_KEY, SessionManager.getAppid());
        List<Code> codes = codeMapper.selectByCondition(c);
        for (Code code : codes) {
            List<CodeItem> itemList = new LinkedList<CodeItem>();
            CodeItem param = new CodeItem();
            param.setType(code.getCode());
            List<CodeItem> items = codeItemMapper.select(param);
            List<CodeItem> topItems = items.stream().filter(item -> 0 == item.getPid()).sorted()
                    .collect(Collectors.toList());

            makeCodeItems(itemList, topItems, items);

            code.setItems(itemList);
        }
        return codes;
    }

    /**
     * 置顶
     * 
     * @param id
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void moveTopCodeItem(Integer id, Integer pid) {
        CodeItem obj = new CodeItem();
        obj.setId(id);
        obj.setSort(0);
        obj.setUpdatedBy(SessionManager.getAccount());
        obj.setUpdatedTime(TimeUtil.getTime());
        codeItemMapper.updateByPrimaryKeySelective(obj);

        // 当前层级其余后置+1
        Condition c = new Condition(ProductCatalog.class);
        c.createCriteria().andEqualTo(AppConst.APPID_KEY, SessionManager.getAppid()).andEqualTo("pid", pid)
                .andNotEqualTo("id", id);
        List<CodeItem> codeitems = codeItemMapper.selectByCondition(c);
        codeitems.forEach(item -> {
            item.setSort(item.getSort() + 1);
            codeItemMapper.updateByPrimaryKeySelective(item);
        });
    }

    /**
     * 递归收集编码项目
     * 
     * @param pItems
     *            编码父项目列表
     * @param codeTtems
     *            编码项目列表
     */
    private static void makeCodeItems(List<CodeItem> items, List<CodeItem> pItems, List<CodeItem> codeTtems) {
        for (CodeItem pItem : pItems) {
            items.add(pItem);
            // 收集下级子项目
            List<CodeItem> child = codeTtems.stream().filter(item -> item.getPid() == pItem.getId()).sorted()
                    .collect(Collectors.toList());
            if (child.size() > 0) {
                makeCodeItems(items, child, codeTtems);
            }
        }
    }

}
