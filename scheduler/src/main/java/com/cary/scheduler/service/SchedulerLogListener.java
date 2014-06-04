package com.cary.scheduler.service;

import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.SchedulerListener;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 任务容器日志
 * @author Cary
 *
 */
public class SchedulerLogListener implements SchedulerListener {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void jobScheduled(Trigger trigger) {
		logger.debug("jobScheduled {}", trigger);
	}

	@Override
	public void jobUnscheduled(String triggerName, String triggerGroup) {
		logger.debug("jobUnscheduled {}.{}", triggerName, triggerGroup);
	}

	@Override
	public void triggerFinalized(Trigger trigger) {
		logger.debug("triggerFinalized {}", trigger);
	}

	@Override
	public void triggersPaused(String triggerName, String triggerGroup) {
		logger.debug("triggersPaused {}.{}", triggerName, triggerGroup);
	}

	@Override
	public void triggersResumed(String triggerName, String triggerGroup) {
		logger.debug("triggersResumed {}.{}", triggerName, triggerGroup);
	}

	@Override
	public void jobAdded(JobDetail jobDetail) {
		logger.debug("jobAdded {}", jobDetail);
	}

	@Override
	public void jobDeleted(String jobName, String groupName) {
		logger.debug("jobDeleted {}.{}", jobName, groupName);
	}

	@Override
	public void jobsPaused(String jobName, String jobGroup) {
		logger.debug("jobsPaused {}.{}", jobName, jobGroup);
	}

	@Override
	public void jobsResumed(String jobName, String jobGroup) {
		logger.debug("jobsResumed {}.{}", jobName, jobGroup);
	}

	@Override
	public void schedulerError(String msg, SchedulerException cause) {
		logger.debug("schedulerError msg={}, cause={}", msg, cause);
	}

	@Override
	public void schedulerInStandbyMode() {
		logger.debug("schedulerInStandbyMode");
	}

	@Override
	public void schedulerStarted() {
		logger.debug("schedulerStarted");
	}

	@Override
	public void schedulerShutdown() {
		logger.debug("schedulerShutdown");
	}

	@Override
	public void schedulerShuttingdown() {
		logger.debug("schedulerShuttingdown");
	}

	@Override
	public String toString() {
		return getClass().getName() + "@" + System.identityHashCode(this);
	}
}
