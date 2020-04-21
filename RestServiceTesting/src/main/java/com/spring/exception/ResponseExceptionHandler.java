package com.spring.exception;


import java.time.LocalDate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

 
  @ExceptionHandler( ContactNotFoundException.class)
  public ResponseEntity<ExceptionResponse> handleBadRequest(
	      RuntimeException ex, WebRequest request) {
	  ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), request.getDescription(false), LocalDate.now(), HttpStatus.NOT_FOUND.value());
	  return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND);
  }
  
  
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
	  ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(), request.getDescription(false), LocalDate.now(), HttpStatus.INTERNAL_SERVER_ERROR.value());
	  return new ResponseEntity<Object>(exceptionResponse,  HttpStatus.INTERNAL_SERVER_ERROR);
  
   }

 
}





