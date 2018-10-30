package com.jyh.scm.dao.bas;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jyh.scm.base.Mapper;
import com.jyh.scm.entity.bas.Role;

public interface RoleMapper extends Mapper<Role> {

    void assignMenu(@Param("roleid") int roleid, @Param("menukey") String menukey,
            @Param("actionkey") String actionkey);

    void assignUser(@Param("roleid") int roleid, @Param("userid") int userid);

    void clearAssignedMenus(@Param("roleid") int roleid);

    void clearAssignedUsers(@Param("roleid") int roleid);
    
    void clearAssignedRoles(@Param("userid") int userid);

    void removeRoleUserByUserId(@Param("userid") int userid);

    List<Map<String, String>> roleMenus(@Param("roleid") int roleid);

    List<Map<String, String>> userMenus(@Param("userid") int userid);
}
