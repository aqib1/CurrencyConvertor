package com.conichi.currency.converter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.model.ResponseShortVatDetailDto;
import com.example.model.ResponseVatDetailDto;

/**
 * @author Aqib_Javed
 * @version 1.0
 * @since 09/24/2019
 */
@Mapper(componentModel = "spring")
public interface ResponseShortVatDetailMapper {

	@Mapping(target = "countryCode", source = "countryCode")
	@Mapping(target = "validFormat", source = "validFormat")
	ResponseShortVatDetailDto responseVatDetailDtoToResponseShortVatDetailDto(
			ResponseVatDetailDto responseVatDetailDto);
}
