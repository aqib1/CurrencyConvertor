package com.conichi.currency.converter.unit.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.junit.Assert;
import org.junit.Test;

import com.conichi.currency.converter.aop.LoggingAspect;

/**
 * @author AQIB JAVED
 * @since 9/26/2019
 * @version 1.0
 */
public class LoggingAspectTest {

	@Test
	public void checkMethodsAllMethod() throws NoSuchMethodException, SecurityException {
		String methodName = "allMethod";
		Class<?> c = LoggingAspect.class;
		Method method = c.getDeclaredMethod(methodName);
		Assert.assertNotNull(method);
	}

	@Test
	public void checkMethodsLogStartOfMethod() throws NoSuchMethodException, SecurityException {
		String methodName = "logStartOfMethod";
		Class<?> c = LoggingAspect.class;
		Method method = c.getDeclaredMethod(methodName, JoinPoint.class);
		Assert.assertNotNull(method);
	}

	@Test
	public void checkMethodsLogEndOfMethod() throws NoSuchMethodException, SecurityException {
		String methodName = "logEndOfMethod";
		Class<?> c = LoggingAspect.class;
		Method method = c.getDeclaredMethod(methodName, JoinPoint.class);
		Assert.assertNotNull(method);
	}
}
