package com.conichi.currency.converter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.model.ResponseConvertDto;
import com.example.model.ResponseShortConvertDto;

/**
 * @author Aqib_Javed
 * @version 1.0
 * @since 09/20/2019
 */
@Mapper(componentModel = "spring")
public interface ResponseShortConvertMapper {

	@Mapping(target="result", source="result")
	ResponseShortConvertDto responseConvertDtoToResponseShortConvertDto(ResponseConvertDto responseConvertDto);
}
