package com.jyh.scm.entity;

public class BaseCode<T extends BaseCode<?>> extends BaseEntity implements Comparable<T> {

    private static final long serialVersionUID = 1L;

    private String name;

    private Integer sort;

    @Override
    public int compareTo(T o) {
        if (this == o) {
            return 0;
        }
        if (o == null) {
            return 1;
        }
        return this.sort > o.getSort() ? 1 : this.sort == o.getSort() ? 0 : -1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

}
