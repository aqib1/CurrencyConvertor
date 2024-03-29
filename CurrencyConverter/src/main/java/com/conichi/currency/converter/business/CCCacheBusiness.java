package com.conichi.currency.converter.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conichi.currency.converter.constant.DataHelper;
import com.conichi.currency.converter.entities.CurrencyConverterEntity;
import com.conichi.currency.converter.mapper.CurrencyConverterEntityMapper;
import com.conichi.currency.converter.mapper.ResponseConvertDtoMapper;
import com.conichi.currency.converter.mapper.ResponseShortConvertMapper;
import com.conichi.currency.converter.service.CCCacheService;
import com.example.model.CCRequestDto;
import com.example.model.ResponseConvertDto;
import com.example.model.ResponseShortConvertDto;

/**
 * @author AQIB JAVED
 * @since 9/26/2019
 * @version 1.0
 */
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

	/**
	 * @param requestDto
	 * @return
	 */
	public ResponseShortConvertDto convertCurrency(CCRequestDto requestDto) {
		DataHelper.checkCoreRequirementsForCCRequestDto(requestDto);
		String query = requestDto.getSourceCurrency() + DataHelper.UNDER_SCORE + requestDto.getTargetCurrency();
		logger.info("Query created for lookup => " + query);
		return responseShortMapper.currencyConverterEntityToResponseShortDto(cacheService.read(query));
	}

	/**
	 * @param requestDto
	 * @return
	 */
	public ResponseConvertDto convertCurrencyDetailed(CCRequestDto requestDto) {
		DataHelper.checkCoreRequirementsForCCRequestDto(requestDto);
		String query = requestDto.getSourceCurrency() + DataHelper.UNDER_SCORE + requestDto.getTargetCurrency();
		logger.info("Query created for lookup => " + query);
		return responseConvertDtoMapper.responseConvertDtoFromCurrencyConverterEntity(cacheService.read(query));
	}

	/**
	 * @param query
	 * @param response
	 */
	public void presist(String query, ResponseConvertDto response) {
		logger.info("convert reposne to entity object");
		CurrencyConverterEntity entity = responseConverDtoMapper.currencyConverterEntityFromResponseConvertDto(response,
				query);
		entity.setInsertedDate(response.getCreatedAt());
		logger.info("Writing curency entity to cache => " + entity);
		cacheService.writeCache(entity);
	}

}
