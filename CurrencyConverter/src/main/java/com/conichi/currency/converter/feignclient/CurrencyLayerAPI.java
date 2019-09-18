package com.conichi.currency.converter.feignclient;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.model.ResponseConvertDto;
import static com.conichi.currency.converter.constant.URLS.*;

/**
 * @author Aqib_Javed
 *
 *         feign client, to connect
 */
@FeignClient(name = CURRENCYLAYER_API)
@RibbonClient(name = CURRENCYLAYER_API)
public interface CurrencyLayerAPI {

	@RequestMapping(CURRENCYLAYER_API_CONVERT + "&from={from}" + "&to={to}" + "&amount={amount}")
	ResponseConvertDto currencyConverter(@PathVariable("from") String from, @PathVariable("to") String to,
			@PathVariable("amount") Integer amount);

}
