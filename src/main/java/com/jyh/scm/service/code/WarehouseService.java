package com.jyh.scm.service.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import com.jyh.scm.base.AppConst;
import com.jyh.scm.base.SessionManager;
import com.jyh.scm.dao.code.WarehouseMapper;
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
        warehouse.setDisabled("F");
        warehouse.setUpdatedBy(SessionManager.getAccount());
        warehouse.setUpdatedTime(TimeUtil.getTime());
        warehouseMapper.updateByPrimaryKeySelective(warehouse);
    }

}
