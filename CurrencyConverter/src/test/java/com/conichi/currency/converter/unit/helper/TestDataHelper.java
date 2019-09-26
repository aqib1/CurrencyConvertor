package com.conichi.currency.converter.unit.helper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import com.conichi.currency.converter.entities.CurrencyConverterEntity;
import com.conichi.currency.converter.entities.VATValidatorEntity;
import com.conichi.currency.converter.exceptions.BadInternalServerException;
import com.conichi.currency.converter.exceptions.CachePresistException;
import com.conichi.currency.converter.exceptions.CacheRefresherException;
import com.conichi.currency.converter.exceptions.InvalidParamException;
import com.conichi.currency.converter.exceptions.InvalidRequestException;
import com.conichi.currency.converter.exceptions.InvalidResponseException;
import com.example.model.CCRequestDto;
import com.example.model.QueryDto;
import com.example.model.ResponseConvertDto;
import com.example.model.ResponseError;
import com.example.model.ResponseShortConvertDto;
import com.example.model.ResponseShortVatDetailDto;
import com.example.model.ResponseVatDetailDto;
import com.example.model.ResultValueDto;
import com.example.model.ResultsDto;

public class TestDataHelper {

	public static final String CURRENCY_API_QUERY_FOR_TEST = "?source=PKR&target=USD&amount=100";
	public static final WebRequest TEST_WEB_REQUEST = null;
	public static final RuntimeException TEST_RUNTIME_EXC = new RuntimeException();
	public static final String QUERY = "USD_PKR";
	public static final String SOURCE_CURR = "US";
	public static final String TAR_CURR = "PKR";
	public static final int CONVR_AMNT = 3;
	public static final String VAT_ID = "12CVAERT";
	public static final String CACHE_REFRESHER_VALUE = "cahce.refresher.scheduler";

	/**
	 * @return
	 */
	public static ResponseEntity<ResponseShortVatDetailDto> getEntityResponseShortVatDto() {
		return ResponseEntity.ok().body(new ResponseShortVatDetailDto().countryCode("US").validFormat(true));
	}

	/**
	 * @return
	 */
	public static ResponseShortVatDetailDto getResponseShortVatDto() {
		return new ResponseShortVatDetailDto().countryCode("US").validFormat(true);
	}

	/**
	 * @return
	 */
	public static ResponseEntity<ResponseVatDetailDto> getEntityResponseVatDto() {
		return ResponseEntity.ok()
				.body(new ResponseVatDetailDto().companyAddress("US Cherm 34, Timiler, 11 mm").companyName("Timiler")
						.countryCode("US").query("12CVAERT").success(true).valid(true).validFormat(true)
						.vatNumber("1TVAT1236"));
	}

	/**
	 * @return
	 */
	public static ResponseVatDetailDto getResponseVatDto() {
		return new ResponseVatDetailDto().companyAddress("US Cherm 34, Timiler, 11 mm").companyName("Timiler")
				.countryCode("US").query("12CVAERT").success(true).valid(true).validFormat(true).vatNumber("1TVAT1236");
	}

	/**
	 * @return
	 */
	public static ResponseVatDetailDto getResponseVatDtoWithoutVatNumber() {
		return new ResponseVatDetailDto().companyAddress("US Cherm 34, Timiler, 11 mm").companyName("Timiler")
				.countryCode("US").query("12CVAERT").success(true).valid(true).validFormat(true);
	}

	/**
	 * @return
	 */
	public static ResponseVatDetailDto getResponseVatDtoWithoutValidFormat() {
		return new ResponseVatDetailDto().companyAddress("US Cherm 34, Timiler, 11 mm").companyName("Timiler")
				.countryCode("US").query("12CVAERT").success(true).valid(true).vatNumber("1TVAT1236");
	}

	/**
	 * @return
	 */
	public static ResponseVatDetailDto getResponseVatDtoWithoutValid() {
		return new ResponseVatDetailDto().companyAddress("US Cherm 34, Timiler, 11 mm").companyName("Timiler")
				.countryCode("US").query("12CVAERT").success(true).validFormat(true).vatNumber("1TVAT1236");
	}

	/**
	 * @return
	 */
	public static ResponseVatDetailDto getResponseVatDtoWithoutSucess() {
		return new ResponseVatDetailDto().companyAddress("US Cherm 34, Timiler, 11 mm").companyName("Timiler")
				.countryCode("US").query("12CVAERT").valid(true).validFormat(true).vatNumber("1TVAT1236");
	}

	/**
	 * @return
	 */
	public static ResponseVatDetailDto getResponseVatDtoWithoutQuery() {
		return new ResponseVatDetailDto().companyAddress("US Cherm 34, Timiler, 11 mm").companyName("Timiler")
				.countryCode("US").success(true).valid(true).validFormat(true).vatNumber("1TVAT1236");
	}

	/**
	 * @return
	 */
	public static ResponseVatDetailDto getResponseVatDtoWithoutCCode() {
		return new ResponseVatDetailDto().companyAddress("US Cherm 34, Timiler, 11 mm").companyName("Timiler")
				.query("12CVAERT").success(true).valid(true).validFormat(true).vatNumber("1TVAT1236");
	}

	/**
	 * @return
	 */
	public static ResponseVatDetailDto getResponseVatDtoWithoutCName() {
		return new ResponseVatDetailDto().companyAddress("US Cherm 34, Timiler, 11 mm").countryCode("US")
				.query("12CVAERT").success(true).valid(true).validFormat(true).vatNumber("1TVAT1236");
	}

