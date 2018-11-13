package com.jyh.scm.service.bas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.jyh.scm.base.SessionManager;
import com.jyh.scm.constant.AppConst;
import com.jyh.scm.dao.bas.RoleMapper;
import com.jyh.scm.entity.bas.Role;
import com.jyh.scm.entity.bas.User;
import com.jyh.scm.entity.console.Code;
import com.jyh.scm.event.UserRegisterEvent;
import com.jyh.scm.util.TimeUtil;

import tk.mybatis.mapper.entity.Condition;

/**
 * 角色服务
 * 
 * @author jiangyonghua
 * @date 2018年1月8日 下午4:36:37
 */
@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 授权菜单
     * 
     * @param roleid
     * @param actionmap
     * @param menuids
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void assignMenus(int roleid, Map<String, List<String>> actionmap) {
        // 清除菜单授权
        roleMapper.clearAssignedMenus(roleid);
        // 分配菜单
        for (String menukey : actionmap.keySet()) {
            if (actionmap.get(menukey).size() > 0) {
                actionmap.get(menukey).forEach(actionkey -> {
                    roleMapper.assignMenu(roleid, menukey, actionkey);
                });
            } else {
                roleMapper.assignMenu(roleid, menukey, "");
            }

        }
    }

    /**
     * 授权用户
     * 
     * @param roleid
     * @param users
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void assignUsers(int roleid, List<Integer> users) {
        // 清空授权用户
        roleMapper.clearAssignedUsers(roleid);
        // 授权
        users.forEach(userid -> {
            roleMapper.assignUser(roleid, userid);
        });
    }

    public List<Role> load() {
        Condition c = new Condition(Code.class);
        c.createCriteria().andEqualTo(AppConst.APPID_KEY, SessionManager.getAppid());
        return roleMapper.selectByCondition(c);
    }

    /**
     * 授权用户角色
     * 
     * @param roleid
     * @param users
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void assignRoles(int userid, List<Integer> roleids) {
        // 清空授权角色
        roleMapper.clearAssignedRoles(userid);
        // 授权
        roleids.forEach(roleid -> {
            roleMapper.assignUser(roleid, userid);
        });
    }

    public void remove(int roleid) {
        // 清空授权菜单
        roleMapper.clearAssignedMenus(roleid);
        // 清空授权用户
        roleMapper.clearAssignedUsers(roleid);
        // 删除权限
        roleMapper.deleteByPrimaryKey(roleid);
    }

    /**
     * 用户菜单，功能
     * 
     * @param userid
     * @return {menu_key,[action_key]}
     */
    public Map<String, List<String>> userMenus(int userid) {
        List<Map<String, String>> points = roleMapper.userMenus(userid);
        Map<String, List<String>> menus = new HashMap<String, List<String>>();
        points.forEach(point -> {
            if (point != null) {
                String menuKey = point.get("menuKey");
                if (menus.get(menuKey) == null) {
                    menus.put(menuKey, new ArrayList<String>());
                }
                if (point.get("actionKey") != null) {
                    menus.get(menuKey).add(point.get("actionKey"));
                }
            }
        });
        return menus;
    }

    /**
     * 监听用户注册事件<br>
     * 创建默认角色并授权<br>
     * 系统默认权限{menuName,[{menuKey,actionKey}]}
     * 
     * @param userRegisterEvent
     */
    @EventListener
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void createDefaultRole(UserRegisterEvent userRegisterEvent) {
        User user = (User) userRegisterEvent.getSource();
        final Map<String, List<Map<String, String>>> superRoleMenu = new HashMap<String, List<Map<String, String>>>();
        Condition c = new Condition(Role.class);
        c.createCriteria().andEqualTo(AppConst.APPID_KEY, AppConst.SUPER_APPID);
        List<Role> roles = roleMapper.selectByCondition(c);
        roles.forEach(item -> {
            if (item.getId() != AppConst.SUPER_ADMIN_ROLEID) {
                Role role = new Role();
                role.setAppid(user.getAppid());
                role.setName(item.getName());
                role.setCreatedBy(user.getAccount());
                role.setCreatedTime(TimeUtil.getTime());
                // 复制角色
                roleMapper.insertSelective(role);
                // 获取系统默认菜单暂存
                superRoleMenu.put(role.getName(), roleMapper.roleMenus(item.getId()));
            }
        });

        // 授权菜单
        c = new Condition(Role.class);
        c.createCriteria().andEqualTo(AppConst.APPID_KEY, user.getAppid());
        roles = roleMapper.selectByCondition(c);
        roles.forEach(role -> {
            superRoleMenu.get(role.getName()).forEach(menu -> {
                roleMapper.assignMenu(role.getId(), menu.get("menuKey"), menu.get("actionKey"));
            });
            // 授权角色
            roleMapper.assignUser(role.getId(), user.getId());
        });
    }
}
