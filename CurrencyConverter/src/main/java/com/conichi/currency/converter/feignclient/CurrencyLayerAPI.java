package com.conichi.currency.converter.feignclient;

import static com.conichi.currency.converter.constant.URLS.ACCESS_KEY;
import static com.conichi.currency.converter.constant.URLS.ACCESS_KEY_VALUE;
import static com.conichi.currency.converter.constant.URLS.CURRENCYCONV_API_CONVERT;
import static com.conichi.currency.converter.constant.URLS.QUERY_KEY;
import static com.conichi.currency.converter.constant.URLS.ULTRA_COMPACT;
import com.example.model.ResponseConvertDto;

import feign.Param;
import feign.RequestLine;

/**
 * @author Aqib_Javed
 *
 *         feign client, to connect
 */
public interface CurrencyLayerAPI {

	@RequestLine("GET " + CURRENCYCONV_API_CONVERT + QUERY_KEY + "={from}_{to}"+ "&" + ACCESS_KEY + "="
			+ ACCESS_KEY_VALUE + "&" + ULTRA_COMPACT)
	ResponseConvertDto currencyConverter(@Param("from") String from, @Param("to") String to);

}
