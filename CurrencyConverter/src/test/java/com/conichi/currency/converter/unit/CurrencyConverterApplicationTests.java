package com.conichi.currency.converter.unit;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.conichi.currency.converter.CurrencyConverterApplication;

/**
 * @author AQIB JAVED
 * @version 1.0
 * @since 9/16/2019
 */
@RunWith(MockitoJUnitRunner.class)
public class CurrencyConverterApplicationTests {

	/**
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	@Test
	public void contextLoads() throws NoSuchMethodException, SecurityException {
		String methodName = "main";
		Class<?> c = CurrencyConverterApplication.class;
		Method method = c.getDeclaredMethod(methodName, String[].class);
		Assert.assertNotNull(method);
	}

}
