package com.jyh.scm.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.jyh.scm.dao.bas.RoleMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleMapperTest {

    private static final Logger log = LoggerFactory.getLogger(RoleMapperTest.class);

    @Autowired
    private RoleMapper roleMapper;

    @Test
    @Ignore
    public void userRoles() {
        List<Integer> roleids = roleMapper.userRoles(27);
        log.info(JSON.toJSONString(roleids));
    }
}
