package com.jyh.scm.entity.code;

import javax.persistence.Table;

import com.jyh.scm.entity.BaseCode;

/**
 * 仓库
 * 
 * @author jiangyonghua
 * @date 2018年9月16日 下午8:11:19
 */
@Table(name = "code_warehouse")
public class Warehouse extends BaseCode<Unit> {

    private static final long serialVersionUID = 1L;

    private String code;

    /**
     * 是否默认仓： T是|F否
     */
    private String defaulted;

    private String address;

    /**
     * 是否开启： T是|F否
     */
    private String enabled;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDefaulted() {
        return defaulted;
    }

    public void setDefaulted(String defaulted) {
        this.defaulted = defaulted;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

}
