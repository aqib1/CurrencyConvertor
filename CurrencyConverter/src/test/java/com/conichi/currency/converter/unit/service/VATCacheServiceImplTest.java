package com.conichi.currency.converter.unit.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.conichi.currency.converter.entities.VATValidatorEntity;
import com.conichi.currency.converter.exceptions.CachePresistException;
import com.conichi.currency.converter.service.Impl.VATCacheServiceImpl;
import com.conichi.currency.converter.unit.helper.TestDataHelper;

/**
 * @author AQIB JAVED
 * @since 9/26/2019
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class VATCacheServiceImplTest {

	@Mock
	private VATCacheServiceImpl vatCacheServiceImpl;

	@Before
	public void init() {
		mockVatCacheServiceImpl();
	}

	private void mockVatCacheServiceImpl() {
		Mockito.when(vatCacheServiceImpl.read(Mockito.anyString())).thenReturn(TestDataHelper.getValiatorEntity());
		Mockito.when(vatCacheServiceImpl.writeCache(Mockito.any(VATValidatorEntity.class)))
				.thenReturn(TestDataHelper.getValiatorEntity());
		Mockito.when(vatCacheServiceImpl.count()).thenReturn(1L);
		Mockito.doNothing().when(vatCacheServiceImpl).deleteAll();
	}

	@Test(expected = CachePresistException.class)
	public void writeWithNullRequest() {
		mockWriteWithNullRequest();
		vatCacheServiceImpl.writeCache(null);
	}

	private void mockWriteWithNullRequest() {
		Mockito.when(vatCacheServiceImpl.writeCache(Mockito.nullable(VATValidatorEntity.class)))
				.thenThrow(CachePresistException.class);
	}

	@Test(expected = CachePresistException.class)
	public void readWithNullRequest() {
		mockReadWithNullRequest();
		vatCacheServiceImpl.read(null);
	}

	private void mockReadWithNullRequest() {
		Mockito.when(vatCacheServiceImpl.read(Mockito.nullable(String.class))).thenThrow(CachePresistException.class);
	}

	@Test(expected = CachePresistException.class)
	public void testCountWithException() {
		mockCountWithException();
		vatCacheServiceImpl.count();
	}

	private void mockCountWithException() {
		Mockito.when(vatCacheServiceImpl.count()).thenThrow(CachePresistException.class);
	}

	@Test
	public void testDeleteAll() {
		Mockito.when(vatCacheServiceImpl.count()).thenReturn(0L);
		vatCacheServiceImpl.deleteAll();
		Assert.assertTrue(0L == vatCacheServiceImpl.count());
	}

	@Test
	public void testVatCacheServiceWrite() {
		VATValidatorEntity entity = vatCacheServiceImpl.writeCache(TestDataHelper.getValiatorEntity());
		Assert.assertEquals("US", entity.getCountryCode());
		Assert.assertEquals(true, entity.getValidFormat());
		Assert.assertEquals("12CVAERT", entity.getQuery());
		Assert.assertEquals("US Cherm 34, Timiler, 11 mm", entity.getCompanyAddress());
		Assert.assertEquals("Timiler", entity.getCompanyName());
		Assert.assertEquals("1TVAT1236", entity.getVatNumber());
		Assert.assertTrue(1L == vatCacheServiceImpl.count());
	}

	@Test
	public void testVatCacheServiceRead() {
		VATValidatorEntity entity = vatCacheServiceImpl.read(TestDataHelper.VAT_ID);
		Assert.assertEquals("US", entity.getCountryCode());
		Assert.assertEquals(true, entity.getValidFormat());
		Assert.assertEquals("12CVAERT", entity.getQuery());
		Assert.assertEquals("US Cherm 34, Timiler, 11 mm", entity.getCompanyAddress());
		Assert.assertEquals("Timiler", entity.getCompanyName());
		Assert.assertEquals("1TVAT1236", entity.getVatNumber());
	}
}
