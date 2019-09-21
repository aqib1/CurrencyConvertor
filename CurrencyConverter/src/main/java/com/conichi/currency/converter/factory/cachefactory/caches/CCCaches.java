package com.conichi.currency.converter.factory.cachefactory.caches;

import com.conichi.currency.converter.entities.CurrencyConverterEntity;

public interface CCCaches {
	
	CurrencyConverterEntity presist(CurrencyConverterEntity entity);

	CurrencyConverterEntity read(String query);

	CCCaches deleteAll();
}
