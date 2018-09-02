package com.jyh.scm.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jyh.scm.base.CacheManager;
import com.jyh.scm.base.SessionManager;
import com.jyh.scm.dao.UserMapper;
import com.jyh.scm.entity.OptLog;
import com.jyh.scm.entity.User;
import com.jyh.scm.util.IDGenUtil;
import com.jyh.scm.util.TimeUtil;

/**
 * 系统API
 * 
 * @author jiangyonghua
 * @date 2017年12月26日 下午12:01:23
 */
@RestController
@RequestMapping(path = "sys")
public class SysRest {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> userMap) {
        // 验证用户
        User param = new User();
        param.setAccount(userMap.get("account"));
        User user = userMapper.selectOne(param);
        if (user == null || !userMap.get("pwd").equals(user.getPwd())) {
            return new ResponseEntity<Map<String, Object>>(HttpStatus.NO_CONTENT);
        }

        Map<String, String> userinfo = new HashMap<String, String>();
        userinfo.put(SessionManager.USER_ID_KEY, user.getId());
        userinfo.put(SessionManager.USER_NAME_KEY, user.getName());
        userinfo.put(SessionManager.USER_ACCOUNT_KEY, user.getAccount());

        String sessionid = IDGenUtil.UUID();
        SessionManager.put(sessionid, userinfo);

        Map<String, Object> rtnmsg = new HashMap<String, Object>();
        boolean needCacheAction = false;
        if (CacheManager.getActionMap().size() == 0) {
            needCacheAction = true;
        }
        rtnmsg.put("sessionid", sessionid);
        rtnmsg.put("needCacheAction", needCacheAction);
        // 添加登录日志
        OptLog log = new OptLog();
        log.setUserName(user.getName());
        log.setCreatedBy(user.getAccount());
        log.setOptType("登录");
        log.setCreatedTime(TimeUtil.getTime());
        CacheManager.logCacheList.add(log);
        return new ResponseEntity<Map<String, Object>>(rtnmsg, HttpStatus.OK);
    }

    @PostMapping("/action")
    public ResponseEntity<Object> cacheAction(@RequestBody Map<String, String> actions) {
        CacheManager.cacheAction(actions);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<Object> logout() {
        // 添加注销日志
        OptLog log = new OptLog();
        log.setUserName(SessionManager.getUsername());
        log.setCreatedBy(SessionManager.getAccount());
        log.setOptType("注销");
        log.setCreatedTime(TimeUtil.getTime());
        CacheManager.logCacheList.add(log);
        SessionManager.invaildSession();
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
