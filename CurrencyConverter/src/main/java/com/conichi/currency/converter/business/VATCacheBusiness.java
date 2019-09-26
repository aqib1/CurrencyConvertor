package com.conichi.currency.converter.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conichi.currency.converter.constant.DataHelper;
import com.conichi.currency.converter.entities.VATValidatorEntity;
import com.conichi.currency.converter.exceptions.InvalidParamException;
import com.conichi.currency.converter.mapper.VATValidatorEntityMapper;
import com.conichi.currency.converter.service.VATCacheService;
import com.example.model.ResponseShortVatDetailDto;
import com.example.model.ResponseVatDetailDto;

/**
 * @author AQIB JAVED
 * @since 9/26/2019
 * @version 1.0
 */
@Component
public class VATCacheBusiness {

	private static final Logger logger = LoggerFactory.getLogger(VATCacheBusiness.class);

	@Autowired
	private VATCacheService vatCacheService;

	@Autowired
	private VATValidatorEntityMapper mapper;

	/**
	 * @param vat
	 * @return
	 */
	public ResponseVatDetailDto validateVatDetails(String vat) {
		if (DataHelper.isNullOrEmptyString(vat))
			throw new InvalidParamException("vat id can not be null or empty");
		logger.info("reading vat details from cache against vat id [" + vat + "]");
		return mapper.vATValidatorEntityToResponseVatDetailDto(vatCacheService.read(vat));
	}

	/**
	 * @param vat
	 * @return
	 */
	public ResponseShortVatDetailDto validateVat(String vat) {
		if (DataHelper.isNullOrEmptyString(vat))
			throw new InvalidParamException("vat id can not be null or empty");
		logger.info("reading vat details from cache against vat id [" + vat + "]");
		return mapper.vATValidatorEntityToResponseShortVatDetailDto(vatCacheService.read(vat));
	}

	/**
	 * @param response
	 */
	public void presist(ResponseVatDetailDto response) {
		logger.info("converting response to entity object against response => ["+response+"]");
		VATValidatorEntity entity = mapper.responseVatDetailDtoToVATValidatorEntity(response);
		logger.info("Entity created VATValidatorEntity ["+entity+"]\n writing to cache!!");
		vatCacheService.writeCache(entity);
	}
}
