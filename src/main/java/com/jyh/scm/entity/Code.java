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

	private String name;

	private String createdBy;

	private String createdTime;

	@Transient
	private List<CodeItem> items = new ArrayList<CodeItem>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<CodeItem> getItems() {
		return items;
	}

	public void setItems(List<CodeItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Code [code=" + code + ", name=" + name + ", createdBy=" + createdBy + ", createdTime=" + createdTime
				+ "]";
	}

}
