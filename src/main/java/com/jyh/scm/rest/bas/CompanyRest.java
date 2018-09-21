package com.jyh.scm.rest.bas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jyh.scm.base.CacheManager;
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
@RequestMapping(path = "bas/companys")
public class CompanyRest {

    @Autowired
    private CompanyMapper companyMapper;

    @GetMapping
    public ResponseEntity<Company> getCompany() {
        return new ResponseEntity<Company>(companyMapper.selectByPrimaryKey(SessionManager.getAppid()), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<Object> editCompany(@RequestBody Company company) {
        company.setUpdatedBy(SessionManager.getAccount());
        company.setUpdatedTime(TimeUtil.getTime());
        if (CacheManager.getBase64Img() != null) {
            company.setAvatar(CacheManager.getBase64Img());
        }
        companyMapper.updateByPrimaryKeySelective(company);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
