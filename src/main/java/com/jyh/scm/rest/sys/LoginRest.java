package com.jyh.scm.rest.sys;

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
import com.jyh.scm.constant.AppConst;
import com.jyh.scm.dao.bas.UserMapper;
import com.jyh.scm.entity.bas.User;
import com.jyh.scm.entity.sys.OptLog;
import com.jyh.scm.service.sys.LoginService;
import com.jyh.scm.util.IDGenUtil;
import com.jyh.scm.util.TimeUtil;

/**
 * 系统API
 * 
 * @author jiangyonghua
 * @date 2017年12月26日 下午12:01:23
 */
@RestController
@RequestMapping(path = "sys/login")
public class LoginRest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoginService sysService;

    @Autowired
    private CacheManager cacheManager;

    @PostMapping
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> userMap) {
        // 验证用户
        User param = new User();
        param.setAccount(userMap.get("account"));
        User user = userMapper.selectOne(param);
        if (user == null || !userMap.get("pwd").equals(user.getPwd())) {
            return new ResponseEntity<Map<String, Object>>(HttpStatus.NO_CONTENT);
        }

        Map<String, String> userinfo = new HashMap<String, String>();
        userinfo.put(SessionManager.USERID_KEY, String.valueOf(user.getId()));
        userinfo.put(AppConst.APPID_KEY, String.valueOf(user.getAppid()));
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
        log.setAppid(user.getAppid());
        log.setUserName(user.getName());
        log.setAppid(user.getAppid());
        log.setCreatedBy(user.getAccount());
        log.setOptType("登录");
        log.setCreatedTime(TimeUtil.getTime());
        CacheManager.LOG_CACHE_LIST.add(log);
        return new ResponseEntity<Map<String, Object>>(rtnmsg, HttpStatus.OK);
    }

    /**
     * 注册公司
     * 
     * @param registerInfo
     * @return 204 公司名称已被注册 | 226 账号已经存在
     */
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody Map<String, String> registerInfo) {
        int flag = sysService.register(registerInfo);
        if (flag == 1) {
            return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
        } else if (flag == 2) {
            return new ResponseEntity<Object>(HttpStatus.IM_USED);
        } else {
            // 刷新缓存
            cacheManager.refreshAppCascadeCode();
            cacheManager.refreshAppCode();
            return new ResponseEntity<Object>(HttpStatus.OK);
        }
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
        log.setAppid(SessionManager.getAppid());
        log.setUserName(SessionManager.getUsername());
        log.setCreatedBy(SessionManager.getAccount());
        log.setOptType("注销");
        log.setCreatedTime(TimeUtil.getTime());
        CacheManager.LOG_CACHE_LIST.add(log);
        SessionManager.invaildSession();
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
