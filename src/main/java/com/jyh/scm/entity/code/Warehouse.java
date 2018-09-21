package com.jyh.scm.entity.code;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 仓库
 * 
 * @author jiangyonghua
 * @date 2018年9月16日 下午8:11:19
 */
@Table(name = "code_warehouse")
public class Warehouse {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    private String code;

    /**
     * 是否默认仓： T是|F否
     */
    private String defaulted;

    private Integer appid;

    private String name;

    private String address;

    /**
     * 是否禁用： T是|F否
     */
    private String disabled;

    private String createdBy;// 创建人

    private String createdTime;// 创建时间

    private String updatedBy;// 更新人

    private String updatedTime;// 更新时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDefaulted() {
        return defaulted;
    }

    public void setDefaulted(String defaulted) {
        this.defaulted = defaulted;
    }

    public Integer getAppid() {
        return appid;
    }

    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
