package com.conichi.currency.converter.controller;

import static com.conichi.currency.converter.constant.URLS.API_CURRENCY_CONVERT;
import static com.conichi.currency.converter.constant.URLS.API_CURRENCY_CONVERT_DETAILS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.conichi.currency.converter.business.CurrencyConverterBusiness;
import com.conichi.currency.converter.constant.DataHelper;
import com.conichi.currency.converter.exceptions.InvalidRequestException;
import com.example.model.CCRequestDto;
import com.example.model.ResponseConvertDto;
import com.example.model.ResponseShortConvertDto;

/**
 * @author Aqib_Javed
 * @version 1.0
 * @since 09/15/2019
 */
@RestController
@RequestMapping(API_CURRENCY_CONVERT)
public class CurrencyConverterController {

	private static final Logger logger = LoggerFactory.getLogger(CurrencyConverterController.class);

	@Autowired
	private CurrencyConverterBusiness currencyConverterBusiness;

	/**
	 * @param sourceCurrency
	 * @param targetCurrency
	 * @param amount
	 * @return
	 *         <p>
	 *         /api/currency/convert
	 *         </p>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<ResponseShortConvertDto> currencyConvert(@RequestParam("source") String sourceCurrency,
			@RequestParam("target") String targetCurrency, @RequestParam("amount") Integer amount) {
		ResponseShortConvertDto response = currencyConverterBusiness
				.currencyConvert(getCCRequestDto(sourceCurrency, targetCurrency, amount));
		logger.info("Response recieved " + response + " recieved by [/api/currency/convert]");
		return ResponseEntity.ok().body(response);
	}

	/**
	 * @param sourceCurrency
	 * @param targetCurrency
	 * @param amount
	 * @return
	 */
	@RequestMapping(value = API_CURRENCY_CONVERT_DETAILS, method = RequestMethod.GET)
	public ResponseEntity<ResponseConvertDto> currencyConvertDetails(@RequestParam("source") String sourceCurrency,
			@RequestParam("target") String targetCurrency, @RequestParam("amount") Integer amount) {
		ResponseConvertDto response = currencyConverterBusiness
				.currencyConvertDetailed(getCCRequestDto(sourceCurrency, targetCurrency, amount));
		logger.info("Response recieved " + response + " recieved by [/api/currency/convert]");
		return ResponseEntity.ok().body(response);
	}

	/**
	 * @param sourceCurrency
	 * @param targetCurrency
	 * @param amount
	 * @return
	 */
	private CCRequestDto getCCRequestDto(String sourceCurrency, String targetCurrency, Integer amount) {
		logger.info("checking if source, target and amout is valid");
		if (DataHelper.isNullOrEmptyString(sourceCurrency) || DataHelper.isNullOrEmptyString(targetCurrency)
				|| DataHelper.isNull(amount)) {
			throw new InvalidRequestException("Invalid request, parameters are not available.");
		}
		logger.info("creating ccrequest object from source, target and amount details");
		CCRequestDto requestDto = new CCRequestDto();
		requestDto.setSourceCurrency(sourceCurrency);
		requestDto.setTargetCurrency(targetCurrency);
		requestDto.setAmount(amount);
		return requestDto;
	}
}
