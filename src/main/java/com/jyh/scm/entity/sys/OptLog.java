package com.jyh.scm.entity.sys;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sys_opt_log")
public class OptLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    private Integer appid;

    private String userName;

    private String optType;

    private String optLog;

    private String createdBy;

    private String createdTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppid() {
        return appid;
    }

    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public String getOptLog() {
        return optLog;
    }

    public void setOptLog(String optLog) {
        this.optLog = optLog;
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

}
