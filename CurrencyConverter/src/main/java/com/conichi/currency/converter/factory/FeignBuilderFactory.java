package com.conichi.currency.converter.factory;

import feign.Feign;
import feign.Feign.Builder;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;

public class FeignBuilderFactory {

	public static Builder getFeignBuilder() {
		return Feign.builder().client(new OkHttpClient()).encoder(new GsonEncoder())
				.decoder(new GsonDecoder());
	}
	private FeignBuilderFactory() {
		
	}
}
