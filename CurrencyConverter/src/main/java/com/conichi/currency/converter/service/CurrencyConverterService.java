package com.conichi.currency.converter.service;

import com.example.model.CCRequestDto;
import com.example.model.ResponseConvertDto;

/**
 * @author AQIB JAVED
 * @since 9/26/2019
 * @version 1.0
 */
public interface CurrencyConverterService {

	/**
	 * @param request
	 * @return
	 */
	ResponseConvertDto currencyConvert(CCRequestDto request);
}
