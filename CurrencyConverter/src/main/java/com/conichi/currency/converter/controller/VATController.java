package com.conichi.currency.converter.controller;

import static com.conichi.currency.converter.constant.URLS.API_VAT_VALIDATION;
import static com.conichi.currency.converter.constant.URLS.API_VAT_VALIDATION_DETAILS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.conichi.currency.converter.business.VATBusiness;
import com.example.model.ResponseShortVatDetailDto;
import com.example.model.ResponseVatDetailDto;

/**
 * @author AQIB JAVED
 * @since 9/26/2019
 * @version 1.0
 */
@RestController
@RequestMapping(API_VAT_VALIDATION)
public class VATController {

	private static final Logger logger = LoggerFactory.getLogger(VATController.class);

	@Autowired
	private VATBusiness VATBusiness;

	/**
	 * @param vatId
	 * @return
	 */
	@GetMapping(API_VAT_VALIDATION_DETAILS)
	public ResponseEntity<ResponseVatDetailDto> validateVatDetailed(@RequestParam("vat") String vatId) {
		logger.info("Request recieved with parameter vatId [" + vatId + "]");
		ResponseVatDetailDto responseVatDetailDto = VATBusiness.validateVatDetails(vatId);
		logger.info("Response recieved responseVatDetailDto [" + responseVatDetailDto + "]");
		return ResponseEntity.ok().body(responseVatDetailDto);
	}

	/**
	 * @param vatId
	 * @return
	 */
	@GetMapping
	public ResponseEntity<ResponseShortVatDetailDto> validateVat(@RequestParam("vat") String vatId) {
		logger.info("Request recieved with parameter vatId [" + vatId + "]");
		ResponseShortVatDetailDto responseShortVatDetailDto = VATBusiness.validateVat(vatId);
		logger.info("Response recieved responseShortVatDetailDto [" + responseShortVatDetailDto + "]");
		return ResponseEntity.ok().body(responseShortVatDetailDto);
	}

}
