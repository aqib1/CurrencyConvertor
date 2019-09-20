package com.conichi.currency.converter.controller;

import static com.conichi.currency.converter.constant.URLS.API_CURRENCY_CONVERT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.conichi.currency.converter.business.CurrencyConverterBusiness;
import com.example.model.CCRequestDto;
import com.example.model.ResponseConvertDto;

/**
 * @author Aqib_Javed
 *
 */
@RestController
@RequestMapping(API_CURRENCY_CONVERT)
public class CurrencyConverterController {

	private static final Logger logger = LoggerFactory.getLogger(CurrencyConverterController.class);

	@Autowired
	private CurrencyConverterBusiness currencyConverterBusiness;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ResponseConvertDto> currencyConvert(@RequestBody CCRequestDto requestDto) {
		ResponseConvertDto response = currencyConverterBusiness.currencyConvert(requestDto);
		return ResponseEntity.ok().body(response);
	}
}
