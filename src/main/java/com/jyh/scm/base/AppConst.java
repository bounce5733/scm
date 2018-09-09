package com.jyh.scm.base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author jiangyonghua
 * @date 2018年3月5日 下午5:23:22
 */
public class AppConst {

    public static final String ENCODING = "UTF-8";

    public static final String APPID = "scm";

    public static final String SYS_DEFAULT_PWD = "c6f057b86584942e415435ffb1fa93d4";

    public static final int SUPER_ADMIN_ROLEID = 0;

    public static final String BASE_PACKAGE = "com.jyh.scm";// 项目基础包名称，根据自己公司的项目修改

    public static final String MODEL_PACKAGE = "com.jyh.scm.entity";// Model所在包
    public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".dao";// Mapper所在包
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service";// Service所在包
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";// ServiceImpl所在包
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".rest";// Controller所在包

    public static final String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE + ".base.Mapper";// Mapper插件基础接口的完全限定名

    // base64图片缓存
    public final static ConcurrentMap<String, String> BASE64_IMG_CACHE = new ConcurrentHashMap<String, String>();

    // 菜单路径随机字符串长度
    public static final int MENU_PATH_LENGTH = 8;

    // 免验证资源
    public static final List<String> AUTH_SKIP_URI = new ArrayList<String>();

    static {
        AUTH_SKIP_URI.add("/sys/login");
        AUTH_SKIP_URI.add("/sys/register");
    }
}
