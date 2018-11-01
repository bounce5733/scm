package com.jyh.scm.service.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.jyh.scm.constant.AppConst;
import com.jyh.scm.dao.bas.CompanyMapper;
import com.jyh.scm.dao.bas.DeptMapper;
import com.jyh.scm.dao.bas.RoleMapper;
import com.jyh.scm.dao.bas.UserMapper;
import com.jyh.scm.dao.code.CustomerGradeMapper;
import com.jyh.scm.dao.code.ProductCatalogMapper;
import com.jyh.scm.dao.code.WarehouseMapper;
import com.jyh.scm.entity.bas.Company;
import com.jyh.scm.entity.bas.Dept;
import com.jyh.scm.entity.bas.Role;
import com.jyh.scm.entity.bas.User;
import com.jyh.scm.entity.code.CustomerGrade;
import com.jyh.scm.entity.code.ProductCatalog;
import com.jyh.scm.entity.code.Warehouse;
import com.jyh.scm.util.CodeUtil;
import com.jyh.scm.util.TimeUtil;

import tk.mybatis.mapper.entity.Condition;

/**
 * 系统服务
 * 
 * @author jiangyonghua
 * @date 2018年9月8日 下午3:09:33
 */
@Service
public class LoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Autowired
    private CustomerGradeMapper customerGradeMapper;

    @Autowired
    private ProductCatalogMapper productCatalogMapper;

    @Autowired
    private DeptMapper deptMapper;

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

        // 创建默认角色并授权
        // 系统默认权限{menuName,[{menuKey,actionKey}]}
        final Map<String, List<Map<String, String>>> superRoleMenu = new HashMap<String, List<Map<String, String>>>();
        Condition c = new Condition(Role.class);
        c.createCriteria().andEqualTo(AppConst.APPID_KEY, AppConst.SUPER_APPID);
        List<Role> roles = roleMapper.selectByCondition(c);
        roles.forEach(item -> {
            if (item.getId() != AppConst.SUPER_ADMIN_ROLEID) {
                Role role = new Role();
                role.setAppid(companyid);
                role.setName(item.getName());
                role.setCreatedBy(registerInfo.get("account"));
                role.setCreatedTime(TimeUtil.getTime());
                // 复制角色
                roleMapper.insertSelective(role);
                // 获取系统默认菜单暂存
                superRoleMenu.put(role.getName(), roleMapper.roleMenus(item.getId()));
            }
        });

        // 授权菜单
        c = new Condition(Role.class);
        c.createCriteria().andEqualTo(AppConst.APPID_KEY, companyid);
        roles = roleMapper.selectByCondition(c);
        roles.forEach(role -> {
            superRoleMenu.get(role.getName()).forEach(menu -> {
                roleMapper.assignMenu(role.getId(), menu.get("menuKey"), menu.get("actionKey"));
            });
            // 授权角色
            roleMapper.assignUser(role.getId(), user.getId());
        });

        // 创建默认仓库
        Warehouse warehouse = new Warehouse();
        warehouse.setAppid(companyid);
        warehouse.setCode(AppConst.DEFAULT_WAREHOUSE_CODE);
        warehouse.setName(AppConst.DEFAULT_WAREHOUSE_NAME);
        warehouse.setDefaulted("T");
        warehouse.setCreatedBy(registerInfo.get("account"));
        warehouse.setCreatedTime(TimeUtil.getTime());
        warehouseMapper.insertSelective(warehouse);

        // 创建默认商品分类
        ProductCatalog productCatalog = new ProductCatalog();
        productCatalog.setAppid(companyid);
        productCatalog.setName(AppConst.DEFAULT_PRODUCT_CATALOG_NAME);
        productCatalog.setDefaulted("T");
        productCatalog.setCreatedBy(registerInfo.get("account"));
        productCatalog.setCreatedTime(TimeUtil.getTime());
        productCatalogMapper.insertSelective(productCatalog);

        // 创建默认客户级别
        CustomerGrade customerGrade = new CustomerGrade();
        customerGrade.setAppid(companyid);
        customerGrade.setName(AppConst.DEFAULT_CUSTOMER_GRADE_NAME);
        customerGrade.setDefaulted("T");
        customerGrade.setDiscount(100f);
        customerGrade.setCreatedBy(registerInfo.get("account"));
        customerGrade.setCreatedTime(TimeUtil.getTime());
        customerGradeMapper.insertSelective(customerGrade);

        // 创建总部
        Dept dept = new Dept();
        dept.setAppid(companyid);
        dept.setName(AppConst.DEFAULT_DEPT_NAME);
        dept.setDefaulted("T");
        dept.setCreatedBy(registerInfo.get("account"));
        dept.setCreatedTime(TimeUtil.getTime());
        deptMapper.insertSelective(dept);

        return 0;
    }
}
