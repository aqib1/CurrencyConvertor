package com.conichi.currency.converter.unit.controller.advice;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.conichi.currency.converter.controller.advices.ControllerExceptionsAdvice;
import com.conichi.currency.converter.exceptions.BadInternalServerException;
import com.conichi.currency.converter.exceptions.CachePresistException;
import com.conichi.currency.converter.exceptions.CacheRefresherException;
import com.conichi.currency.converter.exceptions.InvalidParamException;
import com.conichi.currency.converter.exceptions.InvalidRequestException;
import com.conichi.currency.converter.exceptions.InvalidResponseException;
import com.conichi.currency.converter.unit.helper.TestDataHelper;
import com.example.model.ResponseError;

/**
 * @author AQIB JAVED
 * @since 9/26/2019
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class ControllerExceptionsAdviceTest {

	@Mock
	private ControllerExceptionsAdvice controllerExceptionsAdvice;

	@Before
	public void init() {
		Mockito.when(controllerExceptionsAdvice.handleBadInternalServerException(Mockito.any(RuntimeException.class),
				Mockito.any())).thenReturn(TestDataHelper.getErrorForBadInternalServerException());

		Mockito.when(controllerExceptionsAdvice.handleInvalidParamException(Mockito.any(RuntimeException.class),
				Mockito.any())).thenReturn(TestDataHelper.getErrorForInvalidParamException());

		Mockito.when(controllerExceptionsAdvice.handleInvalidRequestException(Mockito.any(RuntimeException.class),
				Mockito.any())).thenReturn(TestDataHelper.getErrorForInvalidRequestException());

		Mockito.when(controllerExceptionsAdvice.handleInvalidResponseException(Mockito.any(RuntimeException.class),
				Mockito.any())).thenReturn(TestDataHelper.getErrorForInvalidResponseException());

		Mockito.when(controllerExceptionsAdvice.handleCachePresistException(Mockito.any(RuntimeException.class),
				Mockito.any())).thenReturn(TestDataHelper.getErrorForCachePresistException());

		Mockito.when(controllerExceptionsAdvice.handleCacheRefresherException(Mockito.any(RuntimeException.class),
				Mockito.any())).thenReturn(TestDataHelper.getErrorForCacheRefresherException());
	}

	@Test
	public void testBadInternalServerException() {
		ResponseEntity<ResponseError> response = controllerExceptionsAdvice
				.handleBadInternalServerException(TestDataHelper.TEST_RUNTIME_EXC, TestDataHelper.TEST_WEB_REQUEST);
		Assert.assertEquals("12.22.123", response.getBody().getCreatedAt());
		Assert.assertEquals("error", response.getBody().getDetailedMessage());
		Assert.assertEquals("error", response.getBody().getErrorMessage());
		Assert.assertTrue(HttpStatus.BAD_REQUEST.value() == response.getBody().getErrorCode());
		Assert.assertEquals(BadInternalServerException.class.getName(), response.getBody().getExceptionName());

	}

	@Test
	public void testHandleInvalidParamException() {
		ResponseEntity<ResponseError> response = controllerExceptionsAdvice
				.handleInvalidParamException(TestDataHelper.TEST_RUNTIME_EXC, TestDataHelper.TEST_WEB_REQUEST);
		Assert.assertEquals("12.22.123", response.getBody().getCreatedAt());
		Assert.assertEquals("error", response.getBody().getDetailedMessage());
		Assert.assertEquals("error", response.getBody().getErrorMessage());
		Assert.assertTrue(HttpStatus.BAD_REQUEST.value() == response.getBody().getErrorCode());
		Assert.assertEquals(InvalidParamException.class.getName(), response.getBody().getExceptionName());

	}

	@Test
	public void testHandleInvalidRequestException() {
		ResponseEntity<ResponseError> response = controllerExceptionsAdvice
				.handleInvalidRequestException(TestDataHelper.TEST_RUNTIME_EXC, TestDataHelper.TEST_WEB_REQUEST);
		Assert.assertEquals("12.22.123", response.getBody().getCreatedAt());
		Assert.assertEquals("error", response.getBody().getDetailedMessage());
		Assert.assertEquals("error", response.getBody().getErrorMessage());
		Assert.assertTrue(HttpStatus.BAD_REQUEST.value() == response.getBody().getErrorCode());
		Assert.assertEquals(InvalidRequestException.class.getName(), response.getBody().getExceptionName());

	}

	@Test
	public void testHandleInvalidResponseException() {
		ResponseEntity<ResponseError> response = controllerExceptionsAdvice
				.handleInvalidResponseException(TestDataHelper.TEST_RUNTIME_EXC, TestDataHelper.TEST_WEB_REQUEST);
		Assert.assertEquals("12.22.123", response.getBody().getCreatedAt());
		Assert.assertEquals("error", response.getBody().getDetailedMessage());
		Assert.assertEquals("error", response.getBody().getErrorMessage());
		Assert.assertTrue(HttpStatus.BAD_REQUEST.value() == response.getBody().getErrorCode());
		Assert.assertEquals(InvalidResponseException.class.getName(), response.getBody().getExceptionName());

	}

	@Test
	public void testHandleCachePresistException() {
		ResponseEntity<ResponseError> response = controllerExceptionsAdvice
				.handleCachePresistException(TestDataHelper.TEST_RUNTIME_EXC, TestDataHelper.TEST_WEB_REQUEST);
		Assert.assertEquals("12.22.123", response.getBody().getCreatedAt());
		Assert.assertEquals("error", response.getBody().getDetailedMessage());
		Assert.assertEquals("error", response.getBody().getErrorMessage());
		Assert.assertTrue(HttpStatus.EXPECTATION_FAILED.value() == response.getBody().getErrorCode());
		Assert.assertEquals(CachePresistException.class.getName(), response.getBody().getExceptionName());

	}

	@Test
	public void testHandleCacheRefresherException() {
		ResponseEntity<ResponseError> response = controllerExceptionsAdvice
				.handleCacheRefresherException(TestDataHelper.TEST_RUNTIME_EXC, TestDataHelper.TEST_WEB_REQUEST);
		Assert.assertEquals("12.22.123", response.getBody().getCreatedAt());
		Assert.assertEquals("error", response.getBody().getDetailedMessage());
		Assert.assertEquals("error", response.getBody().getErrorMessage());
		Assert.assertTrue(HttpStatus.EXPECTATION_FAILED.value() == response.getBody().getErrorCode());
		Assert.assertEquals(CacheRefresherException.class.getName(), response.getBody().getExceptionName());

	}
}
