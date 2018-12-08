package com.jyh.scm.websocket;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.jyh.scm.base.SysLogAppender;

/**
 * 系统日志WebSocket
 * 
 * @author jiangyonghua
 * @date 2018年9月3日 下午10:18:58
 */
@ServerEndpoint(value = "/syslog")
@Component
public class SysLogWebSocket {

    private static final Logger log = LoggerFactory.getLogger(SysLogWebSocket.class);

    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        SysLogAppender.openLog();
        while (this.session.isOpen()) {
            while (true) {
                if (!SysLogAppender.getQueue().isEmpty()) {
                    try {
                        this.session.getBasicRemote().sendText(SysLogAppender.getQueue().poll());
                    } catch (IOException e) {
                        log.error(e.getMessage());
                    }
                }
            }
        }
        log.info("打开SysLogWebSocket...");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        SysLogAppender.closeLog();
        log.info("关闭SysLogWebSocket...");
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("SysLogWebSocket发生异常", error);
    }

}
