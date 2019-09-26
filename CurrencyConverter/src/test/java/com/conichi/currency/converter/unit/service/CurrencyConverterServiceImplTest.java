package com.conichi.currency.converter.unit.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.conichi.currency.converter.exceptions.BadInternalServerException;
import com.conichi.currency.converter.service.Impl.CurrencyConverterServiceImpl;
import com.conichi.currency.converter.unit.helper.TestDataHelper;
import com.example.model.CCRequestDto;
import com.example.model.ResponseConvertDto;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyConverterServiceImplTest {

	@Mock
	private CurrencyConverterServiceImpl currencyConverterService;

	@Before
	public void init() {
		mockCurrencyConverterService();
	}

	private void mockCurrencyConverterService() {
		Mockito.when(currencyConverterService.currencyConvert(TestDataHelper.getCCRequestDto()))
				.thenReturn(TestDataHelper.getResponseConvertDto());
	}

	private void mockNullRequestCurrencyConverterService() {
		Mockito.when(currencyConverterService.currencyConvert(Mockito.nullable(CCRequestDto.class)))
				.thenThrow(BadInternalServerException.class);
	}

	@Test(expected = BadInternalServerException.class)
	public void nullCurrencyConvert() {
		mockNullRequestCurrencyConverterService();
		currencyConverterService.currencyConvert(null);
	}

	@Test
	public void currencyConvert() {
		ResponseConvertDto responseConvertDto = currencyConverterService.currencyConvert(TestDataHelper.getCCRequestDto());
		Assert.assertEquals((Double) 300.42, responseConvertDto.getResult());
		Assert.assertEquals("19.21.2019", responseConvertDto.getCreatedAt());
		Assert.assertTrue(1 == responseConvertDto.getQuery().getCount());
		Assert.assertEquals("USD", responseConvertDto.getResults().get("USD_PKR").getFr());
		Assert.assertEquals("PKR", responseConvertDto.getResults().get("USD_PKR").getTo());
	}
}
