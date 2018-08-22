package com.jyh.scm.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.jyh.scm.dao.CodeItemMapper;
import com.jyh.scm.dao.CodeMapper;
import com.jyh.scm.entity.Code;
import com.jyh.scm.entity.CodeItem;

@Configuration
public class CacheManager {

	private static final Logger log = LoggerFactory.getLogger(CacheManager.class);

	// 码表缓存{type:[codes]}
	private static LoadingCache<String, List<CodeItem>> codeCache;

	// 码表缓存{id:{path:'',name:''}}
	private static LoadingCache<String, Map<String, Object>> pathMapCodeCache;

	@Autowired
	private CodeMapper codeMapper;

	@Autowired
	private CodeItemMapper codeItemMapper;

	@PostConstruct
	public void init() {
		codeCache = CacheBuilder.newBuilder().build(new CacheLoader<String, List<CodeItem>>() {
			@Override
			public List<CodeItem> load(String key) throws Exception {
				return new ArrayList<CodeItem>();
			}
		});
		pathMapCodeCache = CacheBuilder.newBuilder().build(new CacheLoader<String, Map<String, Object>>() {
			@Override
			public Map<String, Object> load(String key) throws Exception {
				return new HashMap<String, Object>();
			}
		});
	}

	/**
	 * 加载系统码表缓存{type:[codes]}
	 * 
	 * @throws ExecutionException
	 */
	@PostConstruct
	public Map<String, List<CodeItem>> loadCode() {
		ConcurrentMap<String, List<CodeItem>> cacheCodes = codeCache.asMap();
		if (cacheCodes == null || cacheCodes.isEmpty()) {
			log.info("加载码表{type:[codes]}缓存...");
			// 组装码表数结构
			List<Code> codes = codeMapper.selectAll();
			for (Code code : codes) {
				CodeItem param = new CodeItem();
				param.setType(code.getCode());
				List<CodeItem> items = codeItemMapper.select(param);
				log.info(JSON.toJSONString(items));
				List<CodeItem> topCodes = items.stream().filter(item -> "0".equals(item.getPid())).sorted()
						.collect(Collectors.toList());

				makeCodeTree(topCodes, items);

				// 为各条目添加主键路径
				for (CodeItem topCode : topCodes) {
					topCode.makePath(new LinkedList<String>());
				}
				cacheCodes.put(String.valueOf(code.getCode()), topCodes);
			}
		}
		return cacheCodes;
	}

	/**
	 * 加载所有码表键值对缓存map（{id:{path:'',name:''}})
	 * 
	 */
	@PostConstruct
	public Map<String, Map<String, Object>> loadCodePathMap() {
		ConcurrentMap<String, Map<String, Object>> cacheCodePathMap = pathMapCodeCache.asMap();
		if (cacheCodePathMap == null || cacheCodePathMap.isEmpty()) {
			log.info("加载码表{id:{path:'',name:''}}映射缓存...");
			Map<String, List<CodeItem>> codeCache = new HashMap<String, List<CodeItem>>();
			// 组装码表数结构
			List<Code> codes = codeMapper.selectAll();
			for (Code code : codes) {
				CodeItem param = new CodeItem();
				param.setType(code.getCode());
				List<CodeItem> items = codeItemMapper.select(param);

				List<CodeItem> topCodes = items.stream().filter(item -> "0".equals(item.getPid())).sorted()
						.collect(Collectors.toList());

				makeCodeTree(topCodes, items);

				// 为各项目添加主键路径
				for (CodeItem topCode : topCodes) {
					topCode.makePath(new LinkedList<String>());
				}

				codeCache.put(code.getCode(), topCodes);
			}

			// 组装码表主键映射map
			for (String key : codeCache.keySet()) {
				for (CodeItem code : codeCache.get(key)) {
					makeCodeMap(cacheCodePathMap, code);
				}
			}
		}
		return cacheCodePathMap;

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
		codemap.put(code.getId(), codeMapItem);
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

}
