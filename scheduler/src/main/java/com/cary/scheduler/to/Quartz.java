package com.cary.scheduler.to;

import java.util.Date;

/**
* 任务和触发器DTO
*
* @author Cary
*
*/
@Deprecated
public class Quartz {

	private String jobName;

	private String triggerName;

	private Long nextFireTime;

	private Long prevFireTime;

	private String triggerState;

	private String triggerType;

	private Long startTime;

	private Long endTime;

	private String description;

	private String targetBeanName;

	private String targetMethod;

	private String cronExpression;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public Long getNextFireTime() {
		return nextFireTime;
	}

	public Date getNextFireDateTime() {
		return (nextFireTime <= 0) ? null : new Date(nextFireTime);
	}

	public void setNextFireTime(Long nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	public Long getPrevFireTime() {
		return prevFireTime;
	}

	public Date getPrevFireDateTime() {
		return (prevFireTime <= 0) ? null : new Date(prevFireTime);
	}

	public void setPrevFireTime(Long prevFireTime) {
		this.prevFireTime = prevFireTime;
	}

	public String getTriggerState() {
		return triggerState;
	}

	public String getTriggerStateName() {
		if ("ACQUIRED".equalsIgnoreCase(triggerState)) {
			return "运行中";
		}
		if ("PAUSED".equalsIgnoreCase(triggerState)) {
			return "暂停中";
		}
		if ("WAITING".equalsIgnoreCase(triggerState)) {
			return "等待中";
		}
		if ("ERROR".equalsIgnoreCase(triggerState)) {
			return "错误";
		}
		return "未知";
	}

	public void setTriggerState(String triggerState) {
		this.triggerState = triggerState;
	}

	public String getTriggerType() {
		return triggerType;
	}

	public void setTriggerType(String triggerType) {
		this.triggerType = triggerType;
	}

	public Long getStartTime() {
		return startTime;
	}

	public Date getStartDateTime() {
		return (startTime <= 0) ? null : new Date(startTime);
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public Date getEndDateTime() {
		return (endTime <= 0) ? null : new Date(endTime);
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getTargetBeanName() {
		return targetBeanName;
	}

	public void setTargetBeanName(String targetBeanName) {
		this.targetBeanName = targetBeanName;
	}

	public String getTargetMethod() {
		return targetMethod;
	}

	public void setTargetMethod(String targetMethod) {
		this.targetMethod = targetMethod;
	}

}
