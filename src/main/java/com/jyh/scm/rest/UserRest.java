package com.jyh.scm.rest;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jyh.scm.base.AppConst;
import com.jyh.scm.base.SessionManager;
import com.jyh.scm.dao.UserMapper;
import com.jyh.scm.entity.User;
import com.jyh.scm.service.RoleService;
import com.jyh.scm.service.UserService;
import com.jyh.scm.util.IDGenUtil;
import com.jyh.scm.util.TimeUtil;

/**
 * 用户API
 * 
 * @author jiangyonghua
 * @date 2018年1月7日 下午10:43:57
 */
@RestController
@RequestMapping(path = "users")
public class UserRest {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@PostMapping
	public ResponseEntity<Object> addUser(@RequestBody User user) {
		user.setId(IDGenUtil.UUID());
		user.setCreatedBy(SessionManager.getAccount());
		user.setCreatedTime(TimeUtil.getTime());
		user.setPwd(AppConst.SYS_DEFAULT_PWD);
		userMapper.insertSelective(user);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@PatchMapping
	public ResponseEntity<Object> editUser(@RequestBody User user) {
		user.setUpdatedBy(SessionManager.getAccount());
		user.setUpdatedTime(TimeUtil.getTime());
		userMapper.updateByPrimaryKeySelective(user);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") String id) {
		return new ResponseEntity<User>(userMapper.selectByPrimaryKey(id), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<User>> loadUser() {
		return new ResponseEntity<List<User>>(userService.load(), HttpStatus.OK);
	}

	@GetMapping("/matchWithAccountOrName")
	public ResponseEntity<List<User>> queryUser(@RequestParam("param") String param) {
		return new ResponseEntity<List<User>>(userService.query(param), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> removeUser(@PathVariable("id") String id) {
		try {
			userService.remove(id);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<Object>(HttpStatus.LOCKED);
		}
	}

	@GetMapping("/role/{roleid}")
	public ResponseEntity<List<User>> roleUsers(@PathVariable("roleid") String roleid) {
		return new ResponseEntity<List<User>>(userService.roleUsers(roleid), HttpStatus.OK);
	}

	@GetMapping("/userinfo")
	public ResponseEntity<Map<String, Object>> userinfo() {
		Map<String, Object> userinfo = new HashMap<String, Object>();
		User user = userMapper.selectByPrimaryKey(SessionManager.getUserid());
		Map<String, List<String>> menus = roleService.userMenus(SessionManager.getUserid());
		userinfo.put("user", user);
		userinfo.put("menus", menus);
		return new ResponseEntity<Map<String, Object>>(userinfo, HttpStatus.OK);
	}
}
