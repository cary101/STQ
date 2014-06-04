package com.cary.scheduler.to;

import java.util.Date;

public class SchedulerVo {
	private String schedulerName;
	private Date runningSince;
	private String numJobsExecuted;
	private String persistenceType;
	private String threadPoolSize;
	private String version;
	private String state;
	private String summary;

	public SchedulerVo() {
	}

	public String getSchedulerName() {
		return schedulerName;
	}

	public void setSchedulerName(String schedulerName) {
		this.schedulerName = schedulerName;
	}

	public Date getRunningSince() {
		return runningSince;
	}

	public void setRunningSince(Date runningSince) {
		this.runningSince = runningSince;
	}

	public String getNumJobsExecuted() {
		return numJobsExecuted;
	}

	public void setNumJobsExecuted(String numJobsExecuted) {
		this.numJobsExecuted = numJobsExecuted;
	}

	public String getPersistenceType() {
		return persistenceType;
	}

	public void setPersistenceType(String persistenceType) {
		this.persistenceType = persistenceType;
	}

	public String getThreadPoolSize() {
		return threadPoolSize;
	}

	public void setThreadPoolSize(String threadPoolSize) {
		this.threadPoolSize = threadPoolSize;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

}
