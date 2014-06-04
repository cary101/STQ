package com.cary.scheduler.core;

import java.util.Map;

/**
 * 任务
 * 
 * @author Cary
 * 
 */
public interface Task {
	public void run(Map<String, Object> data);
}
