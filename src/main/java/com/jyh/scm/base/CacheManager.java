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
import com.jyh.scm.dao.code.AccountPeriodMapper;
import com.jyh.scm.dao.code.ProductCatalogMapper;
import com.jyh.scm.dao.sys.CodeItemMapper;
import com.jyh.scm.dao.sys.CodeMapper;
import com.jyh.scm.entity.BaseCascaderCode;
import com.jyh.scm.entity.code.AccountPeriod;
import com.jyh.scm.entity.code.ProductCatalog;
import com.jyh.scm.entity.sys.Code;
import com.jyh.scm.entity.sys.CodeItem;
import com.jyh.scm.entity.sys.OptLog;

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
    private ProductCatalogMapper productCatalogMapper;

    // 系统字典缓存{appid:{type:[codes]}}
    private static LoadingCache<String, Map<String, List<CodeItem>>> sysCode;

    // 系统字典路径缓存{appid:{id:{path:'',name:''}}}
    private static LoadingCache<String, Map<String, Map<String, Object>>> sysPathCode;

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
        sysCode = CacheBuilder.newBuilder().build(new CacheLoader<String, Map<String, List<CodeItem>>>() {
            @Override
            public Map<String, List<CodeItem>> load(String key) throws Exception {
                return new HashMap<String, List<CodeItem>>();
            }
        });
        sysPathCode = CacheBuilder.newBuilder().build(new CacheLoader<String, Map<String, Map<String, Object>>>() {
            @Override
            public Map<String, Map<String, Object>> load(String key) throws Exception {
                return new HashMap<String, Map<String, Object>>();
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
    public Map<String, Map<String, List<CodeItem>>> loadSysCode() {
        ConcurrentMap<String, Map<String, List<CodeItem>>> sysCodeMap = sysCode.asMap();
        if (sysCodeMap == null || sysCodeMap.isEmpty()) {
            log.info("加载系统字典缓存{type:[codes]}...");
            // 组装字典数结构
            List<Code> codes = codeMapper.selectAll();
            // 按appid归类
            Map<String, List<Code>> allAppCodeMap = new HashMap<String, List<Code>>();
            codes.forEach(code -> {
                String appid = String.valueOf(code.getAppid());
                if (allAppCodeMap.get(appid) == null) {
                    allAppCodeMap.put(appid, new ArrayList<Code>());
                }
                allAppCodeMap.get(appid).add(code);
            });

            for (String appid : allAppCodeMap.keySet()) {
                Map<String, List<CodeItem>> appCodeMap = new HashMap<String, List<CodeItem>>();
                for (Code code : allAppCodeMap.get(appid)) {
                    CodeItem param = new CodeItem();
                    param.setType(code.getCode());
                    List<CodeItem> items = codeItemMapper.select(param);
                    List<CodeItem> topItems = items.stream().filter(item -> 0 == item.getPid()).sorted()
                            .collect(Collectors.toList());
                    makeCodeTree(topItems, items);

                    // 为各条目添加主键路径
                    for (CodeItem topCode : topItems) {
                        topCode.makePath(new LinkedList<Integer>());
                    }
                    appCodeMap.put(code.getCode(), topItems);
                }
                sysCodeMap.put(appid, appCodeMap);
            }
        }
        return sysCodeMap;
    }

    /**
     * 加载系统字典路径缓存{id:{path:'',name:''}})
     * 
     */
    @PostConstruct
    public Map<String, Map<String, Map<String, Object>>> loadSysPathCode() {
        ConcurrentMap<String, Map<String, Map<String, Object>>> sysPathCodeMap = sysPathCode.asMap();
        if (sysPathCodeMap == null || sysPathCodeMap.isEmpty()) {
            log.info("加载系统字典路径缓存{id:{path:'',name:''}}...");
            Map<String, List<CodeItem>> codeCache = new HashMap<String, List<CodeItem>>();
            List<Code> codes = codeMapper.selectAll();
            // 按appid归类
            Map<String, List<Code>> allAppCodeMap = new HashMap<String, List<Code>>();
            codes.forEach(code -> {
                String appid = String.valueOf(code.getAppid());
                if (allAppCodeMap.get(appid) == null) {
                    allAppCodeMap.put(appid, new ArrayList<Code>());
                }
                allAppCodeMap.get(appid).add(code);
            });

            for (String appid : allAppCodeMap.keySet()) {
                Map<String, Map<String, Object>> appCodeMap = new HashMap<String, Map<String, Object>>();
                for (Code code : allAppCodeMap.get(appid)) {
                    CodeItem param = new CodeItem();
                    param.setType(code.getCode());
                    List<CodeItem> items = codeItemMapper.select(param);
                    List<CodeItem> topCodes = items.stream().filter(item -> 0 == item.getPid()).sorted()
                            .collect(Collectors.toList());

                    makeCodeTree(topCodes, items);

                    // 添加主键路径
                    for (CodeItem topCode : topCodes) {
                        topCode.makePath(new LinkedList<Integer>());
                    }
                    codeCache.put(code.getCode(), topCodes);
                }
                // 组装字典主键映射
                for (String key : codeCache.keySet()) {
                    for (CodeItem code : codeCache.get(key)) {
                        makeCodeMap(appCodeMap, code);
                    }
                }
                sysPathCodeMap.put(appid, appCodeMap);
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
        }
        return appCodeMap;
    }

    /**
     * 加载应用字典级联缓存{appid:<type:[BaseCascaderCode]>}
     * 
     */
    @PostConstruct
    public Map<String, Map<String, List<BaseCascaderCode<?>>>> loadAppCascadeCode() {
        ConcurrentMap<String, Map<String, List<BaseCascaderCode<?>>>> appCascadeCodeMap = appCascadeCode.asMap();
        if (appCascadeCodeMap == null || appCascadeCodeMap.isEmpty()) {
            Map<String, List<BaseCascaderCode<?>>> appItemMap = new HashMap<String, List<BaseCascaderCode<?>>>();
            log.info("加载应用级联缓存{appid:<type:[BaseCascaderCode]>}...");
            // 商品类型
            List<ProductCatalog> items = productCatalogMapper.selectAll();
            // 转换为基础类型
            final List<BaseCascaderCode<?>> baseItems = new ArrayList<BaseCascaderCode<?>>();
            items.forEach(item -> {
                baseItems.add(item);
            });
            List<BaseCascaderCode<?>> topItems = items.stream().filter(item -> 0 == item.getPid()).sorted()
                    .collect(Collectors.toList());
            makeAppCascadeCodeTree(topItems, baseItems);
            // 为各条目添加主键路径
            for (BaseCascaderCode<?> topItem : topItems) {
                topItem.makePath(new LinkedList<Integer>());
            }
            appItemMap.put(CodeTypeEnum.productCatalog.name(), topItems);
            appCascadeCodeMap.put(String.valueOf(topItems.get(0).getAppid()), appItemMap);
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
            // 获取数据
            List<ProductCatalog> items = productCatalogMapper.selectAll();
            List<ProductCatalog> topItemList = items.stream().filter(item -> 0 == item.getPid()).sorted()
                    .collect(Collectors.toList());
            // 组装子项目
            makeProductCatalogTree(topItemList, items);
            // 组装主键路由
            for (ProductCatalog data : topItemList) {
                data.makePath(new LinkedList<Integer>());
            }
            Map<String, Map<String, Object>> itemPathMap = new HashMap<String, Map<String, Object>>();
            topItemList.forEach(item -> {
                makePathMap(itemPathMap, item);
            });
            if (appCascadePathCodeMap.get(String.valueOf(items.get(0).getAppid())) == null) {
                appCascadePathCodeMap.put(String.valueOf(items.get(0).getAppid()),
                        new HashMap<String, Map<String, Map<String, Object>>>());
                appCascadePathCodeMap.get(String.valueOf(items.get(0).getAppid()))
                        .put(CodeTypeEnum.productCatalog.name(), itemPathMap);
            }
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
     * 刷新应用商品级联分类路径缓存
     */
    public void refreshProductCatalogPathCode() {
        appCascadePathCode.invalidateAll();
        this.loadAppCascadeCode();
    }

    /**
     * 组装字典名称与路径
     * 
     * @param codemap
     * @param code
     */
    private void makeCodeMap(Map<String, Map<String, Object>> codemap, CodeItem code) {
        Map<String, Object> codeMapItem = new HashMap<String, Object>();
        codeMapItem.put("name", code.getName());
        codeMapItem.put("path", code.getPath());
        code.setPath(null);
        codemap.put(String.valueOf(code.getId()), codeMapItem);
        if (code.getChildren() != null) {
            for (CodeItem item : code.getChildren()) {
                makeCodeMap(codemap, item);
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
    private void makeCodeTree(List<CodeItem> topItems, List<CodeItem> allItems) {
        for (CodeItem pitem : topItems) {
            // 收集下级子菜单
            List<CodeItem> child = allItems.stream().filter(item -> item.getPid().equals(pitem.getId())).sorted()
                    .collect(Collectors.toList());
            if (child.size() > 0) {
                // 子菜单列表排序
                pitem.setChildren(child);
                makeCodeTree(child, allItems);
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
     * 组装商品列表路径
     * 
     * @param pathMap
     * @param item
     */
    private void makePathMap(Map<String, Map<String, Object>> pathMap, ProductCatalog item) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("name", item.getName());
        dataMap.put("path", item.getPath());
        item.setPath(null);
        pathMap.put(String.valueOf(item.getId()), dataMap);
        if (item.getChildren() != null) {
            for (ProductCatalog subitem : item.getChildren()) {
                makePathMap(pathMap, subitem);
            }
        }
    }

    /**
     * 递归组装产品分类树
     * 
     * @param pItems
     *            父字典列表
     * @param allItems
     *            总字典列表
     */
    private void makeProductCatalogTree(List<ProductCatalog> pDataList, List<ProductCatalog> dataList) {
        for (ProductCatalog pitem : pDataList) {
            // 收集下级子菜单
            List<ProductCatalog> child = dataList.stream().filter(item -> item.getPid().equals(pitem.getId())).sorted()
                    .collect(Collectors.toList());
            if (child.size() > 0) {
                // 子菜单列表排序
                pitem.setChildren(child);
                makeProductCatalogTree(child, dataList);
            }
        }
    }

}
