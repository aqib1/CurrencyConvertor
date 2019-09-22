package com.conichi.currency.converter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.conichi.currency.converter.entities.CurrencyConverterEntity;
import com.example.model.ResponseConvertDto;
import com.example.model.ResultValueDto;
import com.example.model.ResultsDto;

/**
 * @author Aqib_Javed
 * @version 1.0
 * @since 09/20/2019
 */
@Mapper(componentModel = "spring")
public interface CurrencyConverterEntityMapper {

	@Mapping(target = "result", source = "responseConvertDto.result")
	@Mapping(target = "query", source = "queryString")
	@Mapping(target = "countResult", source = "responseConvertDto.query.count")
	@Mapping(expression = "java((Double) responseConvertDto.getResults().get(queryString).getVal())", target = "conversionValue")
	@Mapping(expression = "java((String) responseConvertDto.getResults().get(queryString).getId())", target = "resultId")
	@Mapping(expression = "java((String) responseConvertDto.getResults().get(queryString).getTo())", target = "resultTo")
	@Mapping(expression = "java((String) responseConvertDto.getResults().get(queryString).getFr())", target = "resultFr")
	CurrencyConverterEntity currencyConverterEntityFromResponseConvertDto(ResponseConvertDto responseConvertDto,
			String queryString);

}
