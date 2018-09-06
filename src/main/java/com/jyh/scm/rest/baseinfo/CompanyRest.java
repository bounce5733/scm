package com.jyh.scm.rest.baseinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jyh.scm.base.SessionManager;
import com.jyh.scm.dao.bas.CompanyMapper;
import com.jyh.scm.entity.bas.Company;
import com.jyh.scm.util.TimeUtil;

/**
 * 公司信息
 * 
 * @author jiangyonghua
 * @date 2018年9月6日 下午10:39:11
 */
@RestController
@RequestMapping(path = "companys")
public class CompanyRest {

    @Autowired
    private CompanyMapper companyMapper;

    @PatchMapping
    public ResponseEntity<Object> editUser(@RequestBody Company company) {
        company.setUpdatedBy(SessionManager.getAccount());
        company.setUpdatedTime(TimeUtil.getTime());
        companyMapper.updateByPrimaryKeySelective(company);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
