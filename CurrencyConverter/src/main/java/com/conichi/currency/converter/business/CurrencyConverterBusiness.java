package com.conichi.currency.converter.business;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conichi.currency.converter.constant.DataHelper;
import com.conichi.currency.converter.mapper.ResponseShortConvertMapper;
import com.conichi.currency.converter.service.CurrencyConverterService;
import com.example.model.CCRequestDto;
import com.example.model.ResponseConvertDto;
import com.example.model.ResponseShortConvertDto;

/**
 * @author Aqib_Javed
 * @since 9/26/2019
 * @version 1.0
 */
@Component
public class CurrencyConverterBusiness {
	private static final Logger logger = LoggerFactory.getLogger(CurrencyConverterBusiness.class);

	@Autowired
	private CurrencyConverterService currencyConverterService;

	@Autowired
	private CCCacheBusiness cCCacheBusiness;

	@Autowired
	private ResponseShortConvertMapper responseShortConvertMapper;

	/**
	 * @param requestDto
	 * @return
	 */
	public ResponseShortConvertDto currencyConvert(CCRequestDto requestDto) {
		logger.info("checking core requirements for requestDto => " + requestDto);
		DataHelper.checkCoreRequirementsForCCRequestDto(requestDto);
		logger.info("checking cache if contains details for => " + requestDto);
		ResponseShortConvertDto response = cCCacheBusiness.convertCurrency(requestDto);
		if (!Objects.isNull(response))
			return response;
		logger.info("requestDto is not exits in cache, sending request to service");
		ResponseConvertDto responseConvertDto = currencyConverterService.currencyConvert(requestDto);
		logger.info("presisting resposne to cache => " + responseConvertDto);
		cCCacheBusiness.presist(requestDto.getSourceCurrency() + DataHelper.UNDER_SCORE + requestDto.getTargetCurrency(),
				responseConvertDto);
		return responseShortConvertMapper.responseConvertDtoToResponseShortConvertDto(responseConvertDto);
	}

	/**
	 * @param requestDto
	 * @return
	 */
	public ResponseConvertDto currencyConvertDetailed(CCRequestDto requestDto) {
		logger.info("checking core requirements for requestDto " + requestDto);
		DataHelper.checkCoreRequirementsForCCRequestDto(requestDto);
		logger.info("checking cache if contains details for => " + requestDto);
		ResponseConvertDto responseConvertDto = cCCacheBusiness.convertCurrencyDetailed(requestDto);
		if (!Objects.isNull(responseConvertDto))
			return responseConvertDto;
		logger.info("requestDto is not exits in cache, sending request to service");
		ResponseConvertDto response = currencyConverterService.currencyConvert(requestDto);
		logger.info("presisting resposne to cache => " + responseConvertDto);
		cCCacheBusiness.presist(requestDto.getSourceCurrency() + DataHelper.UNDER_SCORE + requestDto.getTargetCurrency(),
				response);
		return response;
	}

}
