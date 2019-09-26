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

	/**
	 * @param responseVatDetailDto
	 * @return
	 */
	VATValidatorEntity responseVatDetailDtoToVATValidatorEntity (ResponseVatDetailDto responseVatDetailDto);

	/**
	 * @param entity
	 * @return
	 */
	ResponseVatDetailDto vATValidatorEntityToResponseVatDetailDto (VATValidatorEntity entity);
	
	/**
	 * @param entity
	 * @return
	 */
	ResponseShortVatDetailDto vATValidatorEntityToResponseShortVatDetailDto (VATValidatorEntity entity);
}
