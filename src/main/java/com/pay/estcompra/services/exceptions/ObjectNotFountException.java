package com.pay.estcompra.services.exceptions;

public class ObjectNotFountException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFountException(String msg) {
		super(msg);
	}
	
	public ObjectNotFountException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
