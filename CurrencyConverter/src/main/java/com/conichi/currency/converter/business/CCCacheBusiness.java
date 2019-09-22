package com.conichi.currency.converter.business;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conichi.currency.converter.constant.CCHelper;
import com.conichi.currency.converter.entities.CurrencyConverterEntity;
import com.conichi.currency.converter.mapper.CurrencyConverterEntityMapper;
import com.conichi.currency.converter.mapper.ResponseConvertDtoMapper;
import com.conichi.currency.converter.mapper.ResponseShortConvertMapper;
import com.conichi.currency.converter.service.CCCacheService;
import com.example.model.CCRequestDto;
import com.example.model.ResponseConvertDto;
import com.example.model.ResponseShortConvertDto;

@Component
public class CCCacheBusiness {
	private static final Logger logger = LoggerFactory.getLogger(CCCacheBusiness.class);

	@Autowired
	private CCCacheService cacheService;

	@Autowired
	private ResponseShortConvertMapper responseShortMapper;

	@Autowired
	private CurrencyConverterEntityMapper responseConverDtoMapper;

	@Autowired
	private ResponseConvertDtoMapper responseConvertDtoMapper;

	public ResponseShortConvertDto convertCurrency(CCRequestDto requestDto) {
		CCHelper.checkCoreRequirements(requestDto);
		String query = requestDto.getSourceCurrency() + "_" + requestDto.getTargetCurrency();
		logger.info("Query created for lookup => " + query);
		return responseShortMapper.currencyConverterEntityToResponseShortDto(cacheService.read(query));
	}

	public ResponseConvertDto convertCurrencyDetailed(CCRequestDto requestDto) {
		CCHelper.checkCoreRequirements(requestDto);
		String query = requestDto.getSourceCurrency() + "_" + requestDto.getTargetCurrency();
		logger.info("Query created for lookup => " + query);
		return responseConvertDtoMapper.responseConvertDtoFromCurrencyConverterEntity(cacheService.read(query));
	}

	public void presist(String query, ResponseConvertDto response) {
		logger.info("convert reposne to entity object");
		CurrencyConverterEntity entity = responseConverDtoMapper.currencyConverterEntityFromResponseConvertDto(response,
				query);
		entity.setInsertedDate(LocalDateTime.now().toString());
		logger.info("Writing curency entity to cache => " + entity);
		cacheService.writeCache(entity);
	}

}
