package com.jyh.scm.service.bas;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.jyh.scm.base.SessionManager;
import com.jyh.scm.constant.AppConst;
import com.jyh.scm.dao.bas.DeptMapper;
import com.jyh.scm.entity.bas.Dept;
import com.jyh.scm.entity.bas.User;
import com.jyh.scm.event.UserRegisterEvent;
import com.jyh.scm.util.TimeUtil;

import tk.mybatis.mapper.entity.Condition;

/**
 * 部门
 * 
 * @author jiangyonghua
 * @date 2018年9月24日 上午10:09:51
 */
@Service
public class DeptService {

    @Autowired
    private DeptMapper deptMapper;

    public List<Dept> load() {
        List<Dept> result = new LinkedList<Dept>();
        Condition c = new Condition(Dept.class);
        c.createCriteria().andEqualTo(AppConst.APPID_KEY, SessionManager.getAppid());
        List<Dept> records = deptMapper.selectByCondition(c);
        List<Dept> topItems = records.stream().filter(item -> 0 == item.getPid()).sorted().collect(Collectors.toList());
        makeDepts(result, topItems, records);

        return result;
    }

    /**
     * 置顶
     * 
     * @param id
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void moveTop(Integer id, Integer pid) {
        Dept obj = new Dept();
        obj.setId(id);
        obj.setSort(0);
        obj.setUpdatedBy(SessionManager.getAccount());
        obj.setUpdatedTime(TimeUtil.getTime());
        deptMapper.updateByPrimaryKeySelective(obj);

        // 当前层级其余后置+1
        Condition c = new Condition(Dept.class);
        c.createCriteria().andEqualTo(AppConst.APPID_KEY, SessionManager.getAppid()).andEqualTo("pid", pid)
                .andNotEqualTo("id", id);
        List<Dept> depts = deptMapper.selectByCondition(c);
        depts.forEach(item -> {
            item.setSort(item.getSort() + 1);
            deptMapper.updateByPrimaryKeySelective(item);
        });
    }

    /**
     * 监听用户注册事件<br>
     * 创建总部
     * 
     * @param userRegisterEvent
     */
    @EventListener
    public void createHeaderQuarter(UserRegisterEvent userRegisterEvent) {
        User user = (User) userRegisterEvent.getSource();
        Dept dept = new Dept();
        dept.setAppid(user.getAppid());
        dept.setName(AppConst.DEFAULT_DEPT_NAME);
        dept.setDefaulted("T");
        dept.setCreatedBy(user.getAccount());
        dept.setCreatedTime(TimeUtil.getTime());
        deptMapper.insertSelective(dept);
    }

    /**
     * 递归收集编码项目
     * 
     * @param pItems
     *            编码父项目列表
     * @param codeTtems
     *            编码项目列表
     */
    private static void makeDepts(List<Dept> items, List<Dept> pItems, List<Dept> codeTtems) {
        for (Dept pItem : pItems) {
            items.add(pItem);
            // 收集下级子项目
            List<Dept> child = codeTtems.stream().filter(item -> item.getPid() == pItem.getId()).sorted()
                    .collect(Collectors.toList());
            if (child.size() > 0) {
                makeDepts(items, child, codeTtems);
            }
        }
    }
}
