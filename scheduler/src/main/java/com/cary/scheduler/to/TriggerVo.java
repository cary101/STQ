package com.cary.scheduler.to;

import com.founder.rhip.scheduling.core.JaxbDateAdapter;
import org.quartz.CronTrigger;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

/**
 * 触发器DTO
 * 
 * @author liuk
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TriggerVo {
	@XmlJavaTypeAdapter(value = JaxbDateAdapter.class)
	private Date startTime;
	@XmlJavaTypeAdapter(value = JaxbDateAdapter.class)
	private Date stopTime;
	@XmlTransient
	private boolean volatility;
	@XmlTransient
	private int misFireInstruction;
	@XmlTransient
	private String name;
	@XmlTransient
	private String group;
	@XmlTransient
	private String jobName;
	@XmlTransient
	private String jobGroup;
	@XmlTransient
	private String description;
	@XmlTransient
	private Date nextFireTime;
	@XmlTransient
	private Date previousFireTime;
	@XmlTransient
	private String type;
	private String cronExpression;
	@XmlTransient
	private int status;

	public void copyFrom(Trigger trigger) {
		this.setName(trigger.getName());
		this.setGroup(trigger.getGroup());
		this.setDescription(trigger.getDescription());
		this.setJobGroup(trigger.getJobGroup());
		this.setJobName(trigger.getJobName());
		this.setMisFireInstruction(trigger.getMisfireInstruction());
		this.setStartTime(trigger.getStartTime());
		this.setStopTime(trigger.getEndTime());
		this.setNextFireTime(trigger.getNextFireTime());
		this.setPreviousFireTime(trigger.getPreviousFireTime());
		// 触发器类型
		if (trigger instanceof SimpleTrigger) {
			this.setType("SimpleTrigger");
		} else if (trigger instanceof CronTrigger) {
			this.setType("CronTrigger");
			this.setCronExpression(((CronTrigger) trigger).getCronExpression());
		}
	}

	public static String getTriggerType(Trigger trigger) {
		String type = null;
		if (trigger == null) {
			return null;
		}
		if (trigger.getClass() == SimpleTrigger.class) {
			type = "SimpleTrigger";
		} else if (trigger.getClass() == CronTrigger.class) {
			type = "CronTrigger";
		} else {
			type = trigger.getClass().getName();
		}
		return type;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStopTime() {
		return stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

	public boolean isVolatility() {
		return volatility;
	}

	public void setVolatility(boolean volatility) {
		this.volatility = volatility;
	}

	public int getMisFireInstruction() {
		return misFireInstruction;
	}

	public void setMisFireInstruction(int misFireInstruction) {
		this.misFireInstruction = misFireInstruction;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getNextFireTime() {
		return nextFireTime;
	}

	public void setNextFireTime(Date nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	public Date getPreviousFireTime() {
		return previousFireTime;
	}

	public void setPreviousFireTime(Date previousFireTime) {
		this.previousFireTime = previousFireTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

}
