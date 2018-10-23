package com.jyh.scm.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.jyh.scm.constant.CodeTypeEnum;
import com.jyh.scm.dao.bas.DeptMapper;
import com.jyh.scm.dao.code.AccountPeriodMapper;
import com.jyh.scm.dao.code.CustomerGradeMapper;
import com.jyh.scm.dao.code.ProductCatalogMapper;
import com.jyh.scm.dao.code.UnitMapper;
import com.jyh.scm.dao.code.WarehouseMapper;
import com.jyh.scm.dao.console.CodeItemMapper;
import com.jyh.scm.dao.console.CodeMapper;
import com.jyh.scm.entity.BaseCascaderCode;
import com.jyh.scm.entity.bas.Dept;
import com.jyh.scm.entity.code.AccountPeriod;
import com.jyh.scm.entity.code.CustomerGrade;
import com.jyh.scm.entity.code.ProductCatalog;
import com.jyh.scm.entity.code.Unit;
import com.jyh.scm.entity.code.Warehouse;
import com.jyh.scm.entity.console.Code;
import com.jyh.scm.entity.console.CodeItem;
import com.jyh.scm.entity.sys.OptLog;

import tk.mybatis.mapper.entity.Condition;

@Configuration
public class CacheManager {

    private static final Logger log = LoggerFactory.getLogger(CacheManager.class);

    @Value("${custom.cache.base64ImgCache.expiredTime}")
    private int expiredTime;

    @Autowired
    private CodeMapper codeMapper;

    @Autowired
    private CodeItemMapper codeItemMapper;

    @Autowired
    private AccountPeriodMapper accountPeriodMapper;

    @Autowired
    private UnitMapper unitMapper;

    @Autowired
    private CustomerGradeMapper customerGradeMapper;

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Autowired
    private ProductCatalogMapper productCatalogMapper;

    @Autowired
    private DeptMapper deptMapper;

    // 系统字典缓存{type:[codes]}
    private static LoadingCache<String, List<CodeItem>> sysCode;

    // 系统字典路径缓存{id:{path:'',name:''}}
    private static LoadingCache<String, Map<String, Object>> sysPathCode;

    // 应用二维字典缓存{appid:{type:{id,name}}}
    private static LoadingCache<String, Map<String, List<Map<String, String>>>> appCode;

    // 应用级联字典缓存{appid:{type:[BaseCascaderCode]}}
    private static LoadingCache<String, Map<String, List<BaseCascaderCode<?>>>> appCascadeCode;

    // 应用级联字典路径缓存{appid:{typename:{id:{path:'',name:''}}}
    private static LoadingCache<String, Map<String, Map<String, Map<String, Object>>>> appCascadePathCode;

    // 系统功能名称映射，如：{post_users:新增用户}
    private static LoadingCache<String, String> actionCache;

    // base64图片缓存
    private static LoadingCache<String, String> base64ImgCache;

    // 日志缓存列表
    public static final List<OptLog> LOG_CACHE_LIST = Collections.synchronizedList(new ArrayList<OptLog>());

