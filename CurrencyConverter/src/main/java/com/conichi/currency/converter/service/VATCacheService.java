package com.conichi.currency.converter.service;

import com.conichi.currency.converter.entities.VATValidatorEntity;

public interface VATCacheService {

	void writeCache(VATValidatorEntity entity);

	VATValidatorEntity read(String query);

	void deleteAll();
}
