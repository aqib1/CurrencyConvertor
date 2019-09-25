package com.conichi.currency.converter.unit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.conichi.currency.converter.constant.CCHelper;
import com.conichi.currency.converter.exceptions.InvalidRequestException;
import com.conichi.currency.converter.exceptions.InvalidResponseException;
import com.conichi.currency.converter.unit.helper.DataHelper;

@RunWith(MockitoJUnitRunner.class)
public class ConstantTest {

	@Test
	public void testIsNotNullOrEmptyString() {
		boolean t = CCHelper.isNullOrEmptyString("Test");
		Assert.assertFalse(t);
	}

	@Test
	public void testIsNullString() {
		boolean t = CCHelper.isNullOrEmptyString(null);
		Assert.assertTrue(t);
	}

	@Test
	public void testIsEmptyString() {
		boolean t = CCHelper.isNullOrEmptyString("");
		Assert.assertTrue(t);
	}

	@Test
	public void testIsNull() {
		boolean t = CCHelper.isNull(null);
		Assert.assertTrue(t);
	}

	@Test
	public void testIsNotNull() {
		boolean t = CCHelper.isNull(new Object());
		Assert.assertFalse(t);
	}

	@Test
	public void testCheckCoreRequirementsForCCRequestDto() {
		CCHelper.checkCoreRequirementsForCCRequestDto(DataHelper.getCCRequestDto());
		Assert.assertTrue(true);
	}

	@Test(expected = InvalidRequestException.class)
	public void testCheckCoreRequirementsForCCRequestDtoWithOutSource() {
		CCHelper.checkCoreRequirementsForCCRequestDto(DataHelper.getCCRequestDtoWithoutSource());
	}

	@Test(expected = InvalidRequestException.class)
	public void testCheckCoreRequirementsForCCRequestDtoWithOutTarget() {
		CCHelper.checkCoreRequirementsForCCRequestDto(DataHelper.getCCRequestDtoWithoutTarget());
	}

	@Test(expected = InvalidRequestException.class)
	public void testCheckCoreRequirementsForCCRequestDtoNull() {
		CCHelper.checkCoreRequirementsForCCRequestDto(null);
	}

	@Test
	public void testValidateVatResponse() {
		CCHelper.validateVatResponse(DataHelper.getResponseVatDto());
		Assert.assertTrue(true);
	}

	@Test(expected = InvalidResponseException.class)
	public void testValidateVatResponseNull() {
		CCHelper.validateVatResponse(null);
	}

	@Test(expected = InvalidResponseException.class)
	public void testResponseVatDtoWithoutVatNumber() {
		CCHelper.validateVatResponse(DataHelper.getResponseVatDtoWithoutVatNumber());
	}

	@Test(expected = InvalidResponseException.class)
	public void testResponseVatDtoWithoutValidFormat() {
		CCHelper.validateVatResponse(DataHelper.getResponseVatDtoWithoutValidFormat());
	}

	@Test(expected = InvalidResponseException.class)
	public void testResponseVatDtoWithoutValid() {
		CCHelper.validateVatResponse(DataHelper.getResponseVatDtoWithoutValid());
	}

	@Test(expected = InvalidResponseException.class)
	public void testResponseVatDtoWithoutSucess() {
		CCHelper.validateVatResponse(DataHelper.getResponseVatDtoWithoutSucess());
	}

	@Test(expected = InvalidResponseException.class)
	public void testResponseVatDtoWithoutQuery() {
		CCHelper.validateVatResponse(DataHelper.getResponseVatDtoWithoutQuery());
	}

	@Test(expected = InvalidResponseException.class)
	public void testResponseVatDtoWithoutCCode() {
		CCHelper.validateVatResponse(DataHelper.getResponseVatDtoWithoutCCode());
	}

	@Test(expected = InvalidResponseException.class)
	public void testResponseVatDtoWithoutCName() {
		CCHelper.validateVatResponse(DataHelper.getResponseVatDtoWithoutCName());
	}

	@Test(expected = InvalidResponseException.class)
	public void testResponseVatDtoWithoutCAdr() {
		CCHelper.validateVatResponse(DataHelper.getResponseVatDtoWithoutCAdr());
	}

}
