package com.conichi.currency.converter.unit.exceptions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Assert;
import org.junit.Test;

import com.conichi.currency.converter.exceptions.InvalidRequestException;

public class InvalidRequestExceptionTest {
	@Test
	public void contextLoads() throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> c = InvalidRequestException.class;
		Constructor<?> cons[] = c.getDeclaredConstructors();
		Assert.assertEquals(2, cons.length);
		Assert.assertTrue(cons[0].newInstance("") instanceof RuntimeException);
	}
}
