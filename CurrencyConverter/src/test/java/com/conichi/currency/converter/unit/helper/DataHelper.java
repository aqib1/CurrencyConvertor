package com.conichi.currency.converter.unit.helper;

import org.springframework.http.ResponseEntity;

import com.conichi.currency.converter.entities.CurrencyConverterEntity;
import com.conichi.currency.converter.entities.VATValidatorEntity;
import com.example.model.CCRequestDto;
import com.example.model.QueryDto;
import com.example.model.ResponseConvertDto;
import com.example.model.ResponseShortConvertDto;
import com.example.model.ResponseShortVatDetailDto;
import com.example.model.ResponseVatDetailDto;
import com.example.model.ResultValueDto;
import com.example.model.ResultsDto;

public class DataHelper {

	public static final String QUERY = "USD_PKR";
	public static final String SOURCE_CURR = "US";
	public static final String TAR_CURR = "PKR";
	public static final int CONVR_AMNT = 3;
	public static final String VAT_ID = "12CVAERT";
	public static final String CACHE_REFRESHER_VALUE = "cahce.refresher.scheduler";

	public static ResponseEntity<ResponseShortVatDetailDto> getEntityResponseShortVatDto() {
		return ResponseEntity.ok().body(new ResponseShortVatDetailDto().countryCode("US").validFormat(true));
	}

	public static ResponseShortVatDetailDto getResponseShortVatDto() {
		return new ResponseShortVatDetailDto().countryCode("US").validFormat(true);
	}

	public static ResponseEntity<ResponseVatDetailDto> getEntityResponseVatDto() {
		return ResponseEntity.ok()
				.body(new ResponseVatDetailDto().companyAddress("US Cherm 34, Timiler, 11 mm").companyName("Timiler")
						.countryCode("US").query("12CVAERT").success(true).valid(true).validFormat(true)
						.vatNumber("1TVAT1236"));
	}

	public static ResponseVatDetailDto getResponseVatDto() {
		return new ResponseVatDetailDto().companyAddress("US Cherm 34, Timiler, 11 mm").companyName("Timiler")
				.countryCode("US").query("12CVAERT").success(true).valid(true).validFormat(true).vatNumber("1TVAT1236");
	}

	public static ResponseEntity<ResponseShortConvertDto> getEntityResponseShortConvertDto() {
		return ResponseEntity.ok().body(new ResponseShortConvertDto().createdAt("19.21.2019").result(3.45));
	}

	public static CCRequestDto getCCRequestDto() {
		return new CCRequestDto().amount(1).targetCurrency("PKR").sourceCurrency("USD");
	}

	public static ResponseShortConvertDto getResponseShortConvertDto() {
		return new ResponseShortConvertDto().createdAt("19.21.2019").result(3.45);
	}

	public static ResponseEntity<ResponseConvertDto> getEntityResponseConvertDto() {
		ResultsDto results = new ResultsDto();
		results.put("USD_PKR", new ResultValueDto().fr("USD").to("PKR").val(1.0).id("1"));
		return ResponseEntity.ok().body(new ResponseConvertDto().createdAt("19.21.2019").result(300.42)
				.query(new QueryDto().count(1)).results(results));
	}

	public static ResponseConvertDto getResponseConvertDto() {
		ResultsDto results = new ResultsDto();
		results.put("USD_PKR", new ResultValueDto().fr("USD").to("PKR").val(1.0).id("1"));
		return new ResponseConvertDto().createdAt("19.21.2019").result(300.42).query(new QueryDto().count(1))
				.results(results);
	}

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

	private DataHelper() {

	}
}
