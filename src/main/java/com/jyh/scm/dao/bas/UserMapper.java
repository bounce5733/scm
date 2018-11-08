package com.jyh.scm.dao.bas;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyh.scm.base.Mapper;
import com.jyh.scm.entity.bas.User;

public interface UserMapper extends Mapper<User> {

    List<User> roleUsers(@Param("roleid") int roleid);
}
