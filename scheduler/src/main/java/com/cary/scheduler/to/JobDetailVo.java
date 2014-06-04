package com.cary.scheduler.to;

import com.cary.scheduler.core.TaskConstants;
import org.quartz.JobDetail;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * 任务详细DTO
 * @author liuk
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class JobDetailVo {

	private String name;
	private String group;
	private String description;
	private String beanName;
	private String param;
	@XmlElement(name = "trigger")
	@XmlElementWrapper(name = "triggers")
	private List<com.cary.scheduler.to.TriggerVo> triggers;//该任务的关联触发器

	public void copyFrom(JobDetail detail) {
		this.setName(detail.getName());
		this.setGroup(detail.getGroup());
		this.setDescription(detail.getDescription());
		this.setBeanName(detail.getJobDataMap().getString(TaskConstants.TASK_BEAN_NAME_KEY));
		this.setParam(detail.getJobDataMap().getString(TaskConstants.TASK_PARAM_KEY));
	}

	public List<com.cary.scheduler.to.TriggerVo> getTriggers() {
		return triggers;
	}

	public void setTriggers(List<com.cary.scheduler.to.TriggerVo> triggers) {
		this.triggers = triggers;
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

	public void setGroup(String groupName) {
		this.group = groupName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
}
