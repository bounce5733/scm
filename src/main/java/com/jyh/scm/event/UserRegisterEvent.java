package com.jyh.scm.event;

import org.springframework.context.ApplicationEvent;

/**
 * 用户注册时间，用于通知创建初始化内容
 * 
 * @author jiangyonghua
 * @date 2018年11月13日 上午5:34:49
 */
public class UserRegisterEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    public UserRegisterEvent(Object source) {
        super(source);
    }

}
