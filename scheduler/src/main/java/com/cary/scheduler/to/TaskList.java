package com.cary.scheduler.to;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "root")
public class TaskList {
	@XmlElement(name = "task")
	@XmlElementWrapper(name = "tasks")
	private List<JobDetailVo> tasks;

	public List<JobDetailVo> getTasks() {
		return tasks;
	}

	public void setTasks(List<JobDetailVo> tasks) {
		this.tasks = tasks;
	}
}
