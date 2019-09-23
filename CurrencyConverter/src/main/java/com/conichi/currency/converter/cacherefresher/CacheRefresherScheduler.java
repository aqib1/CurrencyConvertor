package com.conichi.currency.converter.cacherefresher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.conichi.currency.converter.exceptions.CacheRefresherException;
import com.conichi.currency.converter.service.CCCacheService;

@Component
public class CacheRefresherScheduler {

	private static final Logger logger = LoggerFactory.getLogger(CacheRefresherScheduler.class);

	@Autowired
	private CCCacheService cacheService;

	@Scheduled(fixedDelay = 86400000)
	public void cacheRefresher() {
		logger.info("cache refresher invoked!!");
		try {
			cacheService.deleteAll();
		} catch (Exception e) {
			throw new CacheRefresherException("Cache refresher failed => " + e.getMessage(), e);
		}

	}
}
