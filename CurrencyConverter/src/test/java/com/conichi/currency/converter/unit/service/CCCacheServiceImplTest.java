package com.conichi.currency.converter.unit.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.conichi.currency.converter.entities.CurrencyConverterEntity;
import com.conichi.currency.converter.exceptions.CachePresistException;
import com.conichi.currency.converter.service.Impl.CCCacheServiceImpl;
import com.conichi.currency.converter.unit.helper.TestDataHelper;

/**
 * @author AQIB JAVED
 * @since 9/26/2019
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class CCCacheServiceImplTest {

	@Mock
	private CCCacheServiceImpl ccCacheServiceImpl;

	@InjectMocks
	private CurrencyConverterEntity currencyConverterEntity;

	@Before
	public void init() {
		Mockito.when(ccCacheServiceImpl.read(Mockito.anyString())).thenReturn(TestDataHelper.getCurrencyConverterEntity());
		Mockito.when(ccCacheServiceImpl.writeCache(Mockito.any(CurrencyConverterEntity.class)))
				.thenReturn(TestDataHelper.getCurrencyConverterEntity());
		Mockito.doNothing().when(ccCacheServiceImpl).deleteAll();
	}

	private void throwCachePresistenceExceptionReadOperation() {
		Mockito.when(ccCacheServiceImpl.read(Mockito.nullable(String.class))).thenThrow(CachePresistException.class);
	}

	private void throwCachePresistenceExceptionWriteOperation() {
		Mockito.when(ccCacheServiceImpl.writeCache(Mockito.nullable(CurrencyConverterEntity.class)))
				.thenThrow(CachePresistException.class);
	}

	private void throwCachePresistenceExceptionCountOperation() {
		Mockito.when(ccCacheServiceImpl.count()).thenThrow(CachePresistException.class);
	}

	@Test(expected = CachePresistException.class)
	public void countValueWithException() {
		throwCachePresistenceExceptionCountOperation();
		ccCacheServiceImpl.count();
	}

	@Test(expected = CachePresistException.class)
	public void writeWithNullQuery() {
		throwCachePresistenceExceptionWriteOperation();
		ccCacheServiceImpl.writeCache(null);
	}

	@Test(expected = CachePresistException.class)
	public void readWithNullQuery() {
		throwCachePresistenceExceptionReadOperation();
		ccCacheServiceImpl.read(null);
	}

	@Test
	public void deleteCache() {
		Mockito.when(ccCacheServiceImpl.count()).thenReturn(0L);
		ccCacheServiceImpl.deleteAll();
		Assert.assertEquals(0L, ccCacheServiceImpl.count());
	}

	@Test
	public void readCache() {
		CurrencyConverterEntity entity = ccCacheServiceImpl.read(TestDataHelper.QUERY);
		Assert.assertEquals((Long) 1l, entity.getId());
		Assert.assertTrue(1.0 == entity.getConvertionValue());
		Assert.assertEquals(1, entity.getCountResult().intValue());
		Assert.assertEquals("USD_PKR", entity.getQuery());
		Assert.assertEquals("USD", entity.getResultFr());
		Assert.assertEquals("PKR", entity.getResultTo());
	}

	@Test
	public void writeCache() {
		Mockito.when(ccCacheServiceImpl.count()).thenReturn(1l);
		CurrencyConverterEntity entity = ccCacheServiceImpl.writeCache(TestDataHelper.getCurrencyConverterEntity());
		Assert.assertEquals(1L, ccCacheServiceImpl.count());
		Assert.assertEquals((Long) 1l, entity.getId());
		Assert.assertTrue(1.0 == entity.getConvertionValue());
		Assert.assertEquals(1, entity.getCountResult().intValue());
		Assert.assertEquals("USD_PKR", entity.getQuery());
		Assert.assertEquals("USD", entity.getResultFr());
		Assert.assertEquals("PKR", entity.getResultTo());
	}
}
