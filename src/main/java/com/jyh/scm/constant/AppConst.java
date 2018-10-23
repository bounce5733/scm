package com.jyh.scm.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jiangyonghua
 * @date 2018年3月5日 下午5:23:22
 */
public class AppConst {

    public static final String ENCODING = "UTF-8";

    public static final String SUPER_APPID = "0";
    
    public static final String APPID_KEY = "appid";

    public static final String SYS_DEFAULT_PWD = "c6f057b86584942e415435ffb1fa93d4";
    
    public static final int SUPER_COMPANY_ID = 0;
    
    public static final int SUPER_ADMIN_ROLEID = 0;

    public static final String BASE_PACKAGE = "com.jyh.scm";// 项目基础包名称，根据自己公司的项目修改

    public static final String MODEL_PACKAGE = "com.jyh.scm.entity";// Model所在包
    public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".dao";// Mapper所在包
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service";// Service所在包
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";// ServiceImpl所在包
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".rest";// Controller所在包

    public static final String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE + ".base.Mapper";// Mapper插件基础接口的完全限定名

    // 菜单路径随机字符串长度
    public static final int MENU_PATH_LENGTH = 8;

    // 免验证资源
    public static final List<String> AUTH_SKIP_URI = new ArrayList<String>();

    //------默认仓库------
    public static final String DEFAULT_WAREHOUSE_CODE = "001";
    
    public static final String DEFAULT_WAREHOUSE_NAME = "默认仓";
    
    //------默认商品分类------
    public static final String DEFAULT_PRODUCT_CATALOG_NAME = "通用";
    
  //------默认部门------
    public static final String DEFAULT_DEPT_NAME = "总部";
    
    //------默认客户级别------
    public static final String DEFAULT_CUSTOMER_GRADE_NAME = "普通";
    
    static {
        AUTH_SKIP_URI.add("/sys/login");
        AUTH_SKIP_URI.add("/sys/login/register");
    }
}
