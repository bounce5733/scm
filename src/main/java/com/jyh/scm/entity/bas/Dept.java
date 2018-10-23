package com.jyh.scm.entity.bas;

import javax.persistence.Table;

import com.jyh.scm.entity.BaseCascaderCode;

/**
 * 公司部门
 * 
 * @author jiangyonghua
 * @date 2018年9月22日 下午11:50:34
 */
@Table(name = "bas_dept")
public class Dept extends BaseCascaderCode<Dept> {

    private static final long serialVersionUID = 1L;

    /**
     * 是否默认： T是|F否
     */
    private String defaulted;

    public String getDefaulted() {
        return defaulted;
    }

    public void setDefaulted(String defaulted) {
        this.defaulted = defaulted;
    }

}
