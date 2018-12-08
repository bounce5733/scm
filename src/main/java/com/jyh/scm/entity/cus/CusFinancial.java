package com.jyh.scm.entity.cus;

import com.jyh.scm.entity.BaseEntity;

/**
 * 客户财务
 * 
 * @author jiangyonghua
 * @date 2018年11月26日 上午5:23:33
 */
public class CusFinancial extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer cusid;

    private String invoiceTitle;// 发票抬头

    private String nsrsbh;// 纳税人识别号

    private String address;

    private String tel;

    private String bankAccountName;// 开户名称

    private String bankName;// 开户银行

    private String bankAccount;// 银行账号

    private String accountPeriod;// 账期类型

    private String reconcileDate;// 上次对账日期

    private String initReconcile = "F"; // 对账初始化

    public Integer getCusid() {
        return cusid;
    }

    public void setCusid(Integer cusid) {
        this.cusid = cusid;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getNsrsbh() {
        return nsrsbh;
    }

    public void setNsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getAccountPeriod() {
        return accountPeriod;
    }

    public void setAccountPeriod(String accountPeriod) {
        this.accountPeriod = accountPeriod;
    }

    public String getReconcileDate() {
        return reconcileDate;
    }

    public void setReconcileDate(String reconcileDate) {
        this.reconcileDate = reconcileDate;
    }

    public String getInitReconcile() {
        return initReconcile;
    }

    public void setInitReconcile(String initReconcile) {
        this.initReconcile = initReconcile;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
