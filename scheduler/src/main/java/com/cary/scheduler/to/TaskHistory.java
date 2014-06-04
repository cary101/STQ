package com.cary.scheduler.to;

import java.util.Date;

/**
* 任务历史
*
* @author Cary
*
*/

public class TaskHistory {
	public final static String table_Alian = "SCHEDULING_TASK_HISTORY";
	public final static String JOB_NAME = "jobName";
	public final static String JOB_DESCRIPTION = "jobDescription";
	public final static String FIRE_TIME = "fireTime";

	public TaskHistory() {

	}

	private Long id;

	private String jobName;

	private String jobGroup;

	private String jobDescription;

	private Date fireTime;

	private Date endTime;

	private Date nextFireTime;

	private long jobRunTime;

	private String result;

	private int refireCount;

	private String beanName;

	private String param;

	private String exceptionMessage;

	private String exceptionDetail;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public Date getFireTime() {
		return fireTime;
	}

	public void setFireTime(Date fireTime) {
		this.fireTime = fireTime;
	}

	public Date getNextFireTime() {
		return nextFireTime;
	}

	public void setNextFireTime(Date nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	public long getJobRunTime() {
		return jobRunTime;
	}

	public void setJobRunTime(long jobRunTime) {
		this.jobRunTime = jobRunTime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getRefireCount() {
		return refireCount;
	}

	public void setRefireCount(int refireCount) {
		this.refireCount = refireCount;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public String getExceptionDetail() {
		return exceptionDetail;
	}

	public void setExceptionDetail(String exceptionDetail) {
		this.exceptionDetail = exceptionDetail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
