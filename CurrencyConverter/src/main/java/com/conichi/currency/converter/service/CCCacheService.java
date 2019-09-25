package com.conichi.currency.converter.service;

import com.conichi.currency.converter.entities.CurrencyConverterEntity;

/**
 * @author AQIB JAVED
 *
 */
public interface CCCacheService {

	CurrencyConverterEntity writeCache(CurrencyConverterEntity entity);

	CurrencyConverterEntity read(String query);

	void deleteAll();
	
	long count();
}
