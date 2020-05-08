package com.codingvine.core.base.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.PropertyAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.codingvine.core.base.common.dto.ResponseDto;
import com.codingvine.core.base.utils.CodingVineUtils;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import lombok.extern.log4j.Log4j2;

/***
 * @author debendra.dhinda
 * Note : the HttpStatus for the respective type to be customize....
 * */


@Log4j2
@RestControllerAdvice
public class ExceptionInterceptor {

	/************************ Framework Specific Exceptions ************************/

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody <T> ResponseDto<T> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

		String exceptionId = CodingVineUtils.generateUniqueId();
		log.error("Got MethodArgumentNotValidException for exceptionId: {} with Message: {}", exceptionId, e.getMessage());

		FieldError fieldError = e.getBindingResult().getFieldError();

		String errorMessage = fieldError.getDefaultMessage();

		if (StringUtils.isBlank(errorMessage)) {
			errorMessage = "Invalid Value: " + fieldError.getRejectedValue() + " for Field: " + fieldError.getField();
		}

		return ResponseDto.failure(errorMessage, exceptionId);

	}

	
	
	@ExceptionHandler(PropertyAccessException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public <T> ResponseDto<T> handlePropertyAccessException(PropertyAccessException e) {

		String exceptionId = CodingVineUtils.generateUniqueId();
		log.error("Got PropertyAccessException for exceptionId: " + exceptionId, e);

		String errorMessgae = "Incorrect Value For Parameter: " + e.getPropertyName() + " in Request. Dirty Value: " + e.getValue();

		return ResponseDto.failure(errorMessgae, exceptionId);
	}

	
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public <T> ResponseDto<T> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {

		String exceptionId = CodingVineUtils.generateUniqueId();
		log.error("Got MethodArgumentTypeMismatchException for exceptionId: {} with Message: {}", exceptionId, e.getMessage());

		String errorMessage = "Incorrect Type For Parameter: " + e.getName() + " in Request. Expected: " + e.getRequiredType() + ", Found: " + e.getValue();

		return ResponseDto.failure(errorMessage, exceptionId);
	}

	
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public <T> ResponseDto<T> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {

		String exceptionId = CodingVineUtils.generateUniqueId();
		log.error("Got MissingServletRequestParameterException for exceptionId: " + exceptionId, e);

		String errorMessage = "Missing Parameter: " + e.getParameterName() + " in Request";

		return ResponseDto.failure(errorMessage, exceptionId);
	}

	
	
	@ExceptionHandler(BindException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public <T> ResponseDto<T> handleBindException(BindException e) {

		String exceptionId = CodingVineUtils.generateUniqueId();
		log.error("Got MissingServletRequestParameterException for exceptionId: " + exceptionId, e);

		BindingResult bindingResult = e.getBindingResult();

		StringBuilder fieldErrors = new StringBuilder();

		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			fieldErrors = fieldErrors.append("{FieldName: ").append(fieldError.getField()).append(", RejectedValue: ").append(fieldError.getRejectedValue()).append("} ");
		}

		String errorMessage = "Found: " + bindingResult.getErrorCount() + " Errors in Request. Fields With Error: [" + fieldErrors.toString() + "]";

		return ResponseDto.failure(errorMessage, exceptionId);
	}
	
	

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public <T> ResponseDto<T> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {

		String exceptionId = CodingVineUtils.generateUniqueId();
		log.error("Got HttpMessageNotReadableException for exceptionId: " + exceptionId, e);

		return ResponseDto.failure(e.getMessage(), exceptionId);
	}

	
	
	@ExceptionHandler(InvalidFormatException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public <T> ResponseDto<T> handleInvalidFormatException(InvalidFormatException e) {

		String exceptionId = CodingVineUtils.generateUniqueId();
		log.error("Got InvalidFormatException for exceptionId: " + exceptionId, e);

		return ResponseDto.failure(e.getMessage(), exceptionId);
	}

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public <T> ResponseDto<T> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {

		String exceptionId = CodingVineUtils.generateUniqueId();
		log.error("Got SQLIntegrityConstraintViolationException for exceptionId:" + exceptionId, e);

		return ResponseDto.failure(e.getMessage(), exceptionId);
	}

	
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public <T> ResponseDto<T> handleConstraintViolationException(ConstraintViolationException e) {

		String exceptionId = CodingVineUtils.generateUniqueId();
		log.error("Got ConstraintViolationException for exceptionId:" + exceptionId, e);

		return ResponseDto.failure(e.getMessage(), exceptionId);
	}

	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public <T> ResponseDto<T> handleDataIntegrityViolationException(DataIntegrityViolationException e) {

		String exceptionId = CodingVineUtils.generateUniqueId();
		log.error("Got DataIntegrityViolationException for exceptionId:" + exceptionId, e);

		return ResponseDto.failure(e.getMessage(), exceptionId);
	}



	@ExceptionHandler(CodingVineHttpException.class)
	@ResponseStatus(value = HttpStatus.FAILED_DEPENDENCY)
	
	public @ResponseBody <T> ResponseDto<T> handleStanzaHttpException(CodingVineHttpException e) {

		String exceptionId = CodingVineUtils.generateUniqueId();
		log.error("Got StanzaHttpException for exceptionId: " + exceptionId, e);

		return ResponseDto.failure(e.getMessage(), exceptionId);
	}


	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	
	public @ResponseBody <T> ResponseDto<T> handleException(Exception ex) {

		String exceptionId = CodingVineUtils.generateUniqueId();
		log.error("Got un-handled exception for exceptionId: " + exceptionId, ex);

		return ResponseDto.failure(ex.getMessage(), exceptionId);
	}

}