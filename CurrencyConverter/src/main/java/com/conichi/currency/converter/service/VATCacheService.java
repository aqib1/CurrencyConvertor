package com.conichi.currency.converter.service;

import com.conichi.currency.converter.entities.VATValidatorEntity;

/**
 * @author AQIB JAVED
 * @since 9/26/2019
 * @version 1.0
 */
public interface VATCacheService {

	/**
	 * @param entity
	 * @return
	 */
	VATValidatorEntity writeCache(VATValidatorEntity entity);

	/**
	 * @param query
	 * @return
	 */
	VATValidatorEntity read(String query);

	/**
	 * 
	 */
	void deleteAll();
	
	/**
	 * @return
	 */
	long count();
}
