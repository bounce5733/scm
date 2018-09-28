package com.jyh.scm.entity.bas;

import javax.persistence.Table;

import com.jyh.scm.entity.BaseEntity;

/**
 * 公司信息
 * 
 * @author jiangyonghua
 * @date 2018年9月6日 下午10:35:22
 */
@Table(name = "bas_company")
public class Company extends BaseEntity{

    private static final long serialVersionUID = 1L;

    private String name;

    private Integer industryCategory;

    private Integer area;

    private String postcode;

    private String address;

    private String tel;

    private String fax;

    private String webSite;

    private String avatar;

    private String descn;

    private String nsrsbh;

    private String invoiceTitle;

    private String serviceTel;

    private String linkmanName;

    private String linkmanPosition;

    private String linkmanMobile;

    private String linkmanQq;

    private String linkmanEmail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndustryCategory() {
        return industryCategory;
    }

    public void setIndustryCategory(Integer industryCategory) {
        this.industryCategory = industryCategory;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public String getDescn() {
        return descn;
    }

    public void setDescn(String descn) {
        this.descn = descn;
    }

    public String getNsrsbh() {
        return nsrsbh;
    }

    public void setNsrsbh(String nsrsbh) {
        this.nsrsbh = nsrsbh;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getServiceTel() {
        return serviceTel;
    }

    public void setServiceTel(String serviceTel) {
        this.serviceTel = serviceTel;
    }

    public String getLinkmanName() {
        return linkmanName;
    }

    public void setLinkmanName(String linkmanName) {
        this.linkmanName = linkmanName;
    }

    public String getLinkmanPosition() {
        return linkmanPosition;
    }

    public void setLinkmanPosition(String linkmanPosition) {
        this.linkmanPosition = linkmanPosition;
    }

    public String getLinkmanMobile() {
        return linkmanMobile;
    }

    public void setLinkmanMobile(String linkmanMobile) {
        this.linkmanMobile = linkmanMobile;
    }

    public String getLinkmanQq() {
        return linkmanQq;
    }

    public void setLinkmanQq(String linkmanQq) {
        this.linkmanQq = linkmanQq;
    }

    public String getLinkmanEmail() {
        return linkmanEmail;
    }

    public void setLinkmanEmail(String linkmanEmail) {
        this.linkmanEmail = linkmanEmail;
    }

}
