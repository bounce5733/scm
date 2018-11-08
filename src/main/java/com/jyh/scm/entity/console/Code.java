package com.jyh.scm.entity.console;

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

    @Transient
    private List<CodeItem> items = new ArrayList<CodeItem>();

    public String getCode() {
        return code;
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

    public void setItems(List<CodeItem> items) {
        this.items = items;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
