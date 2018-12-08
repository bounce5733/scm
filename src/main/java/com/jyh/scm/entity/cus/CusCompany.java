package com.jyh.scm.entity.cus;

import javax.persistence.Table;

import com.jyh.scm.entity.BaseEntity;

/**
 * 客户公司
 * 
 * @author jiangyonghua
 * @date 2018年11月26日 上午5:24:00
 */
@Table(name = "cus_company")
public class CusCompany extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private String grade; // 客户级别

    private String code;

    private String defaultWarehouse; // 默认仓库

    private String ownerDept; // 所在部门

    private String salesman; // 业务员

    private String signingStartdate; // 签约起始时间

    private String signingEnddate; // 签约截止时间

    private String postcode;

    private String fax;

    private String areacode;

    private String address;

    private String logisticsCode; // 物流编码

    private String descn;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDefaultWarehouse() {
        return defaultWarehouse;
    }

    public void setDefaultWarehouse(String defaultWarehouse) {
        this.defaultWarehouse = defaultWarehouse;
    }

    public String getOwnerDept() {
        return ownerDept;
    }

    public void setOwnerDept(String ownerDept) {
        this.ownerDept = ownerDept;
    }

    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

    public String getSigningStartdate() {
        return signingStartdate;
    }

    public void setSigningStartdate(String signingStartdate) {
        this.signingStartdate = signingStartdate;
    }

    public String getSigningEnddate() {
        return signingEnddate;
    }

    public void setSigningEnddate(String signingEnddate) {
        this.signingEnddate = signingEnddate;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getDescn() {
        return descn;
    }

    public void setDescn(String descn) {
        this.descn = descn;
    }

}
