package com.cary.scheduler.service;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 触发器日志
 * @author Cary
 *
 */
public class TriggerLogListener implements TriggerListener {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	private String name=TriggerLogListener.class.getName();
	
	public void setName(String name) {
		this.name=name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void triggerFired(Trigger trigger, JobExecutionContext context) {
		logger.debug("triggerFired trigger={}, context={}", trigger, context);
	}

	@Override
	public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
		logger.debug("vetoJobExecution trigger={}, context={}", trigger, context);
		return false;
	}

	@Override
	public void triggerMisfired(Trigger trigger) {
		logger.debug("triggerMisfired trigger={}", trigger);
	}

	@Override
	public void triggerComplete(Trigger trigger, JobExecutionContext context, int triggerInstructionCode) {
		logger.debug("triggerComplete trigger={}, context={}, triggerInstructionCode={}", new Object[] { trigger, context, triggerInstructionCode });
	}

	@Override
	public String toString() {
		return getName();
	}
}
