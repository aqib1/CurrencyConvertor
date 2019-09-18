package com.conichi.currency.converter.service;

import com.example.model.CCRequestDto;
import com.example.model.ResponseConvertDto;

public interface CurrencyConverterService {

	ResponseConvertDto currencyConvert(CCRequestDto request);
}
