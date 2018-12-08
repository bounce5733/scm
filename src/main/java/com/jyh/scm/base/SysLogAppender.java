package com.jyh.scm.base;

import java.util.concurrent.ConcurrentLinkedQueue;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

/**
 * 用于网页前端查看系统日志
 * 
 * @author jiangyonghua
 * @date 2018年11月15日 上午7:06:29
 */
public class SysLogAppender extends AppenderBase<ILoggingEvent> {

    private static boolean logFlag = false;

    private static ConcurrentLinkedQueue<String> logQueue = new ConcurrentLinkedQueue<String>();

    @Override
    protected void append(ILoggingEvent eventObject) {

        if (logFlag && eventObject != null && eventObject.getMessage() != null) {
            logQueue.add(eventObject.getMessage()+"\n");
        }
    }

    /**
     * 打开日志记录
     */
    public static void openLog() {
        logQueue.clear();
        logFlag = true;
    }

    /**
     * 关闭日志记录
     */
    public static void closeLog() {
        logFlag = false;
        logQueue.clear();
    }

    /**
     * @return 返回日志队列
     */
    public static ConcurrentLinkedQueue<String> getQueue() {
        return logQueue;
    }

}
