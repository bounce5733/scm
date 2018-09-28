package com.jyh.scm.base;

/**
 * 系统二维字典类型
 * 
 * @author jiangyonghua
 * @date 2018年9月28日 下午10:09:20
 */
public enum CodeTypeEnum {

    AccountPeriod("accountPeriod");

    private CodeTypeEnum(String name) {
    }

    public String getName() {
        return this.name();
    }
}
