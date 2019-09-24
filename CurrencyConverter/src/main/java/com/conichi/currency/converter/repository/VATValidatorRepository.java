package com.conichi.currency.converter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.conichi.currency.converter.entities.VATValidatorEntity;

public interface VATValidatorRepository extends JpaRepository<VATValidatorEntity, Long> {

	@Query("SELECT c FROM VATValidatorEntity c WHERE c.query = ?1")
	VATValidatorEntity findByQuery(String query);
}
