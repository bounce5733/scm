package com.jyh.scm.service.code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.jyh.scm.base.AppConst;
import com.jyh.scm.base.SessionManager;
import com.jyh.scm.dao.code.AccountPeriodMapper;
import com.jyh.scm.entity.code.AccountPeriod;
import com.jyh.scm.util.TimeUtil;

import tk.mybatis.mapper.entity.Condition;

/**
 * @author jiangyonghua
 * @date 2018年3月5日 下午2:43:23
 */
@Service
public class AccountPeriodService {

    @Autowired
    private AccountPeriodMapper accountPeriodMapper;

    /**
     * 置顶
     * 
     * @param id
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void moveTop(Integer id) {
        AccountPeriod accountPeriod = new AccountPeriod();
        accountPeriod.setId(id);
        accountPeriod.setAppid(SessionManager.getAppid());
        accountPeriod.setSort(0);
        accountPeriod.setUpdatedBy(SessionManager.getAccount());
        accountPeriod.setUpdatedTime(TimeUtil.getTime());
        accountPeriodMapper.updateByPrimaryKeySelective(accountPeriod);

        // 其余后置+1
        Condition c = new Condition(AccountPeriod.class);
        c.createCriteria().andEqualTo(AppConst.APPID_KEY, SessionManager.getAppid()).andNotEqualTo("id", id);
        List<AccountPeriod> accountPeriods = accountPeriodMapper.selectByCondition(c);
        accountPeriods.forEach(item -> {
            item.setSort(item.getSort() + 1);
            accountPeriodMapper.updateByPrimaryKeySelective(item);
        });

    }

}
