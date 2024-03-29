package com.conichi.currency.converter.unit.business;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.conichi.currency.converter.business.VATCacheBusiness;
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
public class VatCacheBusinessTest {

	@Mock
	private VATCacheBusiness vatCacheBusiness;

	@Before
	public void init() {
		mockVatCacheBusiness();
	}

	private void mockVatCacheBusiness() {
		Mockito.when(vatCacheBusiness.validateVat(Mockito.anyString())).thenReturn(TestDataHelper.getResponseShortVatDto());
		Mockito.when(vatCacheBusiness.validateVatDetails(Mockito.anyString()))
				.thenReturn(TestDataHelper.getResponseVatDto());
	}

	@Test
	public void testValidateVat() {
		ResponseShortVatDetailDto response = vatCacheBusiness.validateVat(TestDataHelper.VAT_ID);
		Assert.assertEquals("US", response.getCountryCode());
		Assert.assertEquals(true, response.getValidFormat());
	}

	private void mockValidateVatForNullVat() {
		Mockito.when(vatCacheBusiness.validateVat(Mockito.nullable(String.class)))
				.thenThrow(InvalidRequestException.class);
	}

	public void mockValidateVatDetailsForNullVat() {
		Mockito.when(vatCacheBusiness.validateVatDetails(Mockito.nullable(String.class)))
				.thenThrow(InvalidRequestException.class);
	}

	@Test
	public void testValidateVatdetails() {
		ResponseVatDetailDto response = vatCacheBusiness.validateVatDetails(TestDataHelper.VAT_ID);
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
		vatCacheBusiness.validateVat(null);
	}

	@Test(expected = InvalidRequestException.class)
	public void testvalidateVatDetailedNullVatValue() {
		mockValidateVatDetailsForNullVat();
		vatCacheBusiness.validateVatDetails(null);
	}
}
