package com.jyh.scm.service.sys;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jyh.scm.base.AppConst;
import com.jyh.scm.base.SessionManager;
import com.jyh.scm.dao.sys.OptLogMapper;
import com.jyh.scm.entity.sys.OptLog;
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
     * @param isme
     *            操作人是否是本人 true|是 false|全部
     * @param startTime
     *            开始时间
     * @param endTime
     *            结束时间
     * @param orderField
     *            排序字段
     * @param order
     *            asc | desc
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<OptLog> queryByPage(String optType, boolean isme, String startTime, String endTime,
            String orderField, String order, int pageNum, int pageSize) {

        return PageHelper.startPage(pageNum, pageSize, true).doSelectPageInfo(new ISelect() {

            @Override
            public void doSelect() {
                Example example = new Example(OptLog.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo(AppConst.APPID_KEY, SessionManager.getAppid());
                if (isme) {
                    criteria.andEqualTo("createdBy", SessionManager.getAccount());
                }
                if (StringUtils.isNotBlank(optType)) {
                    criteria.andLike("optType", "%" + optType + "%");
                }
                if (StringUtils.isNotBlank(startTime)) {
                    criteria.andGreaterThanOrEqualTo("createdTime", startTime);
                }
                if (StringUtils.isNotBlank(endTime)) {
                    criteria.andLessThanOrEqualTo("createdTime", endTime);
                }
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
