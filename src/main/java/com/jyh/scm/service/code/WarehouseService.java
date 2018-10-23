package com.jyh.scm.service.code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.jyh.scm.base.SessionManager;
import com.jyh.scm.constant.AppConst;
import com.jyh.scm.dao.code.WarehouseMapper;
import com.jyh.scm.entity.code.Unit;
import com.jyh.scm.entity.code.Warehouse;
import com.jyh.scm.util.TimeUtil;

import tk.mybatis.mapper.entity.Condition;

/**
 * @author jiangyonghua
 * @date 2018年3月5日 下午2:43:23
 */
@Service
public class WarehouseService {

    @Autowired
    private WarehouseMapper warehouseMapper;

    /**
     * 设置默认仓库
     * 
     * @param id
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void setDefault(Integer id) {
        Warehouse warehouse = new Warehouse();
        warehouse.setDefaulted("F");
        Condition c = new Condition(Warehouse.class);
        c.createCriteria().andEqualTo(AppConst.APPID_KEY, SessionManager.getAppid());
        warehouseMapper.updateByConditionSelective(warehouse, c);

        warehouse.setId(id);
        warehouse.setDefaulted("T");
        warehouse.setEnabled("T");
        warehouse.setUpdatedBy(SessionManager.getAccount());
        warehouse.setUpdatedTime(TimeUtil.getTime());
        warehouseMapper.updateByPrimaryKeySelective(warehouse);
    }

    /**
     * 置顶
     * 
     * @param id
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void moveTop(Integer id) {
        Warehouse obj = new Warehouse();
        obj.setId(id);
        obj.setSort(0);
        obj.setUpdatedBy(SessionManager.getAccount());
        obj.setUpdatedTime(TimeUtil.getTime());
        warehouseMapper.updateByPrimaryKeySelective(obj);

        // 其余后置+1
        Condition c = new Condition(Unit.class);
        c.createCriteria().andEqualTo(AppConst.APPID_KEY, SessionManager.getAppid()).andNotEqualTo("id", id);
        List<Warehouse> units = warehouseMapper.selectByCondition(c);
        units.forEach(item -> {
            item.setSort(item.getSort() + 1);
            warehouseMapper.updateByPrimaryKeySelective(item);
        });

    }

}
