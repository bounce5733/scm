package com.jyh.scm.rest.code;

import java.util.Comparator;
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
import org.springframework.web.bind.annotation.RestController;

import com.jyh.scm.base.AppConst;
import com.jyh.scm.base.CacheManager;
import com.jyh.scm.base.SessionManager;
import com.jyh.scm.dao.code.CustomerGradeMapper;
import com.jyh.scm.entity.code.CustomerGrade;
import com.jyh.scm.service.code.CustomerGradeService;
import com.jyh.scm.util.TimeUtil;

import tk.mybatis.mapper.entity.Condition;

/**
 * 客户等级
 * 
 * @author jiangyonghua
 * @date 2018年9月17日 下午7:56:53
 */
@RestController
@RequestMapping(path = "code/customergrades")
public class CustomerGradeRest {

    @Autowired
    private CustomerGradeMapper customerGradeMapper;

    @Autowired
    private CustomerGradeService customerGradeService;

    @Autowired
    private CacheManager cacheManager;

    @GetMapping
    public ResponseEntity<List<CustomerGrade>> loadCustomerGrade() {
        Condition c = new Condition(CustomerGrade.class);
        c.createCriteria().andEqualTo(AppConst.APPID_KEY, SessionManager.getAppid());
        List<CustomerGrade> records = customerGradeMapper.selectByCondition(c);
        records.sort(new Comparator<CustomerGrade>() {
            @Override
            public int compare(CustomerGrade o1, CustomerGrade o2) {
                return o1.getSort() > o2.getSort() ? 1 : o1.getSort() < o2.getSort() ? -1 : 0;
            }
        });
        return new ResponseEntity<List<CustomerGrade>>(records, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addCustomerGrade(@RequestBody CustomerGrade customerGrade) {
        customerGrade.setCreatedBy(SessionManager.getAccount());
        customerGrade.setCreatedTime(TimeUtil.getTime());
        customerGrade.setAppid(SessionManager.getAppid());
        customerGradeMapper.insertSelective(customerGrade);
        cacheManager.refreshAppCode();
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<Object> editCustomerGrade(@RequestBody CustomerGrade customerGrade) {
        customerGrade.setUpdatedBy(SessionManager.getAccount());
        customerGrade.setUpdatedTime(TimeUtil.getTime());
        customerGradeMapper.updateByPrimaryKeySelective(customerGrade);
        cacheManager.refreshAppCode();
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removeCustomerGrade(@PathVariable("id") Integer id) {
        customerGradeMapper.deleteByPrimaryKey(id);
        cacheManager.refreshAppCode();
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @GetMapping("/moveTop/{id}")
    public ResponseEntity<Object> moveTopCustomerGrade(@PathVariable("id") Integer id) {
        customerGradeService.moveTop(id);
        cacheManager.refreshAppCode();
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
