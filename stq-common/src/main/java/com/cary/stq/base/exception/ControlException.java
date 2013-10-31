package com.cary.stq.base.exception;

public class ControlException extends Exception {

	private static final long serialVersionUID = -7279344262005038153L;

	public ControlException(Throwable t) {
		super(t);
	}

	public ControlException(String errorMessage, Throwable t) {
		super(errorMessage, t);
	}
	
	public ControlException(String errorMessage) {
		super(errorMessage);
	}

}
