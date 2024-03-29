package com.conichi.currency.converter.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conichi.currency.converter.constant.DataHelper;
import com.conichi.currency.converter.exceptions.InvalidRequestException;
import com.conichi.currency.converter.mapper.ResponseShortVatDetailMapper;
import com.conichi.currency.converter.service.VATService;
import com.example.model.ResponseShortVatDetailDto;
import com.example.model.ResponseVatDetailDto;

/**
 * @author AQIB JAVED
 * @since 9/26/2019
 * @version 1.0
 */
@Component
public class VATBusiness {

	private static final Logger logger = LoggerFactory.getLogger(VATBusiness.class);

	@Autowired
	private VATService vatService;

	@Autowired
	private ResponseShortVatDetailMapper mapper;

	@Autowired
	private VATCacheBusiness vatCacheBusiness;

	/**
	 * @param vat
	 * @return
	 */
	public ResponseVatDetailDto validateVatDetails(String vat) {
		if (DataHelper.isNullOrEmptyString(vat))
			throw new InvalidRequestException("vat id can not be null or empty");
		logger.info("checking vat details from cache against vat id [" + vat + "]");
		ResponseVatDetailDto responseVatDetailDto = vatCacheBusiness.validateVatDetails(vat);
		if (!DataHelper.isNull(responseVatDetailDto))
			return responseVatDetailDto;
		logger.info("Vat details not found in cache!, sending request to service");
		responseVatDetailDto = vatService.validateVat(vat);
		logger.info("Presisting response to cache responseVatDetailDto[" + responseVatDetailDto + "]");
		vatCacheBusiness.presist(responseVatDetailDto);
		return responseVatDetailDto;
	}

	/**
	 * @param vat
	 * @return
	 */
	public ResponseShortVatDetailDto validateVat(String vat) {
		if (DataHelper.isNullOrEmptyString(vat))
			throw new InvalidRequestException("vat id can not be null or empty");
		logger.info("sending request to vat service for vat id [" + vat + "]");
		ResponseShortVatDetailDto responseShortVatDetailDto = vatCacheBusiness.validateVat(vat);
		if (!DataHelper.isNull(responseShortVatDetailDto))
			return responseShortVatDetailDto;
		logger.info("Vat details not found in cache!, sending request to service");
		ResponseVatDetailDto responseVatDetailDto = vatService.validateVat(vat);
		vatCacheBusiness.presist(responseVatDetailDto);
		logger.info("Presisting response to cache responseVatDetailDto[" + responseVatDetailDto + "]");
		return mapper.responseVatDetailDtoToResponseShortVatDetailDto(responseVatDetailDto);
	}
}
