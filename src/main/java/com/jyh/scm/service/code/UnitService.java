package com.jyh.scm.service.code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.jyh.scm.base.AppConst;
import com.jyh.scm.base.SessionManager;
import com.jyh.scm.dao.code.UnitMapper;
import com.jyh.scm.entity.code.Unit;
import com.jyh.scm.util.TimeUtil;

import tk.mybatis.mapper.entity.Condition;

/**
 * @author jiangyonghua
 * @date 2018年3月5日 下午2:43:23
 */
@Service
public class UnitService {

    @Autowired
    private UnitMapper unitMapper;

    /**
     * 置顶
     * 
     * @param id
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void moveTop(Integer id) {
        Unit obj = new Unit();
        obj.setId(id);
        obj.setSort(0);
        obj.setUpdatedBy(SessionManager.getAccount());
        obj.setUpdatedTime(TimeUtil.getTime());
        unitMapper.updateByPrimaryKeySelective(obj);

        // 其余后置+1
        Condition c = new Condition(Unit.class);
        c.createCriteria().andEqualTo(AppConst.APPID_KEY, SessionManager.getAppid()).andNotEqualTo("id", id);
        List<Unit> units = unitMapper.selectByCondition(c);
        units.forEach(item -> {
            item.setSort(item.getSort() + 1);
            unitMapper.updateByPrimaryKeySelective(item);
        });

    }

}
