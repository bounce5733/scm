package com.jyh.scm.dao;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.jyh.scm.dao.sys.OptLogMapper;
import com.jyh.scm.entity.sys.OptLog;
import com.jyh.scm.service.sys.OptLogService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OptLogMapperTest {

    private static final Logger log = LoggerFactory.getLogger(OptLogMapper.class);

    @Autowired
    private OptLogService service;

    @Test
    @Ignore
    public void queryByPage() {
        OptLog optlog = new OptLog();
        optlog.setCreatedBy("admin");
        String startTime = "2018-08-30 20:53:15";
        String endTime = "2018-09-02 22:40:22";
        PageInfo<OptLog> optlogPage = service.queryByPage("", true, startTime, endTime, "created_time", "desc", 1, 10);
        log.info(JSON.toJSONString(optlogPage));
    }

}
