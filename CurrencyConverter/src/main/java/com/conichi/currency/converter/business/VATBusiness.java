package com.conichi.currency.converter.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conichi.currency.converter.constant.CCHelper;
import com.conichi.currency.converter.exceptions.InvalidRequestException;
import com.conichi.currency.converter.service.VATService;
import com.example.model.ResponseShortVatDetailDto;
import com.example.model.ResponseVatDetailDto;

@Component
public class VATBusiness {

	private static final Logger logger = LoggerFactory.getLogger(VATBusiness.class);

	@Autowired
	private VATService vatService;

	public ResponseVatDetailDto validateVatDetails(String vat) {
		if (CCHelper.isNullOrEmptyString(vat))
			throw new InvalidRequestException("vat id can not be null or empty");
		ResponseVatDetailDto responseVatDetailDto = vatService.validateVat(vat);
		return responseVatDetailDto;
	}
	
	public ResponseShortVatDetailDto validateVat(String vat) {
		if (CCHelper.isNullOrEmptyString(vat))
			throw new InvalidRequestException("vat id can not be null or empty");
		ResponseVatDetailDto responseVatDetailDto = vatService.validateVat(vat);
		return null;
	}
}
