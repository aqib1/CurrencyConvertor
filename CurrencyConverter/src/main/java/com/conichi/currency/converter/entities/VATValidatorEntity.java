package com.conichi.currency.converter.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author AQIB JAVED
 * @since 9/26/2019
 * @version 1.0
 */
@Entity
public class VATValidatorEntity {
	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "CompanyAddress")
	private String companyAddress;

	@Column(name = "CompanyName")
	private String companyName;

	@Column(name = "CountryCode")
	private String countryCode;

	@Column(name = "Query")
	private String query;

	@Column(name = "Success")
	private Boolean success;

	@Column(name = "IsValid")
	private Boolean valid;

	@Column(name = "IsValidFormat")
	private Boolean validFormat;

	@Column(name = "VATNumber")
	private String vatNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public Boolean getValidFormat() {
		return validFormat;
	}

	public void setValidFormat(Boolean validFormat) {
		this.validFormat = validFormat;
	}

	public String getVatNumber() {
		return vatNumber;
	}

	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}

	@Override
	public String toString() {
		return "VATValidatorEntity [id=" + id + ", companyAddress=" + companyAddress + ", companyName=" + companyName
				+ ", countryCode=" + countryCode + ", query=" + query + ", success=" + success + ", valid=" + valid
				+ ", validFormat=" + validFormat + ", vatNumber=" + vatNumber + "]";
	}

}
