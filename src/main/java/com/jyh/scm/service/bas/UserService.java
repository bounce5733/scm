package com.jyh.scm.service.bas;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyh.scm.base.SessionManager;
import com.jyh.scm.constant.AppConst;
import com.jyh.scm.dao.bas.RoleMapper;
import com.jyh.scm.dao.bas.UserMapper;
import com.jyh.scm.entity.bas.User;
import com.jyh.scm.util.StringUtil;

import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

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

    public List<User> query(String param) {
        Condition c = new Condition(User.class);
        c.createCriteria().andCondition("account LIKE '%" + param + "%' OR name LIKE '%" + param + "%'")
                .andEqualTo(AppConst.APPID_KEY, SessionManager.getAppid());
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

    public PageInfo<User> queryByPage(String orderField, String order, int pageNum, int pageSize) {
        return PageHelper.startPage(pageNum, pageSize, true).doSelectPageInfo(new ISelect() {

            @Override
            public void doSelect() {
                Example example = new Example(User.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo(AppConst.APPID_KEY, SessionManager.getAppid());
                if (StringUtils.isNotBlank(orderField)) {
                    if (StringUtils.isNotBlank(order)) {
                        example.setOrderByClause(StringUtil.camelToUnderline(orderField) + " " + order);
                    }
                }
                userMapper.selectByCondition(example);
            }

        });
    }
}
