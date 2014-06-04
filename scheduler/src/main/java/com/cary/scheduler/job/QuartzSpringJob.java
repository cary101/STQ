package com.cary.scheduler.job;


import com.cary.scheduler.core.Task;
import com.cary.scheduler.core.TaskConstants;
import com.cary.scheduler.core.TaskException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

import java.util.Map;

/**
 * Quartz1.8.x Job 执行spring 实例
 * 
 * @author Cary
 * 
 */
public class QuartzSpringJob implements Job {

	private final Logger log = LoggerFactory.getLogger(getClass());

	public final void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			Task task = getTask(context);
			runTask(task, context);
		} catch (Exception ex) {
			throw new JobExecutionException(ex);
		}
	}

	/**
	 * 执行任务
	 * 
	 * @param task
	 * @param context
	 */
	protected void runTask(Task task, JobExecutionContext context) {
		if (null != task) {
			@SuppressWarnings("unchecked")
			Map<String, Object> data = context.getMergedJobDataMap();
			task.run(data);
		} else {
			log.warn("任务获取失败,任务未执行");
		}
	}

	/**
	 * 获取任务实例
	 * 
	 * @param context
	 * @return
	 * @throws org.quartz.SchedulerException
	 */
	protected Task getTask(JobExecutionContext context) throws SchedulerException {
		// Scheduler保存了ApplicationContext的引用
		// 注意key在spring对SchedulerFactory的配置中指定
		ApplicationContext applicationContext = (ApplicationContext) context.getScheduler().getContext().get(TaskConstants.APPLICATION_CONTEXT_KEY);
		// 从JobDataMap获取beanName
		String name = (String) context.getJobDetail().getJobDataMap().get(TaskConstants.TASK_BEAN_NAME_KEY);
		Task task;
		try {
			// 获取到spring容器中的任务实例
			task = applicationContext.getBean(name, Task.class);
		} catch (BeansException e) {
			String message = "获取task实例失败,bean名:".concat(name);
			log.error(message, e);
			// task = null;
			throw new TaskException(message, e);
		}
		return task;
	}

}