	/**
	 * @return
	 */
	public static ResponseVatDetailDto getResponseVatDtoWithoutCAdr() {
		return new ResponseVatDetailDto().companyName("Timiler").countryCode("US").query("12CVAERT").success(true)
				.valid(true).validFormat(true).vatNumber("1TVAT1236");
	}

	/**
	 * @return
	 */
	public static ResponseEntity<ResponseShortConvertDto> getEntityResponseShortConvertDto() {
		return ResponseEntity.ok().body(new ResponseShortConvertDto().createdAt("19.21.2019").result(3.45));
	}

	/**
	 * @return
	 */
	public static CCRequestDto getCCRequestDto() {
		return new CCRequestDto().amount(1).targetCurrency("PKR").sourceCurrency("USD");
	}

	/**
	 * @return
	 */
	public static CCRequestDto getCCRequestDtoWithoutTarget() {
		return new CCRequestDto().amount(1).sourceCurrency("USD");
	}

	/**
	 * @return
	 */
	public static CCRequestDto getCCRequestDtoWithoutSource() {
		return new CCRequestDto().amount(1).targetCurrency("PKR");
	}

	/**
	 * @return
	 */
	public static ResponseShortConvertDto getResponseShortConvertDto() {
		return new ResponseShortConvertDto().createdAt("19.21.2019").result(3.45);
	}

	/**
	 * @return
	 */
	public static ResponseEntity<ResponseConvertDto> getEntityResponseConvertDto() {
		ResultsDto results = new ResultsDto();
		results.put("USD_PKR", new ResultValueDto().fr("USD").to("PKR").val(1.0).id("1"));
		return ResponseEntity.ok().body(new ResponseConvertDto().createdAt("19.21.2019").result(300.42)
				.query(new QueryDto().count(1)).results(results));
	}

	/**
	 * @return
	 */
	public static ResponseConvertDto getResponseConvertDto() {
		ResultsDto results = new ResultsDto();
		results.put("USD_PKR", new ResultValueDto().fr("USD").to("PKR").val(1.0).id("1"));
		return new ResponseConvertDto().createdAt("19.21.2019").result(300.42).query(new QueryDto().count(1))
				.results(results);
	}

	/**
	 * @return
	 */
	public static CurrencyConverterEntity getCurrencyConverterEntity() {
		CurrencyConverterEntity entity = new CurrencyConverterEntity();
		entity.setId(1l);
		entity.setConvertionValue(1.0);
		entity.setCountResult(1);
		entity.setQuery("USD_PKR");
		entity.setResultFr("USD");
		entity.setResultTo("PKR");
		entity.setInsertedDate("12.21.2019");
		entity.setResult(300.24);
		return entity;
	}

	/**
	 * @return
	 */
	public static VATValidatorEntity getValiatorEntity() {
		VATValidatorEntity validatorEntity = new VATValidatorEntity();
		validatorEntity.setCompanyAddress("US Cherm 34, Timiler, 11 mm");
		validatorEntity.setCompanyName("Timiler");
		validatorEntity.setCountryCode("US");
		validatorEntity.setQuery("12CVAERT");
		validatorEntity.setSuccess(true);
		validatorEntity.setValid(true);
		validatorEntity.setValidFormat(true);
		validatorEntity.setVatNumber("1TVAT1236");
		return validatorEntity;
	}

	public static ResponseEntity<ResponseError> getErrorForBadInternalServerException() {
		ResponseError errorResponse = new ResponseError().createdAt("12.22.123").detailedMessage("error")
				.errorCode(HttpStatus.BAD_REQUEST.value()).exceptionName(BadInternalServerException.class.getName())
				.errorMessage("error");
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	public static ResponseEntity<ResponseError> getErrorForInvalidParamException() {
		ResponseError errorResponse = new ResponseError().createdAt("12.22.123").detailedMessage("error")
				.errorCode(HttpStatus.BAD_REQUEST.value()).exceptionName(InvalidParamException.class.getName())
				.errorMessage("error");
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	public static ResponseEntity<ResponseError> getErrorForInvalidRequestException() {
		ResponseError errorResponse = new ResponseError().createdAt("12.22.123").detailedMessage("error")
				.errorCode(HttpStatus.BAD_REQUEST.value()).exceptionName(InvalidRequestException.class.getName())
				.errorMessage("error");
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	public static ResponseEntity<ResponseError> getErrorForInvalidResponseException() {
		ResponseError errorResponse = new ResponseError().createdAt("12.22.123").detailedMessage("error")
				.errorCode(HttpStatus.BAD_REQUEST.value()).exceptionName(InvalidResponseException.class.getName())
				.errorMessage("error");
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	public static ResponseEntity<ResponseError> getErrorForCachePresistException() {
		ResponseError errorResponse = new ResponseError().createdAt("12.22.123").detailedMessage("error")
				.errorCode(HttpStatus.EXPECTATION_FAILED.value()).exceptionName(CachePresistException.class.getName())
				.errorMessage("error");
		return new ResponseEntity<>(errorResponse, HttpStatus.EXPECTATION_FAILED);
	}

	public static ResponseEntity<ResponseError> getErrorForCacheRefresherException() {
		ResponseError errorResponse = new ResponseError().createdAt("12.22.123").detailedMessage("error")
				.errorCode(HttpStatus.EXPECTATION_FAILED.value()).exceptionName(CacheRefresherException.class.getName())
				.errorMessage("error");
		return new ResponseEntity<>(errorResponse, HttpStatus.EXPECTATION_FAILED);
	}

	private TestDataHelper() {

	}
}
