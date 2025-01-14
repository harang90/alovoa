package com.nonononoki.alovoa.component;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.HtmlUtils;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

	@ExceptionHandler
	protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
		String exceptionMessage = ex.getMessage();
		LOGGER.error(ExceptionUtils.getStackTrace(ex));
		exceptionMessage = exceptionMessage == null ? null : HtmlUtils.htmlEscape(exceptionMessage);
		return handleExceptionInternal(ex, exceptionMessage, new HttpHeaders(),
				HttpStatus.CONFLICT, request);
	}
}