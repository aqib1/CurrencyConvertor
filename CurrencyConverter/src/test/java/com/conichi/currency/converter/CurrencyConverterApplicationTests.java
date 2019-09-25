package com.conichi.currency.converter;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author AQIB JAVED
 * @version 1.0
 * @since 9/16/2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest
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
