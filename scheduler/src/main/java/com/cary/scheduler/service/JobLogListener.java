package com.cary.scheduler.service;


import com.cary.scheduler.core.TaskConstants;
import com.cary.scheduler.dao.ITaskHistoryDao;
import com.cary.scheduler.to.TaskHistory;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 任务日志
 * 
 * @author Cary
 * 
 */
public class JobLogListener implements JobListener {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	private ITaskHistoryDao taskHistoryDao;

	private String name = TriggerLogListener.class.getName();

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		logger.info("jobToBeExecuted context={}", context);
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
		logger.debug("jobExecutionVetoed context={}", context);
	}

	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		logger.info("jobWasExecuted context={}, jobException={}", context, jobException);
		// 保存到数据库
		JobDetail jobDetail = context.getJobDetail();
		try {
			saveLog(context, jobException);
		} catch (Exception e) {
			logger.error("执行日志保存失败,任务:" + jobDetail.getDescription(), e);
		}
	}

	private void saveLog(JobExecutionContext context, JobExecutionException jobException) {
		TaskHistory taskHistory = new TaskHistory();
		JobDetail jobDetail = context.getJobDetail();
		taskHistory.setJobName(jobDetail.getName());
		taskHistory.setJobGroup(jobDetail.getGroup());
		taskHistory.setJobDescription(jobDetail.getDescription());
		taskHistory.setFireTime(context.getFireTime());
		taskHistory.setNextFireTime(context.getNextFireTime());
		taskHistory.setJobRunTime(context.getJobRunTime());
		taskHistory.setResult(null != context.getResult() ? context.getResult().toString() : "");
		taskHistory.setRefireCount(context.getRefireCount());
		taskHistory.setBeanName(context.getMergedJobDataMap().getString(TaskConstants.TASK_BEAN_NAME_KEY));
		taskHistory.setParam(context.getMergedJobDataMap().getString(TaskConstants.TASK_PARAM_KEY));
		// endTime = (Date)
		// context.getMergedJobDataMap().get(TaskConstants.TASK_END_TIME_KEY);
		if (null != jobException) {
			taskHistory.setExceptionMessage(getExceptionMessage(jobException));
			String detail = getRootExceptionDetail(jobException);
			taskHistory.setExceptionDetail(detail);
		}
//		taskHistoryDao.insert(taskHistory);
	}

	private String getHostName() {
		String host = "";
		try {
			// 获取计算机名
			String name = InetAddress.getLocalHost().getHostName();
			// 获取IP地址
			String ip = InetAddress.getLocalHost().getHostAddress();
			host = name + ip;
		} catch (UnknownHostException e) {
			logger.error("", e);
		}
		return host;
	}

	private String getRootExceptionDetail(JobExecutionException jobException) {
		String detail = getHostName();
		Throwable root = ExceptionUtils.getRootCause(jobException);
		if (null != root) {
			detail += ExceptionUtils.getStackTrace(root);
		} else {
			detail += ExceptionUtils.getStackTrace(jobException);
		}
		logger.error(detail);
		// 太长截取
		if (detail.length() >= 1000) {
			detail = detail.substring(0, 999);
		}
		return detail;
	}

	private String getExceptionMessage(JobExecutionException jobException) {
		String message = jobException.getMessage();
		message = message.substring(message.indexOf(":") + 1);
		logger.error(message);
		if (message.length() > 100) {
			message = message.substring(0, 100);
		}
		return message;
	}

	@Override
	public String toString() {
		return getName();
	}

	public void setTaskHistoryDao(ITaskHistoryDao taskHistoryDao) {
		this.taskHistoryDao = taskHistoryDao;
	}
}
