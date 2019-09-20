package com.conichi.currency.converter.service.Impl;

import static com.conichi.currency.converter.constant.URLS.CURRENCYCONV_API;

import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.conichi.currency.converter.constant.HelperConst;
import com.conichi.currency.converter.exceptions.BadInternalServerException;
import com.conichi.currency.converter.exceptions.InvalidResponseException;
import com.conichi.currency.converter.factory.FeignBuilderFactory;
import com.conichi.currency.converter.feignclient.CurrencyLayerAPI;
import com.conichi.currency.converter.service.CurrencyConverterService;
import com.example.model.CCRequestDto;
import com.example.model.ResponseConvertDto;
import feign.Logger;
import feign.slf4j.Slf4jLogger;

@Service
public class CurrencyConverterServiceImpl implements CurrencyConverterService {

	@Override
	public ResponseConvertDto currencyConvert(CCRequestDto request) {
		ResponseConvertDto response = null;
		try {
			CurrencyLayerAPI client = FeignBuilderFactory.getFeignBuilder()
					.logger(new Slf4jLogger(CurrencyLayerAPI.class)).logLevel(Logger.Level.FULL)
					.target(CurrencyLayerAPI.class, CURRENCYCONV_API);
			response = client.currencyConverter(request.getSourceCurrency(), request.getTargetCurrency());
			validateResponse(response);
			response.setResult(getResultValue(response, request));
			checkResultValidation(response);
		} catch (Exception e) {
			throw new BadInternalServerException("BadInternalServerException => " + e.toString(), e);
		}
		return response;
	}

	private void checkResultValidation(ResponseConvertDto response) {
		if (Objects.isNull(response.getResult())) {
			throw new InvalidResponseException("Conversion result not created, against API => " + CURRENCYCONV_API);
		}
	}

	private void validateResponse(ResponseConvertDto response) {
		if (Objects.isNull(response)) {
			throw new InvalidResponseException("\nNull Response recieved, against API => " + CURRENCYCONV_API);
		}
		if (response.getResults().isEmpty()) {
			throw new InvalidResponseException(
					"Results are empty, in response recieved against API => " + CURRENCYCONV_API);
		}
	}

	private BigDecimal getResultValue(ResponseConvertDto response, CCRequestDto request) {
		String key = response.getResults().entrySet().iterator().next().getKey();
		if (HelperConst.isNullOrEmptyString(key)) {
			throw new InvalidResponseException("Key in response not found!!");
		}
		long value = response.getResults().get(key).getVal().longValueExact() * request.getAmount();
		return new BigDecimal(value);
	}

}