    @PostConstruct
    public void init() {
        sysCode = CacheBuilder.newBuilder().build(new CacheLoader<String, List<CodeItem>>() {
            @Override
            public List<CodeItem> load(String key) throws Exception {
                return new ArrayList<CodeItem>();
            }
        });
        sysPathCode = CacheBuilder.newBuilder().build(new CacheLoader<String, Map<String, Object>>() {
            @Override
            public Map<String, Object> load(String key) throws Exception {
                return new HashMap<String, Object>();
            }
        });
        appCode = CacheBuilder.newBuilder().build(new CacheLoader<String, Map<String, List<Map<String, String>>>>() {
            @Override
            public Map<String, List<Map<String, String>>> load(String key) throws Exception {
                return new HashMap<String, List<Map<String, String>>>();
            }
        });
        appCascadeCode = CacheBuilder.newBuilder()
                .build(new CacheLoader<String, Map<String, List<BaseCascaderCode<?>>>>() {
                    @Override
                    public Map<String, List<BaseCascaderCode<?>>> load(String key) throws Exception {
                        return new HashMap<String, List<BaseCascaderCode<?>>>();
                    }
                });
        appCascadePathCode = CacheBuilder.newBuilder()
                .build(new CacheLoader<String, Map<String, Map<String, Map<String, Object>>>>() {
                    @Override
                    public Map<String, Map<String, Map<String, Object>>> load(String key) throws Exception {
                        return new HashMap<String, Map<String, Map<String, Object>>>();
                    }
                });
        actionCache = CacheBuilder.newBuilder().build(new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                return new String();
            }
        });
        base64ImgCache = CacheBuilder.newBuilder().expireAfterAccess(expiredTime, TimeUnit.MINUTES)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return new String();
                    }
                });
    }

    /**
     * 加载系统字典缓存{type:[codes]}
     * 
     */
    @PostConstruct
    public Map<String, List<CodeItem>> loadSysCode() {
        ConcurrentMap<String, List<CodeItem>> sysCodeMap = sysCode.asMap();
        if (sysCodeMap == null || sysCodeMap.isEmpty()) {
            log.info("加载系统字典缓存{type:[codes]}...");
            // 组装字典数结构
            List<Code> codes = codeMapper.selectAll();
            for (Code code : codes) {
                CodeItem param = new CodeItem();
                param.setType(code.getCode());
                List<CodeItem> items = codeItemMapper.select(param);
                List<CodeItem> topItems = items.stream().filter(item -> 0 == item.getPid()).sorted()
                        .collect(Collectors.toList());
                makeSysCodeTree(topItems, items);

                // 为各条目添加主键路径
                for (CodeItem topCode : topItems) {
                    topCode.makePath(new LinkedList<Integer>());
                }
                sysCodeMap.put(code.getCode(), topItems);
            }
        }
        return sysCodeMap;
    }

    /**
     * 加载系统字典路径缓存{id:{path:'',name:''}})
     * 
     */
    @PostConstruct
    public Map<String, Map<String, Object>> loadSysPathCode() {
        ConcurrentMap<String, Map<String, Object>> sysPathCodeMap = sysPathCode.asMap();
        if (sysPathCodeMap == null || sysPathCodeMap.isEmpty()) {
            log.info("加载系统字典路径缓存{id:{path:'',name:''}}...");
            Map<String, List<CodeItem>> codeCache = new HashMap<String, List<CodeItem>>();
            List<Code> codes = codeMapper.selectAll();
            for (Code code : codes) {
                CodeItem param = new CodeItem();
                param.setType(code.getCode());
                List<CodeItem> items = codeItemMapper.select(param);
                List<CodeItem> topCodes = items.stream().filter(item -> 0 == item.getPid()).sorted()
                        .collect(Collectors.toList());

                makeSysCodeTree(topCodes, items);

                // 添加主键路径
                for (CodeItem topCode : topCodes) {
                    topCode.makePath(new LinkedList<Integer>());
                }
                codeCache.put(code.getCode(), topCodes);
            }
            // 组装字典主键映射
            for (String key : codeCache.keySet()) {
                for (CodeItem code : codeCache.get(key)) {
                    makeSysCodeMap(sysPathCodeMap, code);
                }
            }
        }
        return sysPathCodeMap;
    }

    /**
     * 加载应用字典缓存{appid:<type:<id,name>>}
     * 
     */
    @PostConstruct
    public Map<String, Map<String, List<Map<String, String>>>> loadAppCode() {
        ConcurrentMap<String, Map<String, List<Map<String, String>>>> appCodeMap = appCode.asMap();
        if (appCodeMap == null || appCodeMap.isEmpty()) {
            log.info("加载应用字典缓存{appid:<type:<id,name>>}...");
            List<AccountPeriod> accountPeriodList = accountPeriodMapper.selectAll();
            accountPeriodList.forEach(code -> {
                String appid = String.valueOf(code.getAppid());
                if (appCodeMap.get(appid) == null) {
                    appCodeMap.put(appid, new HashMap<String, List<Map<String, String>>>());
                }
                if (appCodeMap.get(appid).get(CodeTypeEnum.accountPeriod.name()) == null) {
                    appCodeMap.get(appid).put(CodeTypeEnum.accountPeriod.name(), new ArrayList<Map<String, String>>());
                }
                Map<String, String> item = new HashMap<String, String>();
                item.put("id", String.valueOf(code.getId()));
                item.put("name", code.getName());
                appCodeMap.get(appid).get(CodeTypeEnum.accountPeriod.name()).add(item);
            });
            List<Unit> unitList = unitMapper.selectAll();
            unitList.forEach(code -> {
                String appid = String.valueOf(code.getAppid());
                if (appCodeMap.get(appid) == null) {
                    appCodeMap.put(appid, new HashMap<String, List<Map<String, String>>>());
                }
                if (appCodeMap.get(appid).get(CodeTypeEnum.unit.name()) == null) {
                    appCodeMap.get(appid).put(CodeTypeEnum.unit.name(), new ArrayList<Map<String, String>>());
                }
                Map<String, String> item = new HashMap<String, String>();
                item.put("id", String.valueOf(code.getId()));
                item.put("name", code.getName());
                appCodeMap.get(appid).get(CodeTypeEnum.unit.name()).add(item);
            });
            Condition c = new Condition(Warehouse.class);
            c.createCriteria().andEqualTo("enabled", "T");
            List<Warehouse> warehouseList = warehouseMapper.selectByCondition(c);
            warehouseList.forEach(code -> {
                String appid = String.valueOf(code.getAppid());
                if (appCodeMap.get(appid) == null) {
                    appCodeMap.put(appid, new HashMap<String, List<Map<String, String>>>());
                }
                if (appCodeMap.get(appid).get(CodeTypeEnum.warehouse.name()) == null) {
                    appCodeMap.get(appid).put(CodeTypeEnum.warehouse.name(), new ArrayList<Map<String, String>>());
                }
                Map<String, String> item = new HashMap<String, String>();
                item.put("id", String.valueOf(code.getId()));
                item.put("name", code.getName());
                appCodeMap.get(appid).get(CodeTypeEnum.warehouse.name()).add(item);
            });
            c = new Condition(CustomerGrade.class);
            List<CustomerGrade> customerGradeList = customerGradeMapper.selectAll();
            customerGradeList.forEach(code -> {
                String appid = String.valueOf(code.getAppid());
                if (appCodeMap.get(appid) == null) {
                    appCodeMap.put(appid, new HashMap<String, List<Map<String, String>>>());
                }
                if (appCodeMap.get(appid).get(CodeTypeEnum.customerGrade.name()) == null) {
                    appCodeMap.get(appid).put(CodeTypeEnum.customerGrade.name(), new ArrayList<Map<String, String>>());
                }
                Map<String, String> item = new HashMap<String, String>();
                item.put("id", String.valueOf(code.getId()));
                item.put("name", code.getName());
                appCodeMap.get(appid).get(CodeTypeEnum.customerGrade.name()).add(item);
            });
        }
        return appCodeMap;
    }

    /**
     * 加载应用字典级联缓存{appid:{type:[BaseCascaderCode]}}
     * 
     */
    @PostConstruct
    public Map<String, Map<String, List<BaseCascaderCode<?>>>> loadAppCascadeCode() {
        ConcurrentMap<String, Map<String, List<BaseCascaderCode<?>>>> appCascadeCodeMap = appCascadeCode.asMap();
        if (appCascadeCodeMap == null || appCascadeCodeMap.isEmpty()) {
            log.info("加载应用级联缓存{appid:{type:[BaseCascaderCode]}}...");
            Map<String, Map<String, List<BaseCascaderCode<?>>>> tempAppCascadeCodeMap = new HashMap<String, Map<String, List<BaseCascaderCode<?>>>>();
            // 商品类型
            List<ProductCatalog> productCatalogs = productCatalogMapper.selectAll();

            // 按应用分类，并转为泛型
            productCatalogs.forEach(item -> {
                if (tempAppCascadeCodeMap.get(String.valueOf(item.getAppid())) == null) {
                    tempAppCascadeCodeMap.put(String.valueOf(item.getAppid()),
                            new HashMap<String, List<BaseCascaderCode<?>>>());
                }
                if (tempAppCascadeCodeMap.get(String.valueOf(item.getAppid()))
                        .get(CodeTypeEnum.productCatalog.name()) == null) {
                    tempAppCascadeCodeMap.get(String.valueOf(item.getAppid())).put(CodeTypeEnum.productCatalog.name(),
                            new ArrayList<BaseCascaderCode<?>>());
                }
                tempAppCascadeCodeMap.get(String.valueOf(item.getAppid())).get(CodeTypeEnum.productCatalog.name())
                        .add(item);
            });

            tempAppCascadeCodeMap.forEach((appid, itemMap) -> {
                itemMap.forEach((type, itemList) -> {
                    List<BaseCascaderCode<?>> topItems = itemList.stream().filter(item -> 0 == item.getPid()).sorted()
                            .collect(Collectors.toList());
                    makeAppCascadeCodeTree(topItems, itemList);
                    // 为各条目添加主键路径
                    for (BaseCascaderCode<?> topItem : topItems) {
                        topItem.makePath(new LinkedList<Integer>());
                    }
                    if (appCascadeCodeMap.get(appid) == null) {
                        appCascadeCodeMap.put(appid, new HashMap<String, List<BaseCascaderCode<?>>>());
                    }
                    appCascadeCodeMap.get(appid).put(CodeTypeEnum.productCatalog.name(), topItems);
                });

            });

            // 公司部门
            List<Dept> depts = deptMapper.selectAll();

            // 按应用分类，并转为泛型
            depts.forEach(item -> {
                if (tempAppCascadeCodeMap.get(String.valueOf(item.getAppid())) == null) {
                    tempAppCascadeCodeMap.put(String.valueOf(item.getAppid()),
                            new HashMap<String, List<BaseCascaderCode<?>>>());
                }
                if (tempAppCascadeCodeMap.get(String.valueOf(item.getAppid())).get(CodeTypeEnum.dept.name()) == null) {
                    tempAppCascadeCodeMap.get(String.valueOf(item.getAppid())).put(CodeTypeEnum.dept.name(),
                            new ArrayList<BaseCascaderCode<?>>());
                }
                tempAppCascadeCodeMap.get(String.valueOf(item.getAppid())).get(CodeTypeEnum.dept.name()).add(item);
            });

            tempAppCascadeCodeMap.forEach((appid, itemMap) -> {
                itemMap.forEach((type, itemList) -> {
                    List<BaseCascaderCode<?>> topItems = itemList.stream().filter(item -> 0 == item.getPid()).sorted()
                            .collect(Collectors.toList());
                    makeAppCascadeCodeTree(topItems, itemList);
                    // 为各条目添加主键路径
                    for (BaseCascaderCode<?> topItem : topItems) {
                        topItem.makePath(new LinkedList<Integer>());
                    }
                    if (appCascadeCodeMap.get(appid) == null) {
                        appCascadeCodeMap.put(appid, new HashMap<String, List<BaseCascaderCode<?>>>());
                    }
                    appCascadeCodeMap.get(appid).put(CodeTypeEnum.dept.name(), topItems);
                });

            });
        }
        return appCascadeCodeMap;
    }

    /**
     * 加载应用级联字典路径缓存{appid:{typename:{id:{path:'',name:''}}}
     * 
     */
    @PostConstruct
    public Map<String, Map<String, Map<String, Map<String, Object>>>> loadAppCascadePathCode() {
        ConcurrentMap<String, Map<String, Map<String, Map<String, Object>>>> appCascadePathCodeMap = appCascadePathCode
                .asMap();
        if (appCascadePathCodeMap == null || appCascadePathCodeMap.isEmpty()) {
            log.info("加载应用级联字典路径缓存{appid:{typename:{id:{path:'',name:''}}}");
            final Map<String, Map<String, List<BaseCascaderCode<?>>>> appDataMap = new HashMap<String, Map<String, List<BaseCascaderCode<?>>>>();
            // 收集商品分类
            List<ProductCatalog> productCatalogs = productCatalogMapper.selectAll();
            productCatalogs.forEach(item -> {
                if (appDataMap.get(String.valueOf(item.getAppid())) == null) {
                    appDataMap.put(String.valueOf(item.getAppid()), new HashMap<String, List<BaseCascaderCode<?>>>());
                }
                if (appDataMap.get(String.valueOf(item.getAppid())).get(CodeTypeEnum.productCatalog.name()) == null) {
                    appDataMap.get(String.valueOf(item.getAppid())).put(CodeTypeEnum.productCatalog.name(),
                            new ArrayList<BaseCascaderCode<?>>());
                }
                appDataMap.get(String.valueOf(item.getAppid())).get(CodeTypeEnum.productCatalog.name()).add(item);
            });
            // 收集公司部门
            List<Dept> depts = deptMapper.selectAll();
            depts.forEach(item -> {
                if (appDataMap.get(String.valueOf(item.getAppid())) == null) {
                    appDataMap.put(String.valueOf(item.getAppid()), new HashMap<String, List<BaseCascaderCode<?>>>());
                }
                if (appDataMap.get(String.valueOf(item.getAppid())).get(CodeTypeEnum.dept.name()) == null) {
                    appDataMap.get(String.valueOf(item.getAppid())).put(CodeTypeEnum.dept.name(),
                            new ArrayList<BaseCascaderCode<?>>());
                }
                appDataMap.get(String.valueOf(item.getAppid())).get(CodeTypeEnum.dept.name()).add(item);
            });
            appDataMap.forEach((appid, typeMap) -> {
                typeMap.forEach((type, datalist) -> {
                    List<BaseCascaderCode<?>> topItemList = datalist.stream().filter(item -> 0 == item.getPid())
                            .sorted().collect(Collectors.toList());
                    if (topItemList.size() > 0) {
                        Map<String, Map<String, Object>> pathMap = new HashMap<String, Map<String, Object>>();
                        // 组装子项目
                        makeAppCascaderCodeTree(topItemList, datalist);
                        // 组装主键路由
                        for (BaseCascaderCode<?> data : topItemList) {
                            data.makePath(new LinkedList<Integer>());
                        }
                        topItemList.forEach(item -> {
                            makeAppPathMap(pathMap, item);
                        });
                        if (appCascadePathCodeMap.get(appid) == null) {
                            appCascadePathCodeMap.put(appid, new HashMap<String, Map<String, Map<String, Object>>>());
                        }
                        appCascadePathCodeMap.get(appid).put(type, pathMap);
                    }
                });
            });
        }
        return appCascadePathCodeMap;
    }

    /**
     * 系统功能名称映射
     * 
     * @return 如：{post_users:新增用户}
     */
    public static void cacheAction(Map<String, String> actions) {
        ConcurrentMap<String, String> actionCacheMap = actionCache.asMap();
        actions.forEach((key, val) -> {
            actionCacheMap.put(key, val);
        });
    }

    /**
     * @return 系统功能{post_users:新增用户}
     */
    public static Map<String, String> getActionMap() {
        return actionCache.asMap();
    }

    /**
     * 缓存图像
     * 
     * @param base64Img
     */
    public static void cacheBase64Img(String base64Img) {
        base64ImgCache.put(SessionManager.getSessionid(), base64Img);
    }

    /**
     * 获取缓存图像
     * 
     * @return
     */
    public static String getBase64Img() {
        try {
            return base64ImgCache.get(SessionManager.getSessionid());
        } catch (ExecutionException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * 刷行系统字典缓存
     */
    public void refreshSysCode() {
        sysCode.invalidateAll();
        sysPathCode.invalidateAll();
        this.loadSysCode();
        this.loadSysPathCode();
    }

    /**
     * 刷新应用字典缓存
     */
    public void refreshAppCode() {
        appCode.invalidateAll();
        this.loadAppCode();
    }

    /**
     * 刷新应用级联字典缓存
     */
    public void refreshAppCascadeCode() {
        appCascadeCode.invalidateAll();
        appCascadePathCode.invalidateAll();
        this.loadAppCascadeCode();
        this.loadAppCascadePathCode();
    }

    /**
     * 组装字典名称与路径
     * 
     * @param codemap
     * @param code
     */
    private void makeSysCodeMap(Map<String, Map<String, Object>> codemap, CodeItem code) {
        Map<String, Object> codeMapItem = new HashMap<String, Object>();
        codeMapItem.put("name", code.getName());
        codeMapItem.put("path", code.getPath());
        code.setPath(null);
        codemap.put(String.valueOf(code.getId()), codeMapItem);
        if (code.getChildren() != null) {
            for (CodeItem item : code.getChildren()) {
                makeSysCodeMap(codemap, item);
            }
        }
    }

    /**
     * 递归组装字典树
     * 
     * @param topItems
     *            父字典列表
     * @param allItems
     *            总字典列表
     */
    private void makeSysCodeTree(List<CodeItem> topItems, List<CodeItem> allItems) {
        for (CodeItem pitem : topItems) {
            // 收集下级子菜单
            List<CodeItem> child = allItems.stream().filter(item -> item.getPid().equals(pitem.getId())).sorted()
                    .collect(Collectors.toList());
            if (child.size() > 0) {
                // 子菜单列表排序
                pitem.setChildren(child);
                makeSysCodeTree(child, allItems);
            }
        }
    }

    /**
     * 递归组装字典树
     * 
     * @param topItems
     *            父字典列表
     * @param allItems
     *            总字典列表
     */
    private void makeAppCascadeCodeTree(List<BaseCascaderCode<?>> topItems, List<BaseCascaderCode<?>> allItems) {
        for (BaseCascaderCode<?> pitem : topItems) {
            // 收集下级子菜单
            List<BaseCascaderCode<?>> child = allItems.stream().filter(item -> item.getPid().equals(pitem.getId()))
                    .sorted().collect(Collectors.toList());
            if (child.size() > 0) {
                // 子菜单列表排序
                pitem.setChildren(child);
                makeAppCascadeCodeTree(child, allItems);
            }
        }
    }

    /**
     * 组装应用级联字典路径
     * 
     * @param pathMap
     * @param item
     */
    private void makeAppPathMap(Map<String, Map<String, Object>> pathMap, BaseCascaderCode<?> item) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("name", item.getName());
        dataMap.put("path", item.getPath());
        item.setPath(null);
        pathMap.put(String.valueOf(item.getId()), dataMap);
        if (item.getChildren() != null) {
            for (BaseCascaderCode<?> subitem : item.getChildren()) {
                makeAppPathMap(pathMap, subitem);
            }
        }
    }

    /**
     * 递归组装应用字典树
     * 
     * @param pItems
     *            父字典列表
     * @param allItems
     *            总字典列表
     */
    private void makeAppCascaderCodeTree(List<BaseCascaderCode<?>> pDataList, List<BaseCascaderCode<?>> dataList) {
        for (BaseCascaderCode<?> pitem : pDataList) {
            // 收集下级子菜单
            List<BaseCascaderCode<?>> child = dataList.stream().filter(item -> item.getPid().equals(pitem.getId()))
                    .sorted().collect(Collectors.toList());
            if (child.size() > 0) {
                // 子菜单列表排序
                pitem.setChildren(child);
                makeAppCascaderCodeTree(child, dataList);
            }
        }
    }

}
