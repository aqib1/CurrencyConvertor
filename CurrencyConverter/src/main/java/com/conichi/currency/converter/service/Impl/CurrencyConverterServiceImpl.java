package com.conichi.currency.converter.service.Impl;

import static com.conichi.currency.converter.constant.URLS.CURRENCYCONV_API;

import org.springframework.stereotype.Service;

import com.conichi.currency.converter.feignclient.CurrencyLayerAPI;
import com.conichi.currency.converter.service.CurrencyConverterService;
import com.example.model.CCRequestDto;
import com.example.model.ResponseConvertDto;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

@Service
public class CurrencyConverterServiceImpl implements CurrencyConverterService {

	@Override
	public ResponseConvertDto currencyConvert(CCRequestDto request) {
		CurrencyLayerAPI client = Feign.builder().client(new OkHttpClient()).encoder(new GsonEncoder())
				.decoder(new GsonDecoder()).logger(new Slf4jLogger(CurrencyLayerAPI.class)).logLevel(Logger.Level.FULL)
				.target(CurrencyLayerAPI.class, CURRENCYCONV_API);
		
		ResponseConvertDto jsonObject= client.currencyConverter(request.getSourceCurrency(), request.getTargetCurrency());
		
		return null;
	}

}
