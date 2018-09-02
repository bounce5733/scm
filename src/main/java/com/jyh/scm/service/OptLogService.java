package com.jyh.scm.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyh.scm.dao.OptLogMapper;
import com.jyh.scm.entity.OptLog;
import com.jyh.scm.util.StringUtil;

import tk.mybatis.mapper.entity.Example;

/**
 * 操作日志
 * 
 * @author jiangyonghua
 * @date 2018年9月1日 上午9:51:17
 */
@Service
public class OptLogService {

    @Autowired
    private OptLogMapper optLogMapper;

    /**
     * @param optType
     *            操作类型
     * @param orderField
     *            排序字段
     * @param order
     *            asc | desc
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<OptLog> queryByPage(String optType, String orderField, String order, int pageNum, int pageSize) {

        return PageHelper.startPage(pageNum, pageSize, true).doSelectPageInfo(new ISelect() {

            @Override
            public void doSelect() {
                Example example = new Example(OptLog.class);
                Example.Criteria criteria = example.createCriteria();
                if (StringUtils.isNotBlank(optType)) {
                    criteria.andLike("optType", "%" + optType + "%");
                }
                if (StringUtils.isNotBlank(orderField)) {
                    if (StringUtils.isNotBlank(order)) {
                        example.setOrderByClause(StringUtil.camelToUnderline(orderField) + " " + order);
                    }
                }
                optLogMapper.selectByCondition(example);
            }

        });
    }

}
