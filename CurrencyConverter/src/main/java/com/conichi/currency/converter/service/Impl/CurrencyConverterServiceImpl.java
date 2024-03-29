package com.conichi.currency.converter.service.Impl;

import static com.conichi.currency.converter.constant.URLS.CURRENCYCONV_API;

import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.conichi.currency.converter.constant.DataHelper;
import com.conichi.currency.converter.exceptions.BadInternalServerException;
import com.conichi.currency.converter.exceptions.InvalidResponseException;
import com.conichi.currency.converter.factory.FeignBuilderFactory;
import com.conichi.currency.converter.feignclient.CurrencyLayerAPI;
import com.conichi.currency.converter.service.CurrencyConverterService;
import com.example.model.CCRequestDto;
import com.example.model.ResponseConvertDto;

import feign.Logger;
import feign.slf4j.Slf4jLogger;

/**
 * @author AQIB JAVED
 * @since 9/26/2019
 * @version 1.0
 */
@Service
public class CurrencyConverterServiceImpl implements CurrencyConverterService {
	/**
	 * 
	 */
	private static org.slf4j.Logger logger = org.slf4j.LoggerFactory
			.getLogger(CurrencyConverterServiceImpl.class.getName());

	@Override
	public ResponseConvertDto currencyConvert(CCRequestDto request) {
		ResponseConvertDto response = null;
		try {
			logger.debug("Sending request[" + request + "] to feign client");
			CurrencyLayerAPI client = FeignBuilderFactory.getFeignBuilder()
					.logger(new Slf4jLogger(CurrencyLayerAPI.class)).logLevel(Logger.Level.FULL)
					.target(CurrencyLayerAPI.class, CURRENCYCONV_API);
			response = client.currencyConverter(request.getSourceCurrency(), request.getTargetCurrency());
			logger.debug("response recieved [" + response + "]");
			validateResponse(response);
			logger.debug("calculate result from detailed recived");
			response.setResult(getResultValue(response, request));
			logger.debug("validate response after calculating results");
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
		response.setCreatedAt(LocalDateTime.now().toString());
	}

	private double getResultValue(ResponseConvertDto response, CCRequestDto request) {
		String key = response.getResults().entrySet().iterator().next().getKey();
		if (DataHelper.isNullOrEmptyString(key)) {
			throw new InvalidResponseException("Key in response not found!!");
		}
		return response.getResults().get(key).getVal() * request.getAmount();
	}

}
