package com.conichi.currency.converter.cacherefresher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.conichi.currency.converter.exceptions.CacheRefresherException;
import com.conichi.currency.converter.service.CCCacheService;
import com.conichi.currency.converter.service.VATCacheService;

/**
 * @author AQIB JAVED
 * @since 9/26/2019
 * @version 1.0
 */
@Component
public class CacheRefresherScheduler {

	private static final Logger logger = LoggerFactory.getLogger(CacheRefresherScheduler.class);

	@Autowired
	private CCCacheService cacheService;

	@Autowired
	private VATCacheService vatCacheService;

	/**
	 * 
	 */
	@Scheduled(fixedDelay = 86400000)
	public void ccCacheRefresher() {
		logger.info("cache refresher invoked!!");
		try {
			cacheService.deleteAll();
		} catch (Exception e) {
			throw new CacheRefresherException("Cache refresher failed => " + e.getMessage(), e);
		}
	}

	/**
	 * 
	 */
	@Scheduled(fixedDelay = 129600000)
	public void vvCacheRefresher() {
		logger.info("cache refresher invoked!!");
		try {
			vatCacheService.deleteAll();
		} catch (Exception e) {
			throw new CacheRefresherException("Cache refresher failed => " + e.getMessage(), e);
		}
	}
}
