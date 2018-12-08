package com.jyh.scm.entity.cus;

import com.jyh.scm.entity.BaseEntity;

/**
 * 客户账号
 * 
 * @author jiangyonghua
 * @date 2018年11月26日 上午5:23:47
 */
public class CusAccount extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer cusid;

    private String account;

    private String pwd;

    private String sendEmail;

    private String sendMobile;

    public Integer getCusid() {
        return cusid;
    }

    public void setCusid(Integer cusid) {
        this.cusid = cusid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(String sendEmail) {
        this.sendEmail = sendEmail;
    }

    public String getSendMobile() {
        return sendMobile;
    }

    public void setSendMobile(String sendMobile) {
        this.sendMobile = sendMobile;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
