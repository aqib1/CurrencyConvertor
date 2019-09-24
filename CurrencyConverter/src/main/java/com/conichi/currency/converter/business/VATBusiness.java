package com.conichi.currency.converter.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conichi.currency.converter.constant.CCHelper;
import com.conichi.currency.converter.exceptions.InvalidRequestException;
import com.conichi.currency.converter.mapper.ResponseShortVatDetailMapper;
import com.conichi.currency.converter.service.VATService;
import com.example.model.ResponseShortVatDetailDto;
import com.example.model.ResponseVatDetailDto;

@Component
public class VATBusiness {

	private static final Logger logger = LoggerFactory.getLogger(VATBusiness.class);

	@Autowired
	private VATService vatService;

	@Autowired
	private ResponseShortVatDetailMapper mapper;

	@Autowired
	private VATCacheBusiness vatCacheBusiness;

	public ResponseVatDetailDto validateVatDetails(String vat) {
		if (CCHelper.isNullOrEmptyString(vat))
			throw new InvalidRequestException("vat id can not be null or empty");
		logger.info("checking vat details from cache against vat id [" + vat + "]");
		ResponseVatDetailDto responseVatDetailDto = vatCacheBusiness.validateVatDetails(vat);
		if (!CCHelper.isNull(responseVatDetailDto))
			return responseVatDetailDto;
		logger.info("Vat details not found in cache!, sending request to service");
		responseVatDetailDto = vatService.validateVat(vat);
		logger.info("Presisting response to cache responseVatDetailDto[" + responseVatDetailDto + "]");
		vatCacheBusiness.presist(responseVatDetailDto);
		return responseVatDetailDto;
	}

	public ResponseShortVatDetailDto validateVat(String vat) {
		if (CCHelper.isNullOrEmptyString(vat))
			throw new InvalidRequestException("vat id can not be null or empty");
		logger.info("sending request to vat service for vat id [" + vat + "]");
		ResponseShortVatDetailDto responseShortVatDetailDto = vatCacheBusiness.validateVat(vat);
		if (!CCHelper.isNull(responseShortVatDetailDto))
			return responseShortVatDetailDto;
		logger.info("Vat details not found in cache!, sending request to service");
		ResponseVatDetailDto responseVatDetailDto = vatService.validateVat(vat);
		vatCacheBusiness.presist(responseVatDetailDto);
		logger.info("Presisting response to cache responseVatDetailDto[" + responseVatDetailDto + "]");
		return mapper.responseVatDetailDtoToResponseShortVatDetailDto(responseVatDetailDto);
	}
}
