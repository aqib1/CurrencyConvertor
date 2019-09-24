package com.conichi.currency.converter.feignclient;

import static com.conichi.currency.converter.constant.URLS.QUESTION_MARK;
import static com.conichi.currency.converter.constant.URLS.VAT_ID_KEY;
import static com.conichi.currency.converter.constant.URLS.VAT_VALIDATE_API;
import static com.conichi.currency.converter.constant.URLS.VV_ACCESS_KEY;
import static com.conichi.currency.converter.constant.URLS.VV_ACCESS_KEY_VALUE;

import com.example.model.ResponseVatDetailDto;

import feign.Param;
import feign.RequestLine;

public interface VATValidatorAPI {

	@RequestLine("GET " + VAT_VALIDATE_API + QUESTION_MARK + VV_ACCESS_KEY + "=" + VV_ACCESS_KEY_VALUE + "&" + VAT_ID_KEY
			+ "={vat}")
	ResponseVatDetailDto validateVatId(@Param("vat") String vat);
}
