package com.conichi.currency.converter.constant;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.conichi.currency.converter.exceptions.InvalidRequestException;
import com.example.model.CCRequestDto;

public final class CCHelper {
	private static final Logger logger = LoggerFactory.getLogger(CCHelper.class);
	public static final String COMPONENT_SCAN_PATH = "com.conichi.currency.converter";
	public static final String COMPONENT_SCAN_PATH_REPOSITORY = "com.conichi.currency.converter.repository";
	public static final String UNDER_SCORE = "_";
	public static boolean isNullOrEmptyString(String string) {
		return Objects.isNull(string) || string.isEmpty();
	}

	public static boolean isNull(Object object) {
		return Objects.isNull(object);
	}

	public static void checkCoreRequirements(CCRequestDto requestDto) {
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

	private CCHelper() {

	}
}