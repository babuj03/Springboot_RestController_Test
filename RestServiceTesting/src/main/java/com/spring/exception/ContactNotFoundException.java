package com.spring.exception;

public class ContactNotFoundException  extends RuntimeException {

	String errorMessage;
	
	public ContactNotFoundException(String message){
		super(message);
		this.errorMessage = message;
	}

}
