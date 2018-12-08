package com.jyh.scm.rest.cus;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.jyh.scm.entity.cus.CusAccount;
import com.jyh.scm.entity.cus.CusCompany;
import com.jyh.scm.entity.cus.CusFinancial;
import com.jyh.scm.entity.cus.CusPersonal;
import com.jyh.scm.service.cus.CustomerService;

/**
 * 客户API
 * 
 * @author jiangyonghua
 * @date 2018年12月8日 上午3:13:29
 */
@RestController
@RequestMapping(path = "cus/customers")
public class CustomerRest {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Object> addCustomer(@RequestBody Map<String, Object> cusInfo) {
        // 公司信息
        CusCompany company = (CusCompany) JSON.parseObject(JSON.toJSONString(cusInfo.get("company")), CusCompany.class);
        // 个人信息
        CusPersonal personal = (CusPersonal) JSON.parseObject(JSON.toJSONString(cusInfo.get("personal")),
                CusPersonal.class);
        // 账号信息
        CusAccount account = (CusAccount) JSON.parseObject(JSON.toJSONString(cusInfo.get("account")), CusAccount.class);
        // 财务信息
        CusFinancial financial = (CusFinancial) JSON.parseObject(JSON.toJSONString(cusInfo.get("financial")),
                CusFinancial.class);

        customerService.addCustomer(company, personal, account, financial);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
