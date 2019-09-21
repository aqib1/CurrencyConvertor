package com.conichi.currency.converter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.conichi.currency.converter.entities.CurrencyConverterEntity;

public interface CurrencyConverterRepository extends JpaRepository<CurrencyConverterEntity, Long> {

	
}
