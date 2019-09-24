package com.conichi.currency.converter.mapper;

import org.mapstruct.Mapper;

import com.conichi.currency.converter.entities.VATValidatorEntity;
import com.example.model.ResponseShortVatDetailDto;
import com.example.model.ResponseVatDetailDto;

/**
 * @author Aqib_Javed
 * @version 1.0
 * @since 09/24/2019
 */
@Mapper(componentModel = "spring")
public interface VATValidatorEntityMapper {

	VATValidatorEntity responseVatDetailDtoToVATValidatorEntity (ResponseVatDetailDto responseVatDetailDto);

	ResponseVatDetailDto vATValidatorEntityToResponseVatDetailDto (VATValidatorEntity entity);
	
	ResponseShortVatDetailDto vATValidatorEntityToResponseShortVatDetailDto (VATValidatorEntity entity);
}
