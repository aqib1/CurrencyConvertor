package com.conichi.currency.converter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

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
public interface ResponseConvertDtoMapper {
	/**
	 * @param entity
	 * @return
	 */
	@Mapping(source = "entity.result", target = "result")
	@Mapping(source = "entity.countResult", target = "query.count")
	@Mapping(target = "results", qualifiedByName = "getResults", source = "entity")
	@Mapping(target = "createdAt", source = "insertedDate")
	ResponseConvertDto responseConvertDtoFromCurrencyConverterEntity(CurrencyConverterEntity entity);

	/**
	 * @param entity
	 * @return
	 */
	@Named("getResults")
	default ResultsDto getResults(CurrencyConverterEntity entity) {
		ResultsDto resultsDto = new ResultsDto();
		ResultValueDto resultValueDto = new ResultValueDto();
		resultValueDto.val(entity.getConversionValue()).fr(entity.getResultFr()).to(entity.getResultTo())
				.id(entity.getResultId());
		resultsDto.put(entity.getQuery(), resultValueDto);
		return resultsDto;
	}
}
