//package com.cary.scheduler.to;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import java.util.Date;
//
///**
// * 任务历史
// *
// * @author Cary
// *
// */
//@Entity
//@Table(name = "SCHEDULING_TASK_HISTORY")
//public class TaskHistory {
//	public final static String table_Alian = "SCHEDULING_TASK_HISTORY";
//	public final static String JOB_NAME = "jobName";
//	public final static String JOB_DESCRIPTION = "jobDescription";
//	public final static String FIRE_TIME = "fireTime";
//
//	public TaskHistory() {
//
//	}
//
//	@Id
//	@Column(name = "ID", columnDefinition = "NUMBER|数据库唯一编号（自增长）||", length = 11, nullable = true)
//	private Long id;
//
//	@Column(name = "JOB_NAME", columnDefinition = "VARCHAR2|任务id||", length = 50, nullable = true)
//	private String jobName;
//
//	@Column(name = "JOB_GROUP", columnDefinition = "VARCHAR2|任务组||", length = 50, nullable = true)
//	private String jobGroup;
//
//	@Column(name = "JOB_DESCRIPTION", columnDefinition = "VARCHAR2|任务描述||", length = 80, nullable = true)
//	private String jobDescription;
//
//	@Column(name = "FIRE_TIME", columnDefinition = "TIMESTAMP|触发时间||", nullable = true)
//	private Date fireTime;
//
//	@Column(name = "END_TIME", columnDefinition = "TIMESTAMP|结束时间||", nullable = true)
//	private Date endTime;
//
//	@Column(name = "NEXT_FIRE_TIME", columnDefinition = "TIMESTAMP|下次触发时间||", nullable = true)
//	private Date nextFireTime;
//
//	@Column(name = "JOB_RUN_TIME", columnDefinition = "NUMBER|执行时间||", length = 11, nullable = true)
//	private long jobRunTime;
//
//	@Column(name = "RESULT", columnDefinition = "VARCHAR2|结果||", length = 100, nullable = true)
//	private String result;
//
//	@Column(name = "REFIRE_COUNT", columnDefinition = "NUMBER|错失触发次数||", length = 11, nullable = true)
//	private int refireCount;
//
//	@Column(name = "BEAN_NAME", columnDefinition = "VARCHAR2|任务实例||", length = 60, nullable = true)
//	private String beanName;
//
//	@Column(name = "PARAM", columnDefinition = "VARCHAR2|任务参数||", length = 500, nullable = true)
//	private String param;
//
//	@Column(name = "EXCEPTION_MESSAGE", columnDefinition = "VARCHAR2|异常消息|", length = 100, nullable = true)
//	private String exceptionMessage;
//
//	@Column(name = "EXCEPTION_DETAIL", columnDefinition = "VARCHAR2|异常详细||", length = 1000, nullable = true)
//	private String exceptionDetail;
//
//	public String getJobName() {
//		return jobName;
//	}
//
//	public void setJobName(String jobName) {
//		this.jobName = jobName;
//	}
//
//	public String getJobGroup() {
//		return jobGroup;
//	}
//
//	public void setJobGroup(String jobGroup) {
//		this.jobGroup = jobGroup;
//	}
//
//	public String getJobDescription() {
//		return jobDescription;
//	}
//
//	public void setJobDescription(String jobDescription) {
//		this.jobDescription = jobDescription;
//	}
//
//	public Date getFireTime() {
//		return fireTime;
//	}
//
//	public void setFireTime(Date fireTime) {
//		this.fireTime = fireTime;
//	}
//
//	public Date getNextFireTime() {
//		return nextFireTime;
//	}
//
//	public void setNextFireTime(Date nextFireTime) {
//		this.nextFireTime = nextFireTime;
//	}
//
//	public long getJobRunTime() {
//		return jobRunTime;
//	}
//
//	public void setJobRunTime(long jobRunTime) {
//		this.jobRunTime = jobRunTime;
//	}
//
//	public String getResult() {
//		return result;
//	}
//
//	public void setResult(String result) {
//		this.result = result;
//	}
//
//	public int getRefireCount() {
//		return refireCount;
//	}
//
//	public void setRefireCount(int refireCount) {
//		this.refireCount = refireCount;
//	}
//
//	public String getBeanName() {
//		return beanName;
//	}
//
//	public void setBeanName(String beanName) {
//		this.beanName = beanName;
//	}
//
//	public String getParam() {
//		return param;
//	}
//
//	public void setParam(String param) {
//		this.param = param;
//	}
//
//	public String getExceptionMessage() {
//		return exceptionMessage;
//	}
//
//	public void setExceptionMessage(String exceptionMessage) {
//		this.exceptionMessage = exceptionMessage;
//	}
//
//	public String getExceptionDetail() {
//		return exceptionDetail;
//	}
//
//	public void setExceptionDetail(String exceptionDetail) {
//		this.exceptionDetail = exceptionDetail;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public Date getEndTime() {
//		return endTime;
//	}
//
//	public void setEndTime(Date endTime) {
//		this.endTime = endTime;
//	}
//
//}
