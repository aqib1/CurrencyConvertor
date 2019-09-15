package com.conichi.currency.converter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.model.CCRequestDto;
import com.example.model.CCResponseDto;

/**
 * @author Aqib_Javed
 *
 */
@RestController
public class CurrencyConverterController {

	private static final Logger logger = LoggerFactory.getLogger(CurrencyConverterController.class);

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CCResponseDto> currencyConvert(@RequestBody CCRequestDto requestDto) {
		CCResponseDto response = null;
		return ResponseEntity.ok().body(response);
	}
}
