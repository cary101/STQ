package com.cary.stq.base.exception;

public class DaoRuntimeException extends Exception {
	
	private static final long serialVersionUID = -7279344262005038153L;

	public DaoRuntimeException(Throwable t) {
		super(t);
	}

	public DaoRuntimeException(String errorMessage) {
		super(errorMessage);
	}
	
	public DaoRuntimeException(String errorMessage, Throwable t) {
		super(errorMessage, t);
	}
}
