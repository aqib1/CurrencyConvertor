package com.conichi.currency.converter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.conichi.currency.converter.entities.VATValidatorEntity;

/**
 * @author AQIB JAVED
 * @since 9/26/2019
 * @version 1.0
 */
public interface VATValidatorRepository extends JpaRepository<VATValidatorEntity, Long> {

	/**
	 * @param query
	 * @return
	 */
	@Query("SELECT c FROM VATValidatorEntity c WHERE c.query = ?1")
	VATValidatorEntity findByQuery(String query);
}
