package com.conichi.currency.converter.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import feign.Feign;
import feign.Feign.Builder;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;

/**
 * @author AQIB JAVED
 * @since 9/26/2019
 * @version 1.0
 */
public class FeignBuilderFactory {
	private static final Logger logger = LoggerFactory.getLogger(FeignBuilderFactory.class);

	/**
	 * @return
	 */
	public static Builder getFeignBuilder() {
		logger.info("FeignBuilder called inside [" + FeignBuilderFactory.class.getName() + "] class");
		return Feign.builder().client(new OkHttpClient()).encoder(new GsonEncoder()).decoder(new GsonDecoder());
	}

	private FeignBuilderFactory() {

	}
}
