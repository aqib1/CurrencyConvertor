package com.conichi.currency.converter.unit.entities;

import org.junit.Test;

import com.conichi.currency.converter.entities.CurrencyConverterEntity;
import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

/**
 * @author AQIB JAVED
 * @since 9/26/2019
 * @version 1.0
 */
public class CurrencyConverterEntityTest {
	@Test
	public void testGetterSetter() {
		PojoClass pojoClass = PojoClassFactory.getPojoClass(CurrencyConverterEntity.class);
		Validator validator = ValidatorBuilder.create().with(new SetterMustExistRule()).with(new GetterMustExistRule())
				.with(new SetterTester()).with(new GetterTester()).build();
		validator.validate(pojoClass);
	}
}
