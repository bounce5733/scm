package com.jyh.scm.rest.bas;

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

import com.jyh.scm.base.CacheManager;
import com.jyh.scm.base.SessionManager;
import com.jyh.scm.dao.bas.DeptMapper;
import com.jyh.scm.entity.bas.Dept;
import com.jyh.scm.service.bas.DeptService;
import com.jyh.scm.util.TimeUtil;

/**
 * 部门API
 * 
 * @author jiangyonghua
 * @date 2017年12月27日 下午4:17:33
 */
@RestController
@RequestMapping(path = "bas/depts")
public class DeptRest {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private DeptService deptService;

    @Autowired
    private CacheManager cacheManager;

    @GetMapping
    public ResponseEntity<List<Dept>> loadDept() {
        return new ResponseEntity<List<Dept>>(deptService.load(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addDept(@RequestBody Dept item) {
        item.setAppid(SessionManager.getAppid());
        item.setCreatedBy(SessionManager.getAccount());
        item.setCreatedTime(TimeUtil.getTime());
        deptMapper.insertSelective(item);
        cacheManager.refreshAppCascadeCode();
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<Object> editDept(@RequestBody Dept item) {
        item.setUpdatedBy(SessionManager.getAccount());
        item.setUpdatedTime(TimeUtil.getTime());
        deptMapper.updateByPrimaryKeySelective(item);
        cacheManager.refreshAppCascadeCode();
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removeDept(@PathVariable Integer id) {
        deptMapper.deleteByPrimaryKey(id);
        cacheManager.refreshAppCascadeCode();
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @GetMapping("/moveTop/{id}")
    public ResponseEntity<Object> moveTopDept(@PathVariable("id") Integer id,
            @RequestParam("pid") Integer pid) {
        deptService.moveTop(id, pid);
        cacheManager.refreshAppCascadeCode();
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

}
