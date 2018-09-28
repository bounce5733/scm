package com.jyh.scm.rest.code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jyh.scm.base.AppConst;
import com.jyh.scm.base.SessionManager;
import com.jyh.scm.dao.code.WarehouseMapper;
import com.jyh.scm.entity.code.Warehouse;
import com.jyh.scm.service.code.WarehouseService;
import com.jyh.scm.util.TimeUtil;

import tk.mybatis.mapper.entity.Condition;

/**
 * @author jiangyonghua
 * @date 2018年9月17日 下午7:56:53
 */
@RestController
@RequestMapping(path = "code/warehouses")
public class WarehouseRest {

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping
    public ResponseEntity<List<Warehouse>> loadWarehouse() {
        Condition c = new Condition(Warehouse.class);
        c.createCriteria().andEqualTo(AppConst.APPID_KEY, SessionManager.getAppid());
        return new ResponseEntity<List<Warehouse>>(warehouseMapper.selectByCondition(c), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<Object> editWarehouse(@RequestBody Warehouse warehouse) {
        return new ResponseEntity<Object>(warehouseMapper.updateByPrimaryKeySelective(warehouse), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addWarehouse(@RequestBody Warehouse warehouse) {
        warehouse.setCreatedBy(SessionManager.getAccount());
        warehouse.setCreatedTime(TimeUtil.getTime());
        warehouse.setAppid(SessionManager.getAppid());
        return new ResponseEntity<Object>(warehouseMapper.insertSelective(warehouse), HttpStatus.OK);
    }

    @GetMapping("/setDefault/{id}")
    public ResponseEntity<Object> setDefaultWarehouse(@PathVariable("id") Integer id) {
        warehouseService.setDefault(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    /**
     * 启停仓库
     * 
     * @param id
     * @param status
     *            T开启|F停止
     * @return
     */
    @GetMapping("/enable/{id}")
    public ResponseEntity<Object> enableWarehouse(@PathVariable("id") Integer id,
            @RequestParam("status") String status) {
        Warehouse warehouse = new Warehouse();
        warehouse.setId(id);
        warehouse.setAppid(SessionManager.getAppid());
        warehouse.setEnabled(status);
        warehouse.setUpdatedBy(SessionManager.getAccount());
        warehouse.setUpdatedTime(TimeUtil.getTime());
        warehouseMapper.updateByPrimaryKeySelective(warehouse);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removeWarehouse(@PathVariable("id") Integer id) {
        return new ResponseEntity<Object>(warehouseMapper.deleteByPrimaryKey(id), HttpStatus.OK);
    }
}
