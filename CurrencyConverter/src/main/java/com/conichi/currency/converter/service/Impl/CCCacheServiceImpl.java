package com.conichi.currency.converter.service.Impl;

import org.springframework.stereotype.Service;

import com.conichi.currency.converter.entities.CurrencyConverterEntity;
import com.conichi.currency.converter.factory.cachefactory.CacheBuilder;
import com.conichi.currency.converter.factory.cachefactory.caches.CCCaches;
import com.conichi.currency.converter.service.CCCacheService;

@Service
public class CCCacheServiceImpl implements CCCacheService {

	private CCCaches ccCaches = CacheBuilder.getInstance().getCache();

	@Override
	public void writeCache(CurrencyConverterEntity entity) {
		ccCaches.presist(entity);
	}

	@Override
	public CurrencyConverterEntity read(String query) {
		return ccCaches.read(query);
	}

	@Override
	public void deleteAll() {
		ccCaches.deleteAll();
	}
}
