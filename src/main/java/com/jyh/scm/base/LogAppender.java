package com.jyh.scm.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jyh.scm.websocket.SysLogWebSocket;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

/**
 * 自定义日志，用于前端输出
 * 
 * @author jiangyonghua
 * @date 2018年11月9日 下午11:44:26
 */
@Service
public class LogAppender extends AppenderBase<ILoggingEvent> {

    @Autowired
    private SysLogWebSocket socket;

    @Override
    protected void append(ILoggingEvent eventObject) {
        if (socket.isOpen()) {
            if (eventObject != null && eventObject.getMessage() != null) {
                socket.sendMessage(eventObject.getMessage());
            }
        }

    }

}
