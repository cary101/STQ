//package com.cary.stq.scheduling;
//
//import com.founder.fasf.beans.Criteria;
//import com.founder.fasf.beans.OP;
//import com.founder.fasf.util.DateUtil;
//import com.founder.fasf.util.StringUtil;
//import com.founder.rhip.scheduling.entity.TaskHistory;
//
//import java.util.Date;
//
///**
// * @author Cary
// *
// */
//public class TaskHistoryQueryForm {
//	private String taskName;
//	private String taskDescription;
//	private Date startDate;
//	private Date endDate;
//
//	public Criteria toCriteria() {
//		Criteria criteria = new Criteria();
//
//		if (StringUtil.isNotEmpty(taskName)) {
//			criteria.add(TaskHistory.JOB_NAME, OP.LIKE, taskName);
//		}
//		if (StringUtil.isNotEmpty(taskDescription)) {
//			criteria.add(TaskHistory.JOB_DESCRIPTION, OP.LIKE, taskDescription);
//		}
//
//		if (null != startDate && null == endDate) {
//			criteria.add(TaskHistory.FIRE_TIME, OP.GE, DateUtil.makeTimeToZero(startDate));
//		} else if (null == startDate && null != endDate) {
//			criteria.add(TaskHistory.FIRE_TIME, OP.LE, DateUtil.makeTimeToMax(endDate));
//		}
//		if (null != startDate && null != endDate) {
//			criteria.add(TaskHistory.FIRE_TIME, OP.BETWEEN, new Date[] { DateUtil.makeTimeToZero(startDate), DateUtil.makeTimeToMax(endDate) });
//		}
//
//		return criteria;
//	}
//
//	public String getTaskName() {
//		return taskName;
//	}
//
//	public void setTaskName(String taskName) {
//		this.taskName = taskName;
//	}
//
//	public String getTaskDescription() {
//		return taskDescription;
//	}
//
//	public void setTaskDescription(String taskDescription) {
//		this.taskDescription = taskDescription;
//	}
//
//	public Date getStartDate() {
//		return startDate;
//	}
//
//	public void setStartDate(Date startDate) {
//		this.startDate = startDate;
//	}
//
//	public Date getEndDate() {
//		return endDate;
//	}
//
//	public void setEndDate(Date endDate) {
//		this.endDate = endDate;
//	}
//
//}
