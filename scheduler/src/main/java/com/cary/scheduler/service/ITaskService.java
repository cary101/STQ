package com.cary.scheduler.service;

import com.cary.scheduler.to.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 任务服务
 *
 * @author Cary
 */
@SuppressWarnings("deprecation")
public interface ITaskService {

    /**
     * 添加任务
     *
     * @param param the param
     * @param taskDescription the task description
     * @param taskBeanName the task bean name
     * @param cronExpression the cron expression
     * @return string
     */
	String addTask(String param, String taskDescription, String taskBeanName, String cronExpression);

    /**
     * 添加触发器
     *
     * @param taskName the task name
     * @param startTime the start time
     * @param stopTime the stop time
     * @param cronExpression the cron expression
     */
	void addTrigger(String taskName, Date startTime, Date stopTime, String cronExpression);

    /**
     * 修改触发器
     *
     * @param taskName the task name
     * @param cronExpression the cron expression
     */
	@Deprecated
	void changeTime(String taskName, String cronExpression);

    /**
     * 获取任务列表
     *
     * @param taskName the task name
     * @param taskDescription the task description
     * @return job list
     */
	List<JobDetailVo> getJobList(String taskName, String taskDescription);

    /**
     * 获取任务列表
     *
     * @param page the page
     * @param criteria the criteria
     * @return job page list
     */
	@Deprecated
	List<Quartz> getJobPageList(Object page, Map criteria);

    /**
     * 获取任务
     *
     * @param taskName the task name
     * @return task
     */
	JobDetailVo getTask(String taskName);

    /**
     * 获取全部任务实例
     *
     * @return task bean names
     */
	List<String> getTaskBeanNames();

    /**
     * 获取任务历史列表,分页显示
     *
     * @param page the page
     * @param criteria the criteria
     * @return task histories
     */
	List<TaskHistory> getTaskHistories(Object page, Map criteria);

    /**
     * 获取任务详细
     *
     * @param taskName the task name
     * @return task info
     */
	@Deprecated
	Quartz getTaskInfo(String taskName);

    /**
     * 获取任务的所有触发器
     *
     * @param taskName the task name
     * @return task triggers
     */
	List<TriggerVo> getTaskTriggers(String taskName);

    /**
     * 检查cron表达式
     *
     * @param cronExpression the cron expression
     * @return boolean
     */
	boolean isValidCronExpression(String cronExpression);

    /**
     * 暂停任务
     *
     * @param taskName the task name
     */
	void pauseTask(String taskName);

    /**
     * 暂停触发器
     *
     * @param triggerName the trigger name
     */
	void pauseTrigger(String triggerName);

    /**
     * 删除任务
     *
     * @param taskName the task name
     */
	void removeTask(String taskName);

    /**
     * 删除触发器
     *
     * @param triggerName the trigger name
     */
	void removeTrigger(String triggerName);

    /**
     * 恢复任务
     *
     * @param taskName the task name
     */
	void resumeTask(String taskName);

    /**
     * 恢复触发器
     *
     * @param triggerName the trigger name
     */
	void resumeTrigger(String triggerName);

    /**
     * 立即执行任务
     *
     * @param taskName the task name
     */
	void runTask(String taskName);

    /**
     * 修改任务
     *
     * @param taskName the task name
     * @param taskDescription the task description
     * @param taskBeanName the task bean name
     * @param param the param
     */
	void updateTask(String taskName, String taskDescription, String taskBeanName, String param);

    /**
     * 启动Scheduler
     */
	void start();

    /**
     * 暂停Scheduler
     */
	void stop();

    /**
     * 获取Scheduler状态
     * @return scheduler status
     */
	SchedulerVo getSchedulerStatus();

    /**
     * Import task.
     *
     * @param xml the xml
     */
    void importTask(String xml);

    /**
     * Export task.
     *
     * @return the string
     */
    String exportTask();

    /**
     * Remove all task.
     */
    void removeAllTask();

    /**
     * Gets task bean deatils.
     *
     * @return the task bean deatils
     */
    Map<String, Map<String, String>> getTaskBeanDeatils();

}
