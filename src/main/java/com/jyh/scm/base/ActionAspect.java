package com.jyh.scm.base;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.jyh.scm.entity.sys.OptLog;
import com.jyh.scm.util.TimeUtil;

/**
 * 功能日志
 * 
 * @author jiangyonghua
 * @date 2018年8月28日 下午10:19:21
 */
@Aspect
@Component
public class ActionAspect {

    @Pointcut("execution(public * com.jyh.scm.rest.*.*(..))")
    public void actionLog() {
    }

    @Before("actionLog()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        if (CacheManager.getActionMap().get(signature.getName()) != null) {
            OptLog log = new OptLog();
            log.setAppid(SessionManager.getAppid());
            log.setUserName(SessionManager.getUsername());
            log.setOptLog(JSON.toJSONString(joinPoint.getArgs()));
            log.setCreatedBy(SessionManager.getAccount());
            log.setOptType(CacheManager.getActionMap().get(signature.getName()));
            log.setCreatedTime(TimeUtil.getTime());
            CacheManager.OPT_LOG_CACHE_LIST.add(log);
        }
    }
}
