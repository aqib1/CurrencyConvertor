package com.conichi.currency.converter.factory.cachefactory;

import com.conichi.currency.converter.factory.cachefactory.caches.CCCaches;
import com.conichi.currency.converter.factory.cachefactory.types.CurrencyConverterCache;

public class CacheBuilder {

	private CacheBuilder() {

	}
	
	public CCCaches getCache() {
		CCCaches cc = CurrencyConverterCache.getInstance();
		
		
		return cc;
	}

	/**
	 * @author Aqib_Javed
	 * @version 1.0
	 * @since 9/21/2019
	 * <p>Initialization on demand pattern</p>
	 */
	private static class InstanceHolder {
		private static final CacheBuilder INSTANCE = new CacheBuilder();

		private InstanceHolder() {

		}
	}

	public static CacheBuilder getInstance() {
		return InstanceHolder.INSTANCE;
	}
}
