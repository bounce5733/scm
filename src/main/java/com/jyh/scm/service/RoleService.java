package com.jyh.scm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.jyh.scm.dao.RoleMapper;
import com.jyh.scm.entity.Role;
import com.jyh.scm.util.IDGenUtil;

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
	public void assignMenus(String roleid, Map<String, List<String>> actionmap) {
		// 清除菜单授权
		roleMapper.clearAssignedMenus(roleid);
		// 分配菜单
		for (String menuid : actionmap.keySet()) {
			if (actionmap.get(menuid).size() > 0) {
				actionmap.get(menuid).forEach(actionkey -> {
					roleMapper.assignMenu(IDGenUtil.UUID(), roleid, menuid, actionkey);
				});
			} else {
				roleMapper.assignMenu(IDGenUtil.UUID(), roleid, menuid, "");
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
	public void assignUsers(String roleid, List<String> users) {
		// 清空授权用户
		roleMapper.clearAssignedUsers(roleid);
		// 授权
		users.forEach(userid -> {
			roleMapper.assignUser(IDGenUtil.UUID(), roleid, userid);
		});
	}

	public List<Role> load() {
		return roleMapper.selectAll();
	}

	public void remove(String roleid) {
		// 清空授权菜单
		roleMapper.clearAssignedMenus(roleid);
		// 清空授权用户
		roleMapper.clearAssignedUsers(roleid);
		// 删除权限
		roleMapper.deleteByPrimaryKey(roleid);
	}

	/**
	 * 用户菜单，去重
	 * 
	 * @param userid
	 * @return
	 */
	public Map<String, List<String>> userMenus(String userid) {
		List<Map<String, String>> points = roleMapper.userMenus(userid);
		// 去除重复
		Map<String, List<String>> menus = new HashMap<String, List<String>>();
		points.forEach(point -> {
			String menuId = point.get("menuId");
			if (menus.get(menuId) == null) {
				menus.put(menuId, new ArrayList<String>());
			}
			if (StringUtils.isNotBlank(point.get("actionKey"))) {
				menus.get(menuId).add(point.get("actionKey"));
			}
		});
		return menus;
	}
}
