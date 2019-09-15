package com.conichi.currency.converter.feignclient;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import static com.conichi.currency.converter.constant.URLS.CURRENCYLAYER_API;

/**
 * @author Aqib_Javed
 *
 *         feign client, to connect
 */
@FeignClient(name = CURRENCYLAYER_API)
@RibbonClient(name = CURRENCYLAYER_API)
public interface CurrencyLayerAPI {

}
