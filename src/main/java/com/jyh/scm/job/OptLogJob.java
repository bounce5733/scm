package com.jyh.scm.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jyh.scm.base.CacheManager;
import com.jyh.scm.dao.OptLogMapper;

/**
 * 功能日志任务
 * 
 * @author jiangyonghua
 * @date 2018年8月30日 下午8:27:27
 */
@Component
@EnableScheduling
public class OptLogJob {

	@Autowired
	private OptLogMapper mapper;

	@Scheduled(cron = "* 0/2 * * * ? ")
	public void handle() {
		if (CacheManager.logCacheList.size() > 0) {
			mapper.insertList(CacheManager.logCacheList);
			CacheManager.logCacheList.clear();
		}
	}
}
