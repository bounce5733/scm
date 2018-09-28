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
import com.jyh.scm.base.SessionManager;
import com.jyh.scm.dao.code.AccountPeriodMapper;
import com.jyh.scm.entity.code.AccountPeriod;
import com.jyh.scm.service.code.AccountPeriodService;
import com.jyh.scm.util.TimeUtil;

import tk.mybatis.mapper.entity.Condition;

/**
 * A
 * 
 * @author jiangyonghua
 * @date 2018年9月17日 下午7:56:53
 */
@RestController
@RequestMapping(path = "code/accountperiods")
public class AccountPeriodRest {

    @Autowired
    private AccountPeriodMapper accountPeriodMapper;

    @Autowired
    private AccountPeriodService accountPeriodService;

    @GetMapping
    public ResponseEntity<List<AccountPeriod>> loadAccountPeriod() {
        Condition c = new Condition(AccountPeriod.class);
        c.createCriteria().andEqualTo(AppConst.APPID_KEY, SessionManager.getAppid());
        List<AccountPeriod> records = accountPeriodMapper.selectByCondition(c);
        records.sort(new Comparator<AccountPeriod>() {
            @Override
            public int compare(AccountPeriod o1, AccountPeriod o2) {
                return o1.getSort() > o2.getSort() ? 1 : o1.getSort() < o2.getSort() ? -1 : 0;
            }
        });
        return new ResponseEntity<List<AccountPeriod>>(records, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<Object> editAccountPeriod(@RequestBody AccountPeriod accountPeriod) {
        return new ResponseEntity<Object>(accountPeriodMapper.updateByPrimaryKeySelective(accountPeriod),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addAccountPeriod(@RequestBody AccountPeriod accountPeriod) {
        accountPeriod.setCreatedBy(SessionManager.getAccount());
        accountPeriod.setCreatedTime(TimeUtil.getTime());
        accountPeriod.setAppid(SessionManager.getAppid());
        return new ResponseEntity<Object>(accountPeriodMapper.insertSelective(accountPeriod), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removeAccountPeriod(@PathVariable("id") Integer id) {
        return new ResponseEntity<Object>(accountPeriodMapper.deleteByPrimaryKey(id), HttpStatus.OK);
    }

    @GetMapping("/moveTop/{id}")
    public ResponseEntity<Object> moveTopAccountPeriod(@PathVariable("id") Integer id) {
        accountPeriodService.moveTop(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
