package com.jyh.scm.entity;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户
 * 
 * @author jiangyonghua
 * @date 2016年6月22日 上午11:19:08
 */
@Table(name = "sys_user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	private String id;

	private String account; // 账号

	private String name;// 姓名

	private String pwd;// 密码

	private String createdBy;// 创建人

	private String createdTime;// 创建时间

	private String updatedBy;// 更新人

	private String updatedTime;// 更新时间

	public String getAccount() {
		return account;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPwd() {
		return pwd;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", name=" + name + ", pwd=" + pwd + ", createdBy="
				+ createdBy + ", createdTime=" + createdTime + ", updatedBy=" + updatedBy + ", updatedTime="
				+ updatedTime + "]";
	}

}
