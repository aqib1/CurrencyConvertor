package com.conichi.currency.converter.constant;

import static com.conichi.currency.converter.constant.URLS.VAT_API;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.conichi.currency.converter.exceptions.InvalidRequestException;
import com.conichi.currency.converter.exceptions.InvalidResponseException;
import com.example.model.CCRequestDto;
import com.example.model.ResponseVatDetailDto;

/**
 * @author AQIB JAVED
 *
 */
public final class DataHelper {
	private static final Logger logger = LoggerFactory.getLogger(DataHelper.class);
	public static final String COMPONENT_SCAN_PATH = "com.conichi.currency.converter";
	public static final String COMPONENT_SCAN_PATH_REPOSITORY = "com.conichi.currency.converter.repository";
	public static final String UNDER_SCORE = "_";
	public static final String CACHE_REFRESHER_VALUE = "cahce.refresher.scheduler";

	public static boolean isNullOrEmptyString(String string) {
		return Objects.isNull(string) || string.isEmpty();
	}

	public static boolean isNull(Object object) {
		return Objects.isNull(object);
	}

	public static void checkCoreRequirementsForCCRequestDto(CCRequestDto requestDto) {
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

	public static void validateVatResponse(ResponseVatDetailDto response) {
		if (DataHelper.isNull(response) || DataHelper.isNullOrEmptyString(response.getCompanyAddress())
				|| DataHelper.isNullOrEmptyString(response.getCompanyName())
				|| DataHelper.isNullOrEmptyString(response.getCountryCode())
				|| DataHelper.isNullOrEmptyString(response.getQuery()) || DataHelper.isNull(response.getValidFormat())
				|| DataHelper.isNull(response.getVatNumber()) || DataHelper.isNull(response.getValid())
				|| DataHelper.isNull(response.getSuccess()))
			throw new InvalidResponseException("\nNull Response recieved, against API => " + VAT_API);
	}

	private DataHelper() {

	}
}
