package com.cary.scheduler.core;

/**
 * 任务异常
 * 
 * @author Cary
 * 
 */
public class TaskException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TaskException(String string, Throwable throwable) {
		super(string, throwable);
	}

}
