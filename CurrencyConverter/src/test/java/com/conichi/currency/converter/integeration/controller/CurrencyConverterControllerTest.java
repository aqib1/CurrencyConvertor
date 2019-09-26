package com.conichi.currency.converter.integeration.controller;

import static com.conichi.currency.converter.constant.URLS.API_CURRENCY_CONVERT;
import static com.conichi.currency.converter.constant.URLS.API_CURRENCY_CONVERT_DETAILS;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.conichi.currency.converter.business.CCCacheBusiness;
import com.conichi.currency.converter.business.CurrencyConverterBusiness;
import com.conichi.currency.converter.controller.CurrencyConverterController;
import com.conichi.currency.converter.entities.CurrencyConverterEntity;
import com.conichi.currency.converter.mapper.CurrencyConverterEntityMapper;
import com.conichi.currency.converter.mapper.ResponseConvertDtoMapper;
import com.conichi.currency.converter.mapper.ResponseShortConvertMapper;
import com.conichi.currency.converter.service.Impl.CCCacheServiceImpl;
import com.conichi.currency.converter.service.Impl.CurrencyConverterServiceImpl;
import com.conichi.currency.converter.unit.helper.TestDataHelper;
import com.example.model.CCRequestDto;
import com.example.model.ResponseConvertDto;

/**
 * @author AQIB JAVED
 * @since 9/26/2019
 * @version 1.0
 */
@RunWith(SpringRunner.class)
public class CurrencyConverterControllerTest {

	private MockMvc mockMvc;

	private MediaType MEDIA_TYPE_JSON_UTF8;

	@Mock
	private CurrencyConverterServiceImpl converterServiceImpl;

	@Mock
	private CCCacheServiceImpl ccCacheService;

	@Mock
	private ResponseShortConvertMapper responseShortMapper;

	@Mock
	private CurrencyConverterEntityMapper currencyConverterDtoMapper;

	@Spy
	private ResponseConvertDtoMapper responseConvertDtoMapper;

	@Spy
	@InjectMocks
	private CCCacheBusiness cCCacheBusiness;

	@Spy
	@InjectMocks
	private CurrencyConverterBusiness business;

	@InjectMocks
	private CurrencyConverterController controller;

	/**
	 * <p>
	 * /api/currency/convert
	 * </p>
	 */

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		Mockito.when(ccCacheService.read(Mockito.anyString())).thenReturn(TestDataHelper.getCurrencyConverterEntity());
		Mockito.when(ccCacheService.writeCache(Mockito.any(CurrencyConverterEntity.class))).thenReturn(null);
		Mockito.when(responseShortMapper
				.currencyConverterEntityToResponseShortDto(Mockito.any(CurrencyConverterEntity.class)))
				.thenReturn(TestDataHelper.getResponseShortConvertDto());
		Mockito.when(currencyConverterDtoMapper.currencyConverterEntityFromResponseConvertDto(
				Mockito.any(ResponseConvertDto.class), Mockito.anyString()))
				.thenReturn(TestDataHelper.getCurrencyConverterEntity());

		Mockito.when(
				responseShortMapper.responseConvertDtoToResponseShortConvertDto(Mockito.any(ResponseConvertDto.class)))
				.thenReturn(TestDataHelper.getResponseShortConvertDto());
	}

	@Test
	public void currencyConvertByCache() throws Exception {
		mockMvc.perform(get(API_CURRENCY_CONVERT + TestDataHelper.CURRENCY_API_QUERY_FOR_TEST))
				.andExpect(status().isOk()).andExpect(jsonPath("$.createdAt").value("19.21.2019"))
				.andExpect(jsonPath("$.result").value(3.45)).andExpect(content().contentType(MEDIA_TYPE_JSON_UTF8))
				.andDo(print());
	}

	@Test
	public void currencyConvertDetailedByCache() throws Exception {
		mockCCacheServiceDetailedByCache();
		mockMvc.perform(
				get(API_CURRENCY_CONVERT + API_CURRENCY_CONVERT_DETAILS + TestDataHelper.CURRENCY_API_QUERY_FOR_TEST))
				.andExpect(status().isOk()).andExpect(jsonPath("$.createdAt").value("19.21.2019"))
				.andExpect(jsonPath("$.result").value(300.42)).andExpect(jsonPath("$.query.count").value(1))
				.andExpect(jsonPath("$.results[\"USD_PKR\"].id").value(1))
				.andExpect(jsonPath("$.results[\"USD_PKR\"].to").value("PKR"))
				.andExpect(jsonPath("$.results[\"USD_PKR\"].fr").value("USD"))
				.andExpect(content().contentType(MEDIA_TYPE_JSON_UTF8)).andDo(print());
	}

	@Test
	public void currencyConvertByDb() throws Exception {
		mockDbService();
		mockMvc.perform(get(API_CURRENCY_CONVERT + TestDataHelper.CURRENCY_API_QUERY_FOR_TEST))
				.andExpect(status().isOk()).andExpect(jsonPath("$.result").value(3.45))
				.andExpect(jsonPath("$.createdAt").value("19.21.2019"))
				.andExpect(content().contentType(MEDIA_TYPE_JSON_UTF8)).andDo(print());
	}
	
	@Test
	public void currencyConvertByDbDetailed() throws Exception {
		mockDbService();
		mockMvc.perform(get(API_CURRENCY_CONVERT+ API_CURRENCY_CONVERT_DETAILS + TestDataHelper.CURRENCY_API_QUERY_FOR_TEST))
				.andExpect(status().isOk()).andExpect(jsonPath("$.createdAt").value("19.21.2019"))
				.andExpect(jsonPath("$.result").value(300.42)).andExpect(jsonPath("$.query.count").value(1))
				.andExpect(jsonPath("$.results[\"USD_PKR\"].id").value(1))
				.andExpect(jsonPath("$.results[\"USD_PKR\"].to").value("PKR"))
				.andExpect(jsonPath("$.results[\"USD_PKR\"].fr").value("USD"))
				.andExpect(content().contentType(MEDIA_TYPE_JSON_UTF8)).andDo(print());
	}

	private void mockDbService() {
		Mockito.when(ccCacheService.read(Mockito.anyString())).thenReturn(null);
		Mockito.when(converterServiceImpl.currencyConvert(Mockito.any(CCRequestDto.class)))
				.thenReturn(TestDataHelper.getResponseConvertDto());
	}

	private void mockCCacheServiceDetailedByCache() {
		Mockito.when(responseConvertDtoMapper
				.responseConvertDtoFromCurrencyConverterEntity(Mockito.any(CurrencyConverterEntity.class)))
				.thenReturn(TestDataHelper.getResponseConvertDto());
	}

}
