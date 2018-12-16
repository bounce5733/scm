package com.jyh.scm.service.cus;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.jyh.scm.base.SessionManager;
import com.jyh.scm.dao.cus.CusCompanyMapper;
import com.jyh.scm.dao.cus.CusFinancialMapper;
import com.jyh.scm.dao.cus.CusPersonalMapper;
import com.jyh.scm.entity.cus.CusCompany;
import com.jyh.scm.entity.cus.CusFinancial;
import com.jyh.scm.entity.cus.CusPersonal;
import com.jyh.scm.service.sys.LoginService;
import com.jyh.scm.util.TimeUtil;

/**
 * 客户服务
 * 
 * @author jiangyonghua
 * @date 2018年12月8日 上午3:18:12
 */
@Service
public class CustomerService {

    @Autowired
    private CusCompanyMapper cusCompanyMapper;

    @Autowired
    private CusPersonalMapper cusPersonalMapper;

    @Autowired
    private CusFinancialMapper cusFinancialMapper;

    @Autowired
    private LoginService loginService;

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void addCustomer(CusCompany company, CusPersonal personal, HashMap<?, ?> account, CusFinancial financial) {
        company.setAppid(SessionManager.getAppid());
        company.setCreatedBy(SessionManager.getAccount());
        company.setCreatedTime(TimeUtil.getTime());
        cusCompanyMapper.insert(company);
        company = cusCompanyMapper.selectOne(company);
        personal.setAppid(SessionManager.getAppid());
        personal.setCusid(company.getId());
        personal.setCreatedBy(SessionManager.getAccount());
        personal.setCreatedTime(TimeUtil.getTime());
        cusPersonalMapper.insert(personal);
        if (account.get("account") != null) {
            Map<String, String> registerInfo = new HashMap<String, String>();
            registerInfo.put("companyName", company.getName());
            registerInfo.put("account", String.valueOf(account.get("account")));
            registerInfo.put("password", String.valueOf(account.get("pwd")));
            loginService.register(registerInfo);
        }
        if (financial != null) {
            financial.setCusid(company.getId());
            financial.setAppid(SessionManager.getAppid());
            financial.setCreatedBy(SessionManager.getAccount());
            financial.setCreatedTime(TimeUtil.getTime());
            cusFinancialMapper.insert(financial);
        }

    }
}
