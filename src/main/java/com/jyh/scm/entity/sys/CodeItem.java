package com.jyh.scm.entity.sys;

import javax.persistence.Table;

import com.jyh.scm.entity.BaseCascaderCode;

/**
 * 编码子项
 * 
 * @author jiangyonghua
 * @date 2017年6月19日 下午5:35:12
 */
@Table(name = "sys_code_item")
public class CodeItem extends BaseCascaderCode<CodeItem> {

    private static final long serialVersionUID = 1L;

    /**
     * 所属编码
     */
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
