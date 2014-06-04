package com.cary.scheduler.core;

/**
 * 
 *
 */
public interface TaskConstants {
	/**
	 * 任务和触发器组
	 */
	static String TASK_GROUP = "FTASKDEFAULT";
	/**
	 * 参数key
	 */
	static String TASK_PARAM_KEY = "jobDataCustomParamKey";
	/**
	 * 实例key
	 */
	static String TASK_BEAN_NAME_KEY = "springTaskBeanName";
	/**
	 * 结束时间key
	 */
	static String TASK_END_TIME_KEY = "taskEndTimeKey";

	/**
	 * SchedulerFactory引用applicationContext的key 需要与spring配置相同
	 */
	static String APPLICATION_CONTEXT_KEY = "applicationContextKey";
}
