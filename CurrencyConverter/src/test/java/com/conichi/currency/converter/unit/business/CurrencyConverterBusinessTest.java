package com.conichi.currency.converter.unit.business;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.conichi.currency.converter.business.CurrencyConverterBusiness;
import com.conichi.currency.converter.exceptions.InvalidRequestException;
import com.conichi.currency.converter.unit.helper.TestDataHelper;
import com.example.model.CCRequestDto;
import com.example.model.ResponseConvertDto;
import com.example.model.ResponseShortConvertDto;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyConverterBusinessTest {

	@Mock
	private CurrencyConverterBusiness currencyConverterBusiness;

	@Before
	public void init() {
		mockCurrencyConverterBusiness();
	}

	private void mockCurrencyConverterBusinessNullRequest() {
		Mockito.when(currencyConverterBusiness.currencyConvert(Mockito.nullable(CCRequestDto.class)))
				.thenThrow(InvalidRequestException.class);
	}

	private void mockCurrencyConverterDetailedNullRequest() {
		Mockito.when(currencyConverterBusiness.currencyConvertDetailed(Mockito.nullable(CCRequestDto.class)))
				.thenThrow(InvalidRequestException.class);
	}

	private void mockCurrencyConverterBusiness() {
		Mockito.when(currencyConverterBusiness.currencyConvert(Mockito.any(CCRequestDto.class)))
				.thenReturn(TestDataHelper.getResponseShortConvertDto());
		Mockito.when(currencyConverterBusiness.currencyConvertDetailed(Mockito.any(CCRequestDto.class)))
				.thenReturn(TestDataHelper.getResponseConvertDto());
	}

	@Test
	public void testCurrencyConvert() {
		ResponseShortConvertDto response = currencyConverterBusiness.currencyConvert(TestDataHelper.getCCRequestDto());
		Assert.assertEquals((Double) 3.45, response.getResult());
		Assert.assertEquals("19.21.2019", response.getCreatedAt());
	}

	@Test
	public void testCuurencyConvertDetailed() {
		ResponseConvertDto responseConvertDto = currencyConverterBusiness
				.currencyConvertDetailed(TestDataHelper.getCCRequestDto());
		Assert.assertEquals((Double) 300.42, responseConvertDto.getResult());
		Assert.assertEquals("19.21.2019", responseConvertDto.getCreatedAt());
		Assert.assertTrue(1 == responseConvertDto.getQuery().getCount());
		Assert.assertEquals("USD", responseConvertDto.getResults().get("USD_PKR").getFr());
		Assert.assertEquals("PKR", responseConvertDto.getResults().get("USD_PKR").getTo());
	}

	@Test(expected = InvalidRequestException.class)
	public void testCurrencyConvertNullRequest() {
		mockCurrencyConverterBusinessNullRequest();
		currencyConverterBusiness.currencyConvert(null);
	}

	@Test(expected = InvalidRequestException.class)
	public void testCurrencyConvertDetailedNullRequest() {
		mockCurrencyConverterDetailedNullRequest();
		currencyConverterBusiness.currencyConvertDetailed(null);
	}

}
