package com.conichi.currency.converter.feignclient;

import static com.conichi.currency.converter.constant.URLS.CURRENCYCONV_API_CONVERT;
import static com.conichi.currency.converter.constant.URLS.ULTRA_COMPACT;
import java.util.HashMap;
import feign.Param;
import feign.RequestLine;

/**
 * @author Aqib_Javed
 *
 *         feign client, to connect
 */
public interface CurrencyLayerAPI {

	@RequestLine("GET " + CURRENCYCONV_API_CONVERT + "&q={from}_{to}" + ULTRA_COMPACT)
	HashMap<String,Double> currencyConverter(@Param("from") String from, @Param("to") String to);

}
