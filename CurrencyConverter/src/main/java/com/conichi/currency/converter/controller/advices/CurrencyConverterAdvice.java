package com.conichi.currency.converter.controller.advices;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.conichi.currency.converter.exceptions.BadInternalServerException;
import com.conichi.currency.converter.exceptions.CachePresistException;
import com.conichi.currency.converter.exceptions.CacheRefresherException;
import com.conichi.currency.converter.exceptions.InvalidParamException;
import com.conichi.currency.converter.exceptions.InvalidRequestException;
import com.conichi.currency.converter.exceptions.InvalidResponseException;

@RestControllerAdvice
public class CurrencyConverterAdvice {

	private static final Logger logger = LoggerFactory.getLogger(CurrencyConverterAdvice.class);

	@ExceptionHandler(value = { BadInternalServerException.class })
	public ResponseEntity<String> handleBadInternalServerException(RuntimeException e, WebRequest wr) {
		String error = Optional.of(e.getMessage()).orElse(e.getClass().getName())
				+ "\n[Bad internal server exception! => (BadInternalServerException)]";
		logger.error(error, e);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { InvalidParamException.class })
	public ResponseEntity<String> handleInvalidParamException(RuntimeException e, WebRequest wr) {
		String error = Optional.of(e.getMessage()).orElse(e.getClass().getName())
				+ "\n[Invalid Params! => (InvalidParamException)]";
		logger.error(error, e);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { InvalidRequestException.class })
	public ResponseEntity<String> handleInvalidRequestException(RuntimeException e, WebRequest wr) {
		String error = Optional.of(e.getMessage()).orElse(e.getClass().getName())
				+ "\n[Invalid Request! => (InvalidRequestException)]";
		logger.error(error, e);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { InvalidResponseException.class })
	public ResponseEntity<String> handleInvalidResponseException(RuntimeException e, WebRequest wr) {
		String error = Optional.of(e.getMessage()).orElse(e.getClass().getName())
				+ "\n[Invalid Response! => (InvalidResponseException)]";
		logger.error(error, e);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { CachePresistException.class })
	public ResponseEntity<String> handleCachePresistException(RuntimeException e, WebRequest wr) {
		String error = Optional.of(e.getMessage()).orElse(e.getClass().getName())
				+ "\n[Cache presistence failed! => (CachePresistException)]";
		logger.error(error, e);
		return new ResponseEntity<>(error, HttpStatus.EXPECTATION_FAILED);
	}

	@ExceptionHandler(value = { CacheRefresherException.class })
	public ResponseEntity<String> handleCacheRefresherException(RuntimeException e, WebRequest wr) {
		String error = Optional.of(e.getMessage()).orElse(e.getClass().getName())
				+ "\n[Cache refresher exception! => (CacheRefresherException)]";
		logger.error(error, e);
		return new ResponseEntity<>(error, HttpStatus.EXPECTATION_FAILED);
	}
}