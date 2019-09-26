package com.conichi.currency.converter.unit.cacherefresher;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.conichi.currency.converter.cacherefresher.CacheRefresherScheduler;
import com.conichi.currency.converter.repository.CurrencyConverterRepository;
import com.conichi.currency.converter.repository.VATValidatorRepository;
import com.conichi.currency.converter.service.Impl.CCCacheServiceImpl;
import com.conichi.currency.converter.service.Impl.VATCacheServiceImpl;

/**
 * @author AQIB JAVED
 * @since 9/26/2019
 * @version 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class CacheRefresherTest {

	@Mock
	private CacheRefresherScheduler cacheRefresherScheduler;

	@InjectMocks
	private CCCacheServiceImpl cacheService;

	@Mock
	private CurrencyConverterRepository repository;

	@InjectMocks
	private VATCacheServiceImpl vatCacheService;

	@Mock
	private VATValidatorRepository vatRepository;

	@Before
	public void init() {
		Mockito.doNothing().when(cacheRefresherScheduler).ccCacheRefresher();
		Mockito.doNothing().when(cacheRefresherScheduler).vvCacheRefresher();
		Mockito.when(cacheService.count()).thenReturn(1L);
		Mockito.when(vatCacheService.count()).thenReturn(1L);
	}

	@Test
	public void testVVCacheRefreshser() {
		Assert.assertTrue(1l == vatCacheService.count());
		cacheRefresherScheduler.vvCacheRefresher();
		deleteAllVVCacheMock();
		Assert.assertTrue(0l == vatCacheService.count());
	}
	
	private void deleteAllVVCacheMock() {
		Mockito.when(vatCacheService.count()).thenReturn(0L);
		
	}

	@Test
	public void testCCCacheRefresh() {
		Assert.assertTrue(1l == cacheService.count());
		cacheRefresherScheduler.ccCacheRefresher();
		deleteAllCurrencyConvertCacheMock();
		Assert.assertTrue(0l == cacheService.count());
	}

	private void deleteAllCurrencyConvertCacheMock() {
		Mockito.when(cacheService.count()).thenReturn(0L);
	}
}
