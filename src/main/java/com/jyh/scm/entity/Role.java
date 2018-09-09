package com.jyh.scm.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色实体
 * 
 * @author jiangyonghua
 * @date 2017年3月27日 下午3:29:45
 */
@Table(name = "sys_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer appid;// 账套ID

    private String name;// 角色名称

    private String descn;// 角色信息描述

    private String enabled;// 是否启用 T|F

    private String createdBy;// 创建人

    private String createdTime;// 创建时间

    private String updatedBy;// 更新人

    private String updatedTime;// 更新时间

    public String getCreatedBy() {
        return createdBy;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public String getDescn() {
        return descn;
    }

    public String getEnabled() {
        return enabled;
    }

    public Integer getId() {
        return id;
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

    public String getUpdatedBy() {
        return updatedBy;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public void setDescn(String descn) {
        this.descn = descn;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
