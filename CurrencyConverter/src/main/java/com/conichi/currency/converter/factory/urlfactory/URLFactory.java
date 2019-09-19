package com.conichi.currency.converter.factory.urlfactory;

import com.conichi.currency.converter.constant.URLS;
import com.example.model.URLTypes;

public class URLFactory {

	public String getURL(URLTypes urlType) {
		String url = URLS.CURRENCYCONV_API;
		switch (urlType) {
		case CONVERT:
			url += URLS.CURRENCYCONV_API_CONVERT;
			break;

		default:
		}

		url += "?" + URLS.ACCESS_KEY + "=" + URLS.ACCESS_KEY_VALUE;
		return url;

	}
}
