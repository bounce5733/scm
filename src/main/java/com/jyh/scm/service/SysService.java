package com.jyh.scm.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.jyh.scm.base.AppConst;
import com.jyh.scm.dao.RoleMapper;
import com.jyh.scm.dao.UserMapper;
import com.jyh.scm.dao.bas.CompanyMapper;
import com.jyh.scm.entity.User;
import com.jyh.scm.entity.bas.Company;
import com.jyh.scm.entity.code.Warehouse;
import com.jyh.scm.util.CodeUtil;
import com.jyh.scm.util.TimeUtil;

/**
 * 系统服务
 * 
 * @author jiangyonghua
 * @date 2018年9月8日 下午3:09:33
 */
@Service
public class SysService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private RoleMapper roleMapper;

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
        User user = new User();
        user.setAccount(registerInfo.get("account"));
        count = userMapper.selectCount(user);
        if (count > 0) {
            return 2;
        }
        // 保存母公司
        company.setLinkmanName(registerInfo.get("name"));
        company.setLinkmanMobile(registerInfo.get("mobile"));
        company.setCreatedBy(registerInfo.get("account"));
        company.setCreatedTime(TimeUtil.getTime());
        companyMapper.insertSelective(company);

        // 获取母公司ID
        company = new Company();
        company.setName(registerInfo.get("companyName"));
        company = companyMapper.selectOne(company);
        int companyid = company.getId();

        // 保存联系人
        user.setAppid(companyid);
        user.setName(registerInfo.get("name"));
        user.setPwd(CodeUtil.md5Encode(registerInfo.get("password")));
        user.setMobile(registerInfo.get("mobile"));
        user.setCreatedBy(registerInfo.get("account"));
        user.setCreatedTime(TimeUtil.getTime());
        userMapper.insertSelective(user);

        // 获取联系人ID
        user = new User();
        user.setAccount(registerInfo.get("account"));
        user = userMapper.selectOne(user);

        // 创建默认仓库
        Warehouse warehouse = new Warehouse();
        warehouse.setCode(AppConst.DEFAULT_WAREHOUSE_CODE);
        warehouse.setName(AppConst.DEFAULT_WAREHOUSE_NAME);
        warehouse.setDefaulted("T");
        warehouse.setCreatedBy(registerInfo.get("account"));
        warehouse.setCreatedTime(TimeUtil.getTime());

        // 授权超级管理员
        roleMapper.assignUser(AppConst.SUPER_ADMIN_ROLEID, user.getId());

        return 0;
    }
}