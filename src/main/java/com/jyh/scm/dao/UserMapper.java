package com.jyh.scm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyh.scm.base.Mapper;
import com.jyh.scm.entity.User;

public interface UserMapper extends Mapper<User> {

	List<User> roleUsers(@Param("roleid") int roleid);
}
