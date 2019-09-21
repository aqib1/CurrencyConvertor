package com.conichi.currency.converter.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CurrencyConverterEntity {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "query", unique = true)
	private String query;

	@Column(name = "convert")
	private double convertionValue;

	@Column(name = "insertAt")
	public String insertedDate;

}
