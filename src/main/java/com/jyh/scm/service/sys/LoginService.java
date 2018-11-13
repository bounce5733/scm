package com.jyh.scm.service.sys;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.jyh.scm.dao.bas.CompanyMapper;
import com.jyh.scm.dao.bas.UserMapper;
import com.jyh.scm.entity.bas.Company;
import com.jyh.scm.entity.bas.User;
import com.jyh.scm.event.UserRegisterEvent;
import com.jyh.scm.util.CodeUtil;
import com.jyh.scm.util.TimeUtil;

/**
 * 系统服务
 * 
 * @author jiangyonghua
 * @date 2018年9月8日 下午3:09:33
 */
@Service
public class LoginService {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CompanyMapper companyMapper;

    /**
     * 注册公司
     * 
     * @param registerInfo
     * @return 0成功 | 1公司名称冲突 | 2账号冲突
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public int register(Map<String, String> registerInfo) {
        // 检查公司是否存在
        Company company = new Company();
        company.setName(registerInfo.get("companyName"));
        int count = companyMapper.selectCount(company);
        if (count > 0) {
            return 1;
        }

        // 验证用户名是否存在
        User param = new User();
        param.setAccount(registerInfo.get("account"));
        count = userMapper.selectCount(param);
        if (count > 0) {
            return 2;
        }

        // 创建公司
        company.setLinkmanName(registerInfo.get("name"));
        company.setLinkmanMobile(registerInfo.get("mobile"));
        company.setCreatedBy(registerInfo.get("account"));
        company.setCreatedTime(TimeUtil.getTime());
        companyMapper.insertSelective(company);

        // 获取公司ID
        company = new Company();
        company.setName(registerInfo.get("companyName"));
        company = companyMapper.selectOne(company);
        int companyid = company.getId();

        // 保存联系人
        param.setAppid(companyid);
        param.setName(registerInfo.get("name"));
        param.setPwd(CodeUtil.md5Encode(registerInfo.get("password")));
        param.setMobile(registerInfo.get("mobile"));
        param.setIsCompanyCreater("T");
        param.setCreatedBy(registerInfo.get("account"));
        param.setCreatedTime(TimeUtil.getTime());
        userMapper.insertSelective(param);

        // 获取联系人ID
        param = new User();
        param.setAccount(registerInfo.get("account"));
        final User user = userMapper.selectOne(param);

        // 广播用户注册事件
        applicationEventPublisher.publishEvent(new UserRegisterEvent(user));

        return 0;
    }
}
