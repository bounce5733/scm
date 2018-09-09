package com.jyh.scm.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 编码子项
 * 
 * @author jiangyonghua
 * @date 2017年6月19日 下午5:35:12
 */
@Table(name = "sys_code")
public class Code {

    private static final long serialVersionUID = 1L;

    @Id
    private String code;

    private Integer appid; // 账套ID

    private String name;

    private String createdBy;

    private String createdTime;

    @Transient
    private List<CodeItem> items = new ArrayList<CodeItem>();

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Integer getAppid() {
        return appid;
    }

    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    public String getCode() {
        return code;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public List<CodeItem> getItems() {
        return items;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public void setItems(List<CodeItem> items) {
        this.items = items;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Code [code=" + code + ", name=" + name + ", createdBy=" + createdBy + ", createdTime=" + createdTime
                + "]";
    }

}
