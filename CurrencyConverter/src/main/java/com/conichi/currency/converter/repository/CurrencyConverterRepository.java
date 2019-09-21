package com.conichi.currency.converter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.conichi.currency.converter.entities.CurrencyConverterEntity;

public interface CurrencyConverterRepository extends JpaRepository<CurrencyConverterEntity, Long> {

	@Query("SELECT c FROM CurrencyConverterEntity c WHERE c.query = ?1")
	CurrencyConverterEntity findByQuery(String query);
	
}
