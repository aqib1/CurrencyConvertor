package com.conichi.currency.converter.service;

import com.example.model.ResponseVatDetailDto;

/**
 * @author AQIB JAVED
 * @since 9/26/2019
 * @version 1.0
 */
public interface VATService {

	/**
	 * @param vat
	 * @return
	 */
	ResponseVatDetailDto validateVat(String vat);
}
