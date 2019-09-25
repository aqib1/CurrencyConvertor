package com.conichi.currency.converter.controller.advices;

import java.time.LocalDateTime;
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
import com.example.model.ResponseError;

@RestControllerAdvice
public class ControllerExceptionsAdvice {

	private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionsAdvice.class);

	@ExceptionHandler(value = { BadInternalServerException.class })
	public ResponseEntity<ResponseError> handleBadInternalServerException(RuntimeException e, WebRequest wr) {
		String error = Optional.of(e.getMessage()).orElse(e.getClass().getName())
				+ " [Bad internal server exception! => (BadInternalServerException)]";
		ResponseError errorResponse = new ResponseError().createdAt(LocalDateTime.now().toString())
				.detailedMessage(error).errorCode(HttpStatus.BAD_REQUEST.value())
				.exceptionName(BadInternalServerException.class.getName()).errorMessage(e.getMessage());
		logger.error(errorResponse.toString(), e);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { InvalidParamException.class })
	public ResponseEntity<ResponseError> handleInvalidParamException(RuntimeException e, WebRequest wr) {
		String error = Optional.of(e.getMessage()).orElse(e.getClass().getName())
				+ " [Invalid Params! => (InvalidParamException)]";
		ResponseError errorResponse = new ResponseError().createdAt(LocalDateTime.now().toString())
				.detailedMessage(error).errorCode(HttpStatus.BAD_REQUEST.value())
				.exceptionName(InvalidParamException.class.getName()).errorMessage(e.getMessage());
		logger.error(errorResponse.toString(), e);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { InvalidRequestException.class })
	public ResponseEntity<ResponseError> handleInvalidRequestException(RuntimeException e, WebRequest wr) {
		String error = Optional.of(e.getMessage()).orElse(e.getClass().getName())
				+ " [Invalid Request! => (InvalidRequestException)]";
		ResponseError errorResponse = new ResponseError().createdAt(LocalDateTime.now().toString())
				.detailedMessage(error).errorCode(HttpStatus.BAD_REQUEST.value())
				.exceptionName(InvalidRequestException.class.getName()).errorMessage(e.getMessage());
		logger.error(errorResponse.toString(), e);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { InvalidResponseException.class })
	public ResponseEntity<ResponseError> handleInvalidResponseException(RuntimeException e, WebRequest wr) {
		String error = Optional.of(e.getMessage()).orElse(e.getClass().getName())
				+ " [Invalid Response! => (InvalidResponseException)]";
		ResponseError errorResponse = new ResponseError().createdAt(LocalDateTime.now().toString())
				.detailedMessage(error).errorCode(HttpStatus.BAD_REQUEST.value())
				.exceptionName(InvalidResponseException.class.getName()).errorMessage(e.getMessage());
		logger.error(errorResponse.toString(), e);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { CachePresistException.class })
	public ResponseEntity<ResponseError> handleCachePresistException(RuntimeException e, WebRequest wr) {
		String error = Optional.of(e.getMessage()).orElse(e.getClass().getName())
				+ " [Cache presistence failed! => (CachePresistException)]";
		ResponseError errorResponse = new ResponseError().createdAt(LocalDateTime.now().toString())
				.detailedMessage(error).errorCode(HttpStatus.EXPECTATION_FAILED.value())
				.exceptionName(CachePresistException.class.getName()).errorMessage(e.getMessage());
		logger.error(errorResponse.toString(), e);
		return new ResponseEntity<>(errorResponse, HttpStatus.EXPECTATION_FAILED);
	}

	@ExceptionHandler(value = { CacheRefresherException.class })
	public ResponseEntity<ResponseError> handleCacheRefresherException(RuntimeException e, WebRequest wr) {
		String error = Optional.of(e.getMessage()).orElse(e.getClass().getName())
				+ " [Cache refresher exception! => (CacheRefresherException)]";
		ResponseError errorResponse = new ResponseError().createdAt(LocalDateTime.now().toString())
				.detailedMessage(error).errorCode(HttpStatus.EXPECTATION_FAILED.value())
				.exceptionName(CacheRefresherException.class.getName()).errorMessage(e.getMessage());
		logger.error(errorResponse.toString(), e);
		return new ResponseEntity<>(errorResponse, HttpStatus.EXPECTATION_FAILED);
	}
}
