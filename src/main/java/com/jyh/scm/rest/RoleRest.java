package com.jyh.scm.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jyh.scm.base.SessionManager;
import com.jyh.scm.dao.RoleMapper;
import com.jyh.scm.entity.Role;
import com.jyh.scm.entity.User;
import com.jyh.scm.service.RoleService;
import com.jyh.scm.util.TimeUtil;

import tk.mybatis.mapper.entity.Condition;

/**
 * 角色API
 * 
 * @author jiangyonghua
 * @date 2018年1月7日 下午10:43:57
 */
@RestController
@RequestMapping(path = "roles")
public class RoleRest {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleService service;

    @PostMapping
    public ResponseEntity<Object> addRole(@RequestBody Role role) {
        role.setAppid(SessionManager.getAppid());
        role.setCreatedBy(SessionManager.getAccount());
        role.setCreatedTime(TimeUtil.getTime());
        roleMapper.insertSelective(role);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    /**
     * 分配菜单给角色
     * 
     * @param roleid
     * @param menuids
     * @return
     */
    @PostMapping("/{roleid}/assign/menus")
    public ResponseEntity<Object> assignMenus(@PathVariable("roleid") int roleid,
            @RequestBody Map<String, List<String>> actionmap) {

        service.assignMenus(roleid, actionmap);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    /**
     * 分配用户给角色
     * 
     * @param roleid
     * @param userids
     * @return
     */
    @PostMapping("/{roleid}/assign/users")
    public ResponseEntity<Object> assignUsers(@PathVariable("roleid") int roleid, @RequestBody List<Integer> userids) {
        service.assignUsers(roleid, userids);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<Object> editRole(@RequestBody Role role) {
        role.setUpdatedBy(SessionManager.getAccount());
        role.setUpdatedTime(TimeUtil.getTime());

        roleMapper.updateByPrimaryKeySelective(role);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Role>> loadRole() {
        Condition c = new Condition(User.class);
        c.createCriteria().andEqualTo("appid", SessionManager.getAppid());
        return new ResponseEntity<List<Role>>(roleMapper.selectByCondition(c), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removeRole(@PathVariable("id") int id) {
        try {
            service.remove(id);
            return new ResponseEntity<Object>(HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<Object>(HttpStatus.LOCKED);
        }
    }

    /**
     * 角色菜单
     * 
     * @param roleid
     * @return
     */
    @GetMapping("/menus/{roleid}")
    public ResponseEntity<List<Map<String, String>>> roleMenus(@PathVariable("roleid") int roleid) {
        return new ResponseEntity<List<Map<String, String>>>(roleMapper.roleMenus(roleid), HttpStatus.OK);
    }

    /**
     * 人员菜单
     * 
     * @param userid
     * @return
     */
    @GetMapping("/usermenus/{userid}")
    public ResponseEntity<Map<String, List<String>>> userMenus(@PathVariable("userid") int userid) {
        return new ResponseEntity<Map<String, List<String>>>(service.userMenus(userid), HttpStatus.OK);
    }

}
