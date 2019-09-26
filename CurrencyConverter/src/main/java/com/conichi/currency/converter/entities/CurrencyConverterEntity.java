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
public class CurrencyConverterEntity {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "countResult")
	private Integer countResult;

	@Column(name = "query", unique = true)
	private String query;

	@Column(name = "convert")
	private double convertionValue;

	@Column(name = "insertAt")
	private String insertedDate;

	@Column(name = "resultId")
	private String resultId;

	@Column(name = "conversionValue")
	private Double conversionValue;

	@Column(name = "resultTo")
	private String resultTo;

	@Column(name = "resultFr")
	private String resultFr;

	@Column(name = "result")
	private Double result;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCountResult() {
		return countResult;
	}

	public void setCountResult(Integer countResult) {
		this.countResult = countResult;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public double getConvertionValue() {
		return convertionValue;
	}

	public void setConvertionValue(double convertionValue) {
		this.convertionValue = convertionValue;
	}

	public String getInsertedDate() {
		return insertedDate;
	}

	public void setInsertedDate(String insertedDate) {
		this.insertedDate = insertedDate;
	}

	public String getResultId() {
		return resultId;
	}

	public void setResultId(String resultId) {
		this.resultId = resultId;
	}

	public String getResultTo() {
		return resultTo;
	}

	public void setResultTo(String resultTo) {
		this.resultTo = resultTo;
	}

	public String getResultFr() {
		return resultFr;
	}

	public void setResultFr(String resultFr) {
		this.resultFr = resultFr;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	public Double getConversionValue() {
		return conversionValue;
	}

	public void setConversionValue(Double conversionValue) {
		this.conversionValue = conversionValue;
	}

	@Override
	public String toString() {
		return "CurrencyConverterEntity [id=" + id + ", countResult=" + countResult + ", query=" + query
				+ ", convertionValue=" + convertionValue + ", insertedDate=" + insertedDate + ", resultId=" + resultId
				+ ", conversionValue=" + conversionValue + ", resultTo=" + resultTo + ", resultFr=" + resultFr
				+ ", result=" + result + "]";
	}

}
