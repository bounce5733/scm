package com.jyh.scm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.jyh.scm.entity.OptLog;
import com.jyh.scm.service.OptLogService;

/**
 * 操作日志
 * 
 * @author jiangyonghua
 * @date 2018年8月31日 上午12:42:57
 */
@RestController
@RequestMapping(path = "optlogs")
public class OptLogRest {

    @Autowired
    private OptLogService optLogService;

    @GetMapping
    public ResponseEntity<PageInfo<OptLog>> queryByPage(@RequestParam("optType") String optType,
            @RequestParam("orderField") String orderField, @RequestParam("order") String order,
            @RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        return new ResponseEntity<PageInfo<OptLog>>(
                optLogService.queryByPage(optType, orderField, order, pageNum, pageSize), HttpStatus.OK);
    }

}
