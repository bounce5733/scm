package com.jyh.scm.service.code;

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
import com.jyh.scm.dao.code.ProductCatalogMapper;
import com.jyh.scm.entity.bas.User;
import com.jyh.scm.entity.code.ProductCatalog;
import com.jyh.scm.event.UserRegisterEvent;
import com.jyh.scm.util.TimeUtil;

import tk.mybatis.mapper.entity.Condition;

/**
 * 商品目录
 * 
 * @author jiangyonghua
 * @date 2018年9月24日 上午10:09:51
 */
@Service
public class ProductCatalogService {

    @Autowired
    private ProductCatalogMapper productCatalogMapper;

    public List<ProductCatalog> load() {
        List<ProductCatalog> result = new LinkedList<ProductCatalog>();
        Condition c = new Condition(ProductCatalog.class);
        c.createCriteria().andEqualTo(AppConst.APPID_KEY, SessionManager.getAppid());
        List<ProductCatalog> records = productCatalogMapper.selectByCondition(c);
        List<ProductCatalog> topItems = records.stream().filter(item -> 0 == item.getPid()).sorted()
                .collect(Collectors.toList());
        makeProductCatalogs(result, topItems, records);

        return result;
    }

    /**
     * 置顶
     * 
     * @param id
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void moveTop(Integer id, Integer pid) {
        ProductCatalog obj = new ProductCatalog();
        obj.setId(id);
        obj.setSort(0);
        obj.setUpdatedBy(SessionManager.getAccount());
        obj.setUpdatedTime(TimeUtil.getTime());
        productCatalogMapper.updateByPrimaryKeySelective(obj);

        // 当前层级其余后置+1
        Condition c = new Condition(ProductCatalog.class);
        c.createCriteria().andEqualTo(AppConst.APPID_KEY, SessionManager.getAppid()).andEqualTo("pid", pid)
                .andNotEqualTo("id", id);
        List<ProductCatalog> productCatalogs = productCatalogMapper.selectByCondition(c);
        productCatalogs.forEach(item -> {
            item.setSort(item.getSort() + 1);
            productCatalogMapper.updateByPrimaryKeySelective(item);
        });
    }

    /**
     * 监听用户注册事件<br>
     * 创建默认商品分类
     * 
     * @param userRegisterEvent
     */
    @EventListener
    public void createDefaultProductCatalog(UserRegisterEvent userRegisterEvent) {
        User user = (User) userRegisterEvent.getSource();
        ProductCatalog productCatalog = new ProductCatalog();
        productCatalog.setAppid(user.getAppid());
        productCatalog.setName(AppConst.DEFAULT_PRODUCT_CATALOG_NAME);
        productCatalog.setDefaulted("T");
        productCatalog.setCreatedBy(user.getAccount());
        productCatalog.setCreatedTime(TimeUtil.getTime());
        productCatalogMapper.insertSelective(productCatalog);
    }

    /**
     * 递归收集编码项目
     * 
     * @param pItems
     *            编码父项目列表
     * @param codeTtems
     *            编码项目列表
     */
    private static void makeProductCatalogs(List<ProductCatalog> items, List<ProductCatalog> pItems,
            List<ProductCatalog> codeTtems) {
        for (ProductCatalog pItem : pItems) {
            items.add(pItem);
            // 收集下级子项目
            List<ProductCatalog> child = codeTtems.stream().filter(item -> item.getPid() == pItem.getId()).sorted()
                    .collect(Collectors.toList());
            if (child.size() > 0) {
                makeProductCatalogs(items, child, codeTtems);
            }
        }
    }
}
