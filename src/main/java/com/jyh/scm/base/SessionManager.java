package com.jyh.scm.base;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.jyh.scm.constant.AppConst;

@Configuration
public class SessionManager {

    private static final Logger log = LoggerFactory.getLogger(SessionManager.class);

    public static final String SESSION_KEY = "X-Auth-Token";

    public static final String USERID_KEY = "userid";

    public static final String USER_NAME_KEY = "username";

    public static final String USER_ACCOUNT_KEY = "account";

    private static final ThreadLocal<String> SESSION_THREAD_LOCAL = new ThreadLocal<String>();

    private static LoadingCache<String, Map<String, String>> session;

    public static String getAccount() {
        if (userinfo() != null && !userinfo().isEmpty()) {
            return userinfo().get(USER_ACCOUNT_KEY);
        } else {
            return "";
        }
    }

    public static String getSessionid() {
        return SESSION_THREAD_LOCAL.get();
    }

    public static int getUserid() {
        if (userinfo() != null && !userinfo().isEmpty()) {
            return Integer.valueOf(userinfo().get(USERID_KEY));
        } else {
            return -1;
        }
    }

    public static int getAppid() {
        if (userinfo() != null && !userinfo().isEmpty()) {
            return Integer.valueOf(userinfo().get(AppConst.APPID_KEY));
        } else {
            return -1;
        }
    }

    public static String getUsername() {
        if (userinfo() != null && !userinfo().isEmpty()) {
            return userinfo().get(USER_NAME_KEY);
        } else {
            return "";
        }
    }

    public static boolean isValid(String sessionid) {
        try {
            return session.get(sessionid).isEmpty() ? false : true;
        } catch (ExecutionException e) {
            log.info(e.getMessage());
        }
        return false;
    }

    public static void put(String sessionid, Map<String, String> userinfo) {
        session.put(sessionid, userinfo);
    }

    public static void setSessionid(String sessionid) {
        SESSION_THREAD_LOCAL.set(sessionid);
    }

    public static void invaildSession() {
        session.invalidate(SESSION_THREAD_LOCAL.get());
    }

    public static Map<String, String> userinfo() {
        try {
            return session.get(SESSION_THREAD_LOCAL.get());
        } catch (ExecutionException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Value("${custom.session.expiredTime}")
    private int expiredTime;

    @PostConstruct
    public void init() {
        session = CacheBuilder.newBuilder().expireAfterAccess(expiredTime, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Map<String, String>>() {
                    @Override
                    public Map<String, String> load(String key) throws Exception {
                        return new HashMap<String, String>();
                    }
                });
    }

}
