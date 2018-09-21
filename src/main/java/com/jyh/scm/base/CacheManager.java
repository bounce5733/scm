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

import com.alibaba.fastjson.JSON;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.jyh.scm.dao.CodeItemMapper;
import com.jyh.scm.dao.CodeMapper;
import com.jyh.scm.entity.CodeItem;
import com.jyh.scm.entity.OptLog;
import com.jyh.scm.entity.code.Code;

@Configuration
public class CacheManager {

    private static final Logger log = LoggerFactory.getLogger(CacheManager.class);

    // 码表缓存{appid:<type:[codes]>}
    private static LoadingCache<String, Map<String, List<CodeItem>>> codeCache;

    // 码表缓存{appid:<id:{path:'',name:''}>}
    private static LoadingCache<String, Map<String, Map<String, Object>>> pathMapCodeCache;

    // 系统功能名称映射，如：{post_users:新增用户}
    private static LoadingCache<String, String> actionCache;

    // 日志缓存列表
    public static List<OptLog> logCacheList = Collections.synchronizedList(new ArrayList<OptLog>());

    // base64图片缓存
    private static LoadingCache<String, String> base64ImgCache;

    @Value("${custom.cache.base64ImgCache.expiredTime}")
    private int expiredTime;

    @Autowired
    private CodeMapper codeMapper;

    @Autowired
    private CodeItemMapper codeItemMapper;

    @PostConstruct
    public void init() {
        codeCache = CacheBuilder.newBuilder().build(new CacheLoader<String, Map<String, List<CodeItem>>>() {
            @Override
            public Map<String, List<CodeItem>> load(String key) throws Exception {
                return new HashMap<String, List<CodeItem>>();
            }
        });
        pathMapCodeCache = CacheBuilder.newBuilder().build(new CacheLoader<String, Map<String, Map<String, Object>>>() {
            @Override
            public Map<String, Map<String, Object>> load(String key) throws Exception {
                return new HashMap<String, Map<String, Object>>();
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
     * 加载系统码表缓存{type:[codes]}
     * 
     * @throws ExecutionException
     */
    @PostConstruct
    public Map<String, Map<String, List<CodeItem>>> loadCode() {
        ConcurrentMap<String, Map<String, List<CodeItem>>> cacheCodes = codeCache.asMap();
        if (cacheCodes == null || cacheCodes.isEmpty()) {
            log.info("加载码表{type:[codes]}缓存...");
            // 组装码表数结构
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
                    log.info(JSON.toJSONString(items));
                    List<CodeItem> topCodes = items.stream().filter(item -> 0 == item.getPid()).sorted()
                            .collect(Collectors.toList());
                    makeCodeTree(topCodes, items);

                    // 为各条目添加主键路径
                    for (CodeItem topCode : topCodes) {
                        topCode.makePath(new LinkedList<Integer>());
                    }
                    appCodeMap.put(code.getCode(), topCodes);
                }
                cacheCodes.put(appid, appCodeMap);
            }
        }
        return cacheCodes;
    }

    /**
     * 加载所有码表键值对缓存map（{id:{path:'',name:''}})
     * 
     */
    @PostConstruct
    public Map<String, Map<String, Map<String, Object>>> loadCodePathMap() {
        ConcurrentMap<String, Map<String, Map<String, Object>>> cacheCodePathMap = pathMapCodeCache.asMap();
        if (cacheCodePathMap == null || cacheCodePathMap.isEmpty()) {
            log.info("加载码表{id:{path:'',name:''}}映射缓存...");
            Map<String, List<CodeItem>> codeCache = new HashMap<String, List<CodeItem>>();
            // 组装码表数结构
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

                    // 为各项目添加主键路径
                    for (CodeItem topCode : topCodes) {
                        topCode.makePath(new LinkedList<Integer>());
                    }
                    codeCache.put(code.getCode(), topCodes);
                }
                // 组装码表主键映射map
                for (String key : codeCache.keySet()) {
                    for (CodeItem code : codeCache.get(key)) {
                        makeCodeMap(appCodeMap, code);
                    }
                }
                cacheCodePathMap.put(appid, appCodeMap);
            }
        }
        return cacheCodePathMap;

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
     * @return 系统功能map,如：{post_users:新增用户}
     */
    public static Map<String, String> getActionMap() {
        return actionCache.asMap();
    }

    /**
     * 组装编码名称与路径
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
     * 递归组装编码树
     * 
     * @param pItems
     *            父编码列表
     * @param allItems
     *            总编码列表
     */
    private void makeCodeTree(List<CodeItem> pItems, List<CodeItem> allItems) {
        for (CodeItem pItem : pItems) {
            // 收集下级子菜单
            List<CodeItem> child = allItems.stream().filter(item -> item.getPid().equals(pItem.getId())).sorted()
                    .collect(Collectors.toList());
            if (child.size() > 0) {
                // 子菜单列表排序
                pItem.setChildren(child);
                makeCodeTree(child, allItems);
            }
        }
    }

    public void refreshCode() {
        codeCache.invalidateAll();
        pathMapCodeCache.invalidateAll();
        this.loadCode();
        this.loadCodePathMap();
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

}
