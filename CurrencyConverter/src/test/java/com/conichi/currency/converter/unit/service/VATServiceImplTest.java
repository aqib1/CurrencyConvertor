package com.conichi.currency.converter.unit.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.conichi.currency.converter.exceptions.InvalidResponseException;
import com.conichi.currency.converter.service.Impl.VATServiceImpl;
import com.conichi.currency.converter.unit.helper.TestDataHelper;
import com.example.model.ResponseVatDetailDto;

@RunWith(MockitoJUnitRunner.class)
public class VATServiceImplTest {

	@Mock
	private VATServiceImpl vatServiceImpl;

	@Before
	public void init() {
		mockVatServiceImpl();
	}

	private void mockVatServiceImpl() {
		Mockito.when(vatServiceImpl.validateVat(Mockito.anyString())).thenReturn(TestDataHelper.getResponseVatDto());
	}

	@Test(expected = InvalidResponseException.class)
	public void testValidateVatNullRequest() {
		mockValidateVatNullRequest();
		vatServiceImpl.validateVat(null);
	}

	private void mockValidateVatNullRequest() {
		Mockito.when(vatServiceImpl.validateVat(Mockito.nullable(String.class)))
				.thenThrow(InvalidResponseException.class);
	}

	@Test
	public void testValidateVat() {
		ResponseVatDetailDto response = vatServiceImpl.validateVat(TestDataHelper.VAT_ID);
		Assert.assertEquals("US", response.getCountryCode());
		Assert.assertEquals(true, response.getValidFormat());
		Assert.assertEquals("12CVAERT", response.getQuery());
		Assert.assertEquals("US Cherm 34, Timiler, 11 mm", response.getCompanyAddress());
		Assert.assertEquals("Timiler", response.getCompanyName());
		Assert.assertEquals("1TVAT1236", response.getVatNumber());
	}
}
