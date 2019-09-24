package com.conichi.currency.converter.service;

import com.example.model.ResponseVatDetailDto;

/**
 * @author AQIB JAVED
 *
 */
public interface VATService {

	ResponseVatDetailDto validateVat(String vat);
}
