package com.conichi.currency.converter.unit.constants;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.conichi.currency.converter.constant.DataHelper;
import com.conichi.currency.converter.exceptions.InvalidRequestException;
import com.conichi.currency.converter.exceptions.InvalidResponseException;
import com.conichi.currency.converter.unit.helper.TestDataHelper;

@RunWith(MockitoJUnitRunner.class)
public class DataHelperTest {

	@Test
	public void testIsNotNullOrEmptyString() {
		boolean t = DataHelper.isNullOrEmptyString("Test");
		Assert.assertFalse(t);
	}

	@Test
	public void testIsNullString() {
		boolean t = DataHelper.isNullOrEmptyString(null);
		Assert.assertTrue(t);
	}

	@Test
	public void testIsEmptyString() {
		boolean t = DataHelper.isNullOrEmptyString("");
		Assert.assertTrue(t);
	}

	@Test
	public void testIsNull() {
		boolean t = DataHelper.isNull(null);
		Assert.assertTrue(t);
	}

	@Test
	public void testIsNotNull() {
		boolean t = DataHelper.isNull(new Object());
		Assert.assertFalse(t);
	}

	@Test
	public void testCheckCoreRequirementsForCCRequestDto() {
		DataHelper.checkCoreRequirementsForCCRequestDto(TestDataHelper.getCCRequestDto());
		Assert.assertTrue(true);
	}

	@Test(expected = InvalidRequestException.class)
	public void testCheckCoreRequirementsForCCRequestDtoWithOutSource() {
		DataHelper.checkCoreRequirementsForCCRequestDto(TestDataHelper.getCCRequestDtoWithoutSource());
	}

	@Test(expected = InvalidRequestException.class)
	public void testCheckCoreRequirementsForCCRequestDtoWithOutTarget() {
		DataHelper.checkCoreRequirementsForCCRequestDto(TestDataHelper.getCCRequestDtoWithoutTarget());
	}

	@Test(expected = InvalidRequestException.class)
	public void testCheckCoreRequirementsForCCRequestDtoNull() {
		DataHelper.checkCoreRequirementsForCCRequestDto(null);
	}

	@Test
	public void testValidateVatResponse() {
		DataHelper.validateVatResponse(TestDataHelper.getResponseVatDto());
		Assert.assertTrue(true);
	}

	@Test(expected = InvalidResponseException.class)
	public void testValidateVatResponseNull() {
		DataHelper.validateVatResponse(null);
	}

	@Test(expected = InvalidResponseException.class)
	public void testResponseVatDtoWithoutVatNumber() {
		DataHelper.validateVatResponse(TestDataHelper.getResponseVatDtoWithoutVatNumber());
	}

	@Test(expected = InvalidResponseException.class)
	public void testResponseVatDtoWithoutValidFormat() {
		DataHelper.validateVatResponse(TestDataHelper.getResponseVatDtoWithoutValidFormat());
	}

	@Test(expected = InvalidResponseException.class)
	public void testResponseVatDtoWithoutValid() {
		DataHelper.validateVatResponse(TestDataHelper.getResponseVatDtoWithoutValid());
	}

	@Test(expected = InvalidResponseException.class)
	public void testResponseVatDtoWithoutSucess() {
		DataHelper.validateVatResponse(TestDataHelper.getResponseVatDtoWithoutSucess());
	}

	@Test(expected = InvalidResponseException.class)
	public void testResponseVatDtoWithoutQuery() {
		DataHelper.validateVatResponse(TestDataHelper.getResponseVatDtoWithoutQuery());
	}

	@Test(expected = InvalidResponseException.class)
	public void testResponseVatDtoWithoutCCode() {
		DataHelper.validateVatResponse(TestDataHelper.getResponseVatDtoWithoutCCode());
	}

	@Test(expected = InvalidResponseException.class)
	public void testResponseVatDtoWithoutCName() {
		DataHelper.validateVatResponse(TestDataHelper.getResponseVatDtoWithoutCName());
	}

	@Test(expected = InvalidResponseException.class)
	public void testResponseVatDtoWithoutCAdr() {
		DataHelper.validateVatResponse(TestDataHelper.getResponseVatDtoWithoutCAdr());
	}

}
