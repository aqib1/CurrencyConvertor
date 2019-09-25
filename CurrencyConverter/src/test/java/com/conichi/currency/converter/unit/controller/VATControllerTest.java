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

import com.conichi.currency.converter.controller.VATController;
import com.conichi.currency.converter.unit.helper.DataHelper;
import com.example.model.ResponseShortVatDetailDto;
import com.example.model.ResponseVatDetailDto;

@RunWith(MockitoJUnitRunner.class)
public class VATControllerTest {
	@Mock
	private VATController vatController;

	@Before
	public void init() {
		mockVatController();
	}

	private void mockVatController() {
		Mockito.when(vatController.validateVat(Mockito.anyString()))
				.thenReturn(DataHelper.getEntityResponseShortVatDto());
		Mockito.when(vatController.validateVatDetailed(Mockito.anyString()))
				.thenReturn(DataHelper.getEntityResponseVatDto());
	}

	@Test
	public void testValidateVatDetailed() {
		ResponseEntity<ResponseVatDetailDto> responseEntity = vatController.validateVatDetailed(DataHelper.VAT_ID);
		Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		Assert.assertEquals("US", responseEntity.getBody().getCountryCode());
		Assert.assertEquals(true, responseEntity.getBody().getValidFormat());
		Assert.assertEquals("12CVAERT", responseEntity.getBody().getQuery());
		Assert.assertEquals("US Cherm 34, Timiler, 11 mm", responseEntity.getBody().getCompanyAddress());
		Assert.assertEquals("Timiler", responseEntity.getBody().getCompanyName());
		Assert.assertEquals("1TVAT1236", responseEntity.getBody().getVatNumber());
	}

	@Test
	public void testValidateVat() {
		ResponseEntity<ResponseShortVatDetailDto> responseShortVatDetailDto = vatController
				.validateVat(DataHelper.VAT_ID);
		Assert.assertEquals(HttpStatus.OK, responseShortVatDetailDto.getStatusCode());
		Assert.assertEquals("US", responseShortVatDetailDto.getBody().getCountryCode());
		Assert.assertEquals(true, responseShortVatDetailDto.getBody().getValidFormat());
	}
}