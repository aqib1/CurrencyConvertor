package com.conichi.currency.converter;

import static com.conichi.currency.converter.constant.CCHelper.COMPONENT_SCAN_PATH;
import static com.conichi.currency.converter.constant.CCHelper.COMPONENT_SCAN_PATH_REPOSITORY;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(COMPONENT_SCAN_PATH)
@EnableJpaRepositories(COMPONENT_SCAN_PATH_REPOSITORY)
public class CurrencyConverterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConverterApplication.class, args);
	}

}
