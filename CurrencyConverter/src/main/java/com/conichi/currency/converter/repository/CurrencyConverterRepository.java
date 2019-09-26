package com.conichi.currency.converter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.conichi.currency.converter.entities.CurrencyConverterEntity;

/**
 * @author AQIB JAVED
 * @since 9/26/2019
 * @version 1.0
 */
public interface CurrencyConverterRepository extends JpaRepository<CurrencyConverterEntity, Long> {

	/**
	 * @param query
	 * @return
	 */
	@Query("SELECT c FROM CurrencyConverterEntity c WHERE c.query = ?1")
	CurrencyConverterEntity findByQuery(String query);
	
}
