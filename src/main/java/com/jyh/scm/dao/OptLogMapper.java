package com.jyh.scm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jyh.scm.base.Mapper;
import com.jyh.scm.entity.OptLog;

public interface OptLogMapper extends Mapper<OptLog> {

    List<OptLog> queryByPage(@Param("optlog") OptLog optlog, @Param("pageNum") int pageNum,
            @Param("pageSize") int pageSize);
}
