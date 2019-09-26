package com.conichi.currency.converter.unit.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.conichi.currency.converter.controller.CurrencyConverterController;
import com.conichi.currency.converter.unit.helper.TestDataHelper;
import com.example.model.ResponseConvertDto;
import com.example.model.ResponseShortConvertDto;

/**
 * @author AQIB JAVED
 * @since 9/26/2019
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class CurrencyConverterControllerTest {

	@Mock
	private CurrencyConverterController controller;

	@Before
	public void init() {
		mockCurrencyConverterController();
	}

	private void mockCurrencyConverterController() {
		Mockito.when(controller.currencyConvert(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt()))
				.thenReturn(TestDataHelper.getEntityResponseShortConvertDto());
		Mockito.when(controller.currencyConvertDetails(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt()))
				.thenReturn(TestDataHelper.getEntityResponseConvertDto());

	}

	@Test
	public void testCurrencyConvert() {
		ResponseEntity<ResponseShortConvertDto> response = controller.currencyConvert(TestDataHelper.SOURCE_CURR,
				TestDataHelper.TAR_CURR, TestDataHelper.CONVR_AMNT);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertEquals((Double) 3.45, response.getBody().getResult());
		Assert.assertEquals("19.21.2019", response.getBody().getCreatedAt());
	}

	@Test
	public void testCurrencyConvertDetails() {
		ResponseEntity<ResponseConvertDto> response = controller.currencyConvertDetails(TestDataHelper.SOURCE_CURR,
				TestDataHelper.TAR_CURR, TestDataHelper.CONVR_AMNT);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertEquals((Double) 300.42, response.getBody().getResult());
		Assert.assertEquals("19.21.2019", response.getBody().getCreatedAt());
		Assert.assertTrue(1 == response.getBody().getQuery().getCount());
		Assert.assertEquals("USD", response.getBody().getResults().get("USD_PKR").getFr());
		Assert.assertEquals("PKR", response.getBody().getResults().get("USD_PKR").getTo());
	}
}
