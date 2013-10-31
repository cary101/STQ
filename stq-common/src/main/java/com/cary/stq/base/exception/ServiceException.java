package com.cary.stq.base.exception;

public class ServiceException extends Exception {
	
	private static final long serialVersionUID = -7279344262005038153L;

	public ServiceException(Throwable t) {
		super(t);
	}

	public ServiceException(String errorMessage) {
		super(errorMessage);
	}
	
	public ServiceException(String errorMessage, Throwable t) {
		super(errorMessage, t);
	}
}
