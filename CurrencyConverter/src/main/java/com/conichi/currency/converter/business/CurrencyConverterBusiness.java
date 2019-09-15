package com.conichi.currency.converter.business;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.conichi.currency.converter.exceptions.InvalidRequestException;
import com.example.model.CCRequestDto;
import com.example.model.CCResponseDto;

/**
 * @author Aqib_Javed
 *
 */
@Component
public class CurrencyConverterBusiness {
	private static final Logger logger = LoggerFactory.getLogger(CurrencyConverterBusiness.class);

	public CCResponseDto currencyConvert(CCRequestDto requestDto) {
		checkCoreRequirements(requestDto);
		return null;
	}

	private void checkCoreRequirements(CCRequestDto requestDto) {
		logger.info("Checking core requirements of request[" + requestDto + "]");
		if (Objects.isNull(requestDto)) {
			throw new InvalidRequestException("Request is empty or null");
		}
		if (Objects.isNull(requestDto.getSourceCurrency()) || requestDto.getSourceCurrency().isEmpty()) {
			throw new InvalidRequestException("source-currency cannot be null or empty");
		}
		if (Objects.isNull(requestDto.getTargetCurrency()) || requestDto.getTargetCurrency().isEmpty()) {
			throw new InvalidRequestException("target-currency cannot be null or empty");
		}

	}
}
