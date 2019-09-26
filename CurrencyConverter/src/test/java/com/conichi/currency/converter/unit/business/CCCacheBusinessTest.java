package com.conichi.currency.converter.unit.business;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.conichi.currency.converter.business.CCCacheBusiness;
import com.conichi.currency.converter.exceptions.InvalidRequestException;
import com.conichi.currency.converter.unit.helper.TestDataHelper;
import com.example.model.CCRequestDto;
import com.example.model.ResponseConvertDto;
import com.example.model.ResponseShortConvertDto;

@RunWith(MockitoJUnitRunner.class)
public class CCCacheBusinessTest {

	@Mock
	private CCCacheBusiness ccCacheBusiness;

	@Before
	public void init() {
		mockCCCacheBusiness();
	}

	private void mockCurrencyConverterBusinessNullRequest() {
		Mockito.when(ccCacheBusiness.convertCurrency(Mockito.nullable(CCRequestDto.class)))
				.thenThrow(InvalidRequestException.class);
	}

	private void mockCurrencyConverterDetailedNullRequest() {
		Mockito.when(ccCacheBusiness.convertCurrencyDetailed(Mockito.nullable(CCRequestDto.class)))
				.thenThrow(InvalidRequestException.class);
	}

	private void mockCCCacheBusiness() {
		Mockito.when(ccCacheBusiness.convertCurrency(Mockito.any(CCRequestDto.class)))
				.thenReturn(TestDataHelper.getResponseShortConvertDto());
		Mockito.when(ccCacheBusiness.convertCurrencyDetailed(Mockito.any(CCRequestDto.class)))
				.thenReturn(TestDataHelper.getResponseConvertDto());
	}

	@Test
	public void testConvertCurrency() {
		ResponseShortConvertDto response = ccCacheBusiness.convertCurrency(TestDataHelper.getCCRequestDto());
		Assert.assertEquals((Double) 3.45, response.getResult());
		Assert.assertEquals("19.21.2019", response.getCreatedAt());
	}

	@Test
	public void testConvertCurrencyDetails() {
		ResponseConvertDto responseConvertDto = ccCacheBusiness.convertCurrencyDetailed(TestDataHelper.getCCRequestDto());
		Assert.assertEquals((Double) 300.42, responseConvertDto.getResult());
		Assert.assertEquals("19.21.2019", responseConvertDto.getCreatedAt());
		Assert.assertTrue(1 == responseConvertDto.getQuery().getCount());
		Assert.assertEquals("USD", responseConvertDto.getResults().get("USD_PKR").getFr());
		Assert.assertEquals("PKR", responseConvertDto.getResults().get("USD_PKR").getTo());
	}

	@Test(expected = InvalidRequestException.class)
	public void testCurrencyConvertNullRequest() {
		mockCurrencyConverterBusinessNullRequest();
		ccCacheBusiness.convertCurrency(null);
	}

	@Test(expected = InvalidRequestException.class)
	public void testCurrencyConvertDetailedNullRequest() {
		mockCurrencyConverterDetailedNullRequest();
		ccCacheBusiness.convertCurrencyDetailed(null);
	}
}
