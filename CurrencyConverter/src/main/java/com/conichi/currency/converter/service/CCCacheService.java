package com.conichi.currency.converter.service;

import com.conichi.currency.converter.entities.CurrencyConverterEntity;

/**
 * @author AQIB JAVED
 * @since 9/26/2019
 * @version 1.0
 */
public interface CCCacheService {

	/**
	 * @param entity
	 * @return
	 */
	CurrencyConverterEntity writeCache(CurrencyConverterEntity entity);

	/**
	 * @param query
	 * @return
	 */
	CurrencyConverterEntity read(String query);

	/**
	 * 
	 */
	void deleteAll();
	
	/**
	 * @return
	 */
	long count();
}
