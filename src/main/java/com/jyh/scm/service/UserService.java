package com.jyh.scm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.jyh.scm.base.SessionManager;
import com.jyh.scm.dao.RoleMapper;
import com.jyh.scm.dao.UserMapper;
import com.jyh.scm.entity.User;

import tk.mybatis.mapper.entity.Condition;

/**
 * 用户服务
 * 
 * @author jiangyonghua
 * @date 2018年1月7日 下午10:42:36
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    public List<User> load() {
        Condition c = new Condition(User.class);
        c.createCriteria().andEqualTo("appid", SessionManager.getAppid());
        return userMapper.selectByCondition(c);
    }

    public List<User> query(String param) {
        Condition c = new Condition(User.class);
        c.createCriteria().andCondition("account LIKE '%" + param + "%' OR name LIKE '%" + param + "%'")
                .andEqualTo("appid", SessionManager.getAppid());
        return userMapper.selectByCondition(c);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void remove(int id) {
        roleMapper.removeRoleUserByUserId(id);
        userMapper.deleteByPrimaryKey(id);
    }

    public List<User> roleUsers(int roleid) {
        return userMapper.roleUsers(roleid);
    }
}
