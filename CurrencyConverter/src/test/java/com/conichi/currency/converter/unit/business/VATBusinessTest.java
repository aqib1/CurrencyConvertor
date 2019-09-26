package com.conichi.currency.converter.unit.business;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.conichi.currency.converter.business.VATBusiness;
import com.conichi.currency.converter.exceptions.InvalidRequestException;
import com.conichi.currency.converter.unit.helper.TestDataHelper;
import com.example.model.ResponseShortVatDetailDto;
import com.example.model.ResponseVatDetailDto;

/**
 * @author AQIB JAVED
 * @since 9/26/2019
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class VATBusinessTest {

	@Mock
	private VATBusiness vatBusiness;

	@Before
	public void init() {
		mockVatBusiness();
	}

	private void mockVatBusiness() {
		Mockito.when(vatBusiness.validateVat(Mockito.anyString())).thenReturn(TestDataHelper.getResponseShortVatDto());
		Mockito.when(vatBusiness.validateVatDetails(Mockito.anyString())).thenReturn(TestDataHelper.getResponseVatDto());
	}

	private void mockValidateVatForNullVat() {
		Mockito.when(vatBusiness.validateVat(Mockito.nullable(String.class))).thenThrow(InvalidRequestException.class);
	}

	public void mockValidateVatDetailsForNullVat() {
		Mockito.when(vatBusiness.validateVatDetails(Mockito.nullable(String.class)))
				.thenThrow(InvalidRequestException.class);
	}

	@Test
	public void testValidateVat() {
		ResponseShortVatDetailDto response = vatBusiness.validateVat(TestDataHelper.VAT_ID);
		Assert.assertEquals("US", response.getCountryCode());
		Assert.assertEquals(true, response.getValidFormat());
	}

	@Test
	public void testValidateVatdetails() {
		ResponseVatDetailDto response = vatBusiness.validateVatDetails(TestDataHelper.VAT_ID);
		Assert.assertEquals("US", response.getCountryCode());
		Assert.assertEquals(true, response.getValidFormat());
		Assert.assertEquals("12CVAERT", response.getQuery());
		Assert.assertEquals("US Cherm 34, Timiler, 11 mm", response.getCompanyAddress());
		Assert.assertEquals("Timiler", response.getCompanyName());
		Assert.assertEquals("1TVAT1236", response.getVatNumber());
	}

	@Test(expected = InvalidRequestException.class)
	public void testvalidateVatNullVatValue() {
		mockValidateVatForNullVat();
		vatBusiness.validateVat(null);
	}

	@Test(expected = InvalidRequestException.class)
	public void testvalidateVatDetailedNullVatValue() {
		mockValidateVatDetailsForNullVat();
		vatBusiness.validateVatDetails(null);
	}

}
