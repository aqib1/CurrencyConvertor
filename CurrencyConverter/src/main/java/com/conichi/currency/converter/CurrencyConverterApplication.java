package com.conichi.currency.converter;

import static com.conichi.currency.converter.constant.DataHelper.COMPONENT_SCAN_PATH;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author AQIB JAVED
 * @version 1.0
 * @since 9/16/2019
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan(COMPONENT_SCAN_PATH)
@EnableScheduling
public class CurrencyConverterApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(CurrencyConverterApplication.class, args);
	}

}
