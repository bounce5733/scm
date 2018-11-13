package com.jyh.scm.service.code;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.jyh.scm.base.SessionManager;
import com.jyh.scm.constant.AppConst;
import com.jyh.scm.dao.code.CustomerGradeMapper;
import com.jyh.scm.entity.bas.User;
import com.jyh.scm.entity.code.CustomerGrade;
import com.jyh.scm.event.UserRegisterEvent;
import com.jyh.scm.util.TimeUtil;

import tk.mybatis.mapper.entity.Condition;

/**
 * @author jiangyonghua
 * @date 2018年3月5日 下午2:43:23
 */
@Service
public class CustomerGradeService {

    @Autowired
    private CustomerGradeMapper customerGradeMapper;

    /**
     * 置顶
     * 
     * @param id
     * @return
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void moveTop(Integer id) {
        CustomerGrade obj = new CustomerGrade();
        obj.setId(id);
        obj.setSort(0);
        obj.setUpdatedBy(SessionManager.getAccount());
        obj.setUpdatedTime(TimeUtil.getTime());
        customerGradeMapper.updateByPrimaryKeySelective(obj);

        // 其余后置+1
        Condition c = new Condition(CustomerGrade.class);
        c.createCriteria().andEqualTo(AppConst.APPID_KEY, SessionManager.getAppid()).andNotEqualTo("id", id);
        List<CustomerGrade> customerGrades = customerGradeMapper.selectByCondition(c);
        customerGrades.forEach(item -> {
            item.setSort(item.getSort() + 1);
            customerGradeMapper.updateByPrimaryKeySelective(item);
        });

    }

    /**
     * 监听用户注册事件<br>
     * 创建默认客户级别
     * 
     * @param userRegisterEvent
     */
    @EventListener
    public void createDefaultCustomerGrade(UserRegisterEvent userRegisterEvent) {
        User user = (User) userRegisterEvent.getSource();
        CustomerGrade customerGrade = new CustomerGrade();
        customerGrade.setAppid(user.getAppid());
        customerGrade.setName(AppConst.DEFAULT_CUSTOMER_GRADE_NAME);
        customerGrade.setDefaulted("T");
        customerGrade.setDiscount(100f);
        customerGrade.setCreatedBy(user.getAccount());
        customerGrade.setCreatedTime(TimeUtil.getTime());
        customerGradeMapper.insertSelective(customerGrade);
    }

}
