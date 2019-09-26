package com.conichi.currency.converter.feignclient;

import static com.conichi.currency.converter.constant.URLS.CC_ACCESS_KEY;
import static com.conichi.currency.converter.constant.URLS.CC_ACCESS_KEY_VALUE;
import static com.conichi.currency.converter.constant.URLS.CURRENCYCONV_API_CONVERT;
import static com.conichi.currency.converter.constant.URLS.QUERY_KEY;
import static com.conichi.currency.converter.constant.URLS.QUESTION_MARK;
import static com.conichi.currency.converter.constant.URLS.ULTRA_COMPACT;

import com.example.model.ResponseConvertDto;

import feign.Param;
import feign.RequestLine;

/**
 * @author Aqib_Javed
 * @since 9/26/2019
 * @version 1.0
 *         feign client, to connect
 */
public interface CurrencyLayerAPI {

	/**
	 * @param from
	 * @param to
	 * @return
	 */
	@RequestLine("GET " + CURRENCYCONV_API_CONVERT + QUESTION_MARK + QUERY_KEY + "={from}_{to}" + "&" + CC_ACCESS_KEY
			+ "=" + CC_ACCESS_KEY_VALUE + "&" + ULTRA_COMPACT)
	ResponseConvertDto currencyConverter(@Param("from") String from, @Param("to") String to);

}
