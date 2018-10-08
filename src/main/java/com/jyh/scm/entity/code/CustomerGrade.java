package com.jyh.scm.entity.code;

import javax.persistence.Table;

import com.jyh.scm.entity.BaseCode;

/**
 * 客户级别
 * 
 * @author jiangyonghua
 * @date 2018年10月8日 下午12:02:01
 */
@Table(name = "code_customer_grade")
public class CustomerGrade extends BaseCode<CustomerGrade> {

    private static final long serialVersionUID = 1L;

    /**
     * 折扣
     */
    private Float discount;

    /**
     * 是否默认仓： T是|F否
     */
    private String defaulted;

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public String getDefaulted() {
        return defaulted;
    }

    public void setDefaulted(String defaulted) {
        this.defaulted = defaulted;
    }

}
