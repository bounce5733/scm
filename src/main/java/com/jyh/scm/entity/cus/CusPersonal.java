package com.jyh.scm.entity.cus;

import com.jyh.scm.entity.BaseEntity;

/**
 * 客户个人
 * 
 * @author jiangyonghua
 * @date 2018年11月26日 上午5:24:09
 */
public class CusPersonal extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer cusid;

    private String name;

    private String mobile;

    private String tel;

    private String postion;

    private String email;

    public Integer getCusid() {
        return cusid;
    }

    public void setCusid(Integer cusid) {
        this.cusid = cusid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPostion() {
        return postion;
    }

    public void setPostion(String postion) {
        this.postion = postion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    private String qq;

}
