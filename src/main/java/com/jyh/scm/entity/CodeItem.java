package com.jyh.scm.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 编码子项
 * 
 * @author jiangyonghua
 * @date 2017年6月19日 下午5:35:12
 */
@Table(name = "sys_code_item")
public class CodeItem implements Comparable<CodeItem>, Serializable {

    private static final long serialVersionUID = 1L;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer appid;// 账套ID
    private Integer pid;// 父主键
    private String name;// 名称
    private String type;// 所属类型

    private Integer sort;// 排序
    private String createdBy;// 创建人
    private String createdTime;// 创建时间
    private String updatedBy;// 更新人

    private String updatedTime;// 更新时间

    // 节点ID路径集合
    @Transient
    private List<Integer> path = new LinkedList<Integer>();

    private List<CodeItem> children;

    /**
     * 码表排序
     * 
     * @param o
     * @return
     */
    @Override
    public int compareTo(CodeItem o) {
        if (this == o) {
            return 0;
        }
        if (o == null) {
            return 1;
        }
        return this.sort > o.sort ? 1 : this.sort == o.sort ? 0 : -1;
    }

    public List<CodeItem> getChildren() {
        return children;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getCreatedTime() {
        return createdTime;
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

    public List<Integer> getPath() {
        return path;
    }

    public Integer getPid() {
        return pid;
    }

    public Integer getSort() {
        return sort;
    }

    public String getType() {
        return type;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    /**
     * 组装主键全路径
     * 
     * @param parentPath
     *            父主键路径
     */
    public void makePath(List<Integer> parentPath) {
        this.path.addAll(parentPath);
        this.path.add(this.id);
        if (this.children != null && this.children.size() > 0) {
            for (CodeItem code : this.children) {
                code.makePath(this.path);
            }
        }
    }

    public void setChildren(List<CodeItem> children) {
        this.children = children;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(List<Integer> path) {
        this.path = path;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getId() {
        return id;
    }

}
