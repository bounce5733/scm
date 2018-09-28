package com.jyh.scm.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Transient;

/**
 * 级联字典基础类
 * 
 * @author jiangyonghua
 * @date 2018年9月24日 下午9:26:35
 */
public abstract class BaseCascaderCode<T extends BaseCascaderCode<?>> extends BaseCode<T> {

    private static final long serialVersionUID = 1L;

    private Integer pid;

    /**
     * 节点ID路径集合
     */
    @Transient
    private List<Integer> path = new LinkedList<Integer>();

    @Transient
    private List<T> children;

    /**
     * 组装主键全路径
     * 
     * @param parentPath
     *            父主键路径
     */
    public void makePath(List<Integer> parentPath) {
        this.path.addAll(parentPath);
        this.path.add(this.getId());
        if (this.children != null && this.children.size() > 0) {
            for (T t : this.children) {
                t.makePath(this.path);
            }
        }
    }

    public List<Integer> getPath() {
        return path;
    }

    public void setPath(List<Integer> path) {
        this.path = path;
    }

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

}
