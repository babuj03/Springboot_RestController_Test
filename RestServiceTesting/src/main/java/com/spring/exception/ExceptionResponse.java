package com.spring.exception;

import java.time.LocalDate;

public class ExceptionResponse {
	
	String message;
	String details;
	LocalDate  dates =LocalDate.now();
	int status;
	
	
	public ExceptionResponse() {
		super();
	}
	public ExceptionResponse(String message, String details, LocalDate dates, int status) {
		super();
		this.message = message;
		this.details = details;
		this.dates = dates;
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public LocalDate getDates() {
		return dates;
	}
	public void setDates(LocalDate dates) {
		this.dates = dates;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ErrorResponse [message=" + message + ", details=" + details + ", dates=" + dates + ", status=" + status
				+ "]";
	}
	
	
	
	

}
