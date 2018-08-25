package com.jyh.scm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jyh.scm.base.Mapper;
import com.jyh.scm.entity.Role;

public interface RoleMapper extends Mapper<Role> {

	void assignMenu(@Param("id") String id, @Param("roleid") String roleid, @Param("menuid") String menuid,
			@Param("actionkey") String actionkey);

	void assignUser(@Param("id") String id, @Param("roleid") String roleid, @Param("userid") String userid);

	void clearAssignedMenus(@Param("roleid") String roleid);

	void clearAssignedUsers(@Param("roleid") String roleid);

	void removeRoleMenuByMenuId(@Param("menuid") String menuid);

	void removeRoleUserByUserId(@Param("userid") String userid);

	List<Map<String,String>> roleMenus(@Param("roleid") String roleid);

	List<Map<String,String>> userMenus(@Param("userid") String userid);
}
