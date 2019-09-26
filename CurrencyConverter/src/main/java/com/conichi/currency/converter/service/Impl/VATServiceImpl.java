package com.conichi.currency.converter.service.Impl;

import static com.conichi.currency.converter.constant.URLS.VAT_API;

import org.springframework.stereotype.Service;

import com.conichi.currency.converter.constant.DataHelper;
import com.conichi.currency.converter.factory.FeignBuilderFactory;
import com.conichi.currency.converter.feignclient.VATValidatorAPI;
import com.conichi.currency.converter.service.VATService;
import com.example.model.ResponseVatDetailDto;

import feign.Logger;
import feign.slf4j.Slf4jLogger;

/**
 * @author AQIB JAVED
 * @since 9/26/2019
 * @version 1.0
 */
@Service
public class VATServiceImpl implements VATService {

	@Override
	public ResponseVatDetailDto validateVat(String vat) {
		VATValidatorAPI vatAPI = FeignBuilderFactory.getFeignBuilder().logger(new Slf4jLogger(VATValidatorAPI.class))
				.logLevel(Logger.Level.FULL).target(VATValidatorAPI.class, VAT_API);
		ResponseVatDetailDto response = vatAPI.validateVatId(vat);
		DataHelper.validateVatResponse(response);
		return response;
	}

}
