package com.conichi.currency.converter.factory;

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

	/**
	 * @return
	 */
	public static Builder getFeignBuilder() {
		return Feign.builder().client(new OkHttpClient()).encoder(new GsonEncoder())
				.decoder(new GsonDecoder());
	}
	private FeignBuilderFactory() {
		
	}
}
