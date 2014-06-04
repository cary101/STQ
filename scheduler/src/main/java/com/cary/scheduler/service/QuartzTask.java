package com.cary.scheduler.service;

import com.cary.scheduler.core.Task;
import com.cary.scheduler.core.TaskBean;
import com.cary.scheduler.core.TaskConstants;
import com.cary.scheduler.core.TaskException;
import com.cary.scheduler.job.QuartzSpringJob;
import com.cary.scheduler.to.*;
import com.cary.stq.utils.ObjectUtil;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.*;

/**
 * 任务调度服务,使用Quartz1.8.x实现
 * 
 * @author Cary
 * 
 */
@SuppressWarnings("deprecation")
@Service
public class QuartzTask implements ITaskService {

	@Autowired(required = false)
	protected Scheduler scheduler;

	@Autowired(required = false)
	private SchedulerFactoryBean schedulerFactoryBean;

//	@Autowired
//	private ITaskHistoryDao taskHistoryDao;

	@Autowired
    Map<String, Task> tasks = Collections.emptyMap();// 任务实例

	private List<String> taskNames;// 任务实例名称缓存

	private Map<String, Map<String, String>> taskWithDescs;

	private Logger logger = LoggerFactory.getLogger(QuartzTask.class);

	private void initTaskBeanInfo() {
		taskWithDescs = new HashMap(tasks.size());
		taskNames = new ArrayList(tasks.size());
		for (Map.Entry<String, Task> entry : tasks.entrySet()) {
			String beanName = entry.getKey();
			taskNames.add(beanName);
			Task bean = entry.getValue();
			Class<?> taskClass = bean.getClass();
			//如果被代理,则需要获取目标类型,否则无法获取注解
			if(AopUtils.isAopProxy(bean)){
				taskClass= AopUtils.getTargetClass(bean);
			}
			if (taskClass.isAnnotationPresent(TaskBean.class)) {
				Map<String, String> beanDef = new HashMap(3);
				TaskBean taskBeanAnn = taskClass.getAnnotation(TaskBean.class);
				String corn = taskBeanAnn.cron();
				String deacription = taskBeanAnn.description();
				beanDef.put("beanName", beanName);
				beanDef.put("cron", corn);
				beanDef.put("description", deacription);
				taskWithDescs.put(beanName, Collections.unmodifiableMap(beanDef));
			}
		}
		Collections.sort(taskNames);
		taskNames = Collections.unmodifiableList(taskNames);
		taskWithDescs = Collections.unmodifiableMap(taskWithDescs);
	}

	@PostConstruct
	public void init() {
		if (null == scheduler) {
			return;
		}
		// 缓存所有实例信息
		initTaskBeanInfo();
		// 添加全局Listener
		try {
			scheduler.addSchedulerListener(new SchedulerLogListener());

			if (null == scheduler.getGlobalJobListener(JobLogListener.class.getName())) {
				JobLogListener jobLogListener = new JobLogListener();
				// 执行历史
//				jobLogListener.setTaskHistoryDao(taskHistoryDao);
				scheduler.addGlobalJobListener(jobLogListener);
			}

			if (null == scheduler.getGlobalTriggerListener(TriggerLogListener.class.getName())) {
				scheduler.addGlobalTriggerListener(new TriggerLogListener());
			}

		} catch (SchedulerException e) {
			exceptionHandle(e);
		}
		//
		try {
			for (Class<?> clazz : suppotClasses) {
				JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
				jaxbCache.put(clazz, jaxbContext);
			}
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String addTask(String param, String taskDescription, String taskBeanName, String cronExpression) {
		Assert.hasText(taskDescription, "任务描述为空");
		checkTaskBeanName(taskBeanName);

		JobDetail detail = new JobDetail();
		String taskName = buildTaskName(taskDescription, taskBeanName);
		detail.setName(taskName);
		detail.setDescription(taskDescription);
		detail.setJobClass(QuartzSpringJob.class);
		// 此处将任务实例 beanName保存到JobDataMap,Job执行时根据此获取spring容器的实例
		detail.getJobDataMap().put(TaskConstants.TASK_BEAN_NAME_KEY, taskBeanName);
		// 保存参数到JobDataMap,任务执行时根据自己的逻辑解析
		// 目前使用字符串
		detail.getJobDataMap().put(TaskConstants.TASK_PARAM_KEY, param);
		// 默认任务组
		detail.setGroup(TaskConstants.TASK_GROUP);
		// 当任务没有关联的触发器时,不予删除
		detail.setDurability(true);
		//
		detail.setRequestsRecovery(false);
		// 易失性的, 设为false,可以持久化
		detail.setVolatility(false);
		try {
			scheduler.addJob(detail, true);
			if (ObjectUtil.isNotEmpty(cronExpression)) {
				checkCronExpression(cronExpression);
				String tirggerName = buildTriggerName(taskName);
				// 触发器使用和任务一样的默认组
				CronTrigger trigger = new CronTrigger(tirggerName, TaskConstants.TASK_GROUP, taskName, TaskConstants.TASK_GROUP);
				trigger.setCronExpression(cronExpression);
				scheduler.scheduleJob(trigger);
			}
		} catch (Exception e) {
			exceptionHandle(e);
		}
		return taskName;
	}

	@Override
	public void addTrigger(String taskName, Date startTime, Date stopTime, String cronExpression) {
		Assert.hasText(taskName, "任务名为空");
		checkCronExpression(cronExpression);
		// JobDetail detail = getTask(taskName);
		// Assert.notNull(detail, "根据任务任务名无法获取任务详细:".concat(taskName));
		String tirggerName = buildTriggerName(taskName);
		CronTrigger trigger = new CronTrigger(tirggerName, TaskConstants.TASK_GROUP, taskName, TaskConstants.TASK_GROUP);
		try {
			trigger.setCronExpression(cronExpression);
			if (null != startTime) {
				trigger.setStartTime(startTime);
			}
			if (null != stopTime) {
				trigger.setEndTime(stopTime);
			}
			scheduler.scheduleJob(trigger);
		} catch (Exception e) {
			exceptionHandle(e);
		}
	}

	@Override
	@Deprecated
	public void changeTime(String taskName, String cronExpression) {
		Assert.hasText(taskName, "任务名为空");
		checkCronExpression(cronExpression);
		try {
			CronTrigger newTrigger = new CronTrigger(buildTriggerName(taskName), TaskConstants.TASK_GROUP, taskName, TaskConstants.TASK_GROUP);
			newTrigger.setCronExpression(cronExpression);
			scheduler.rescheduleJob(newTrigger.getName(), TaskConstants.TASK_GROUP, newTrigger);
		} catch (Exception e) {
			exceptionHandle(e);
		}
	}

	@Override
	public List<JobDetailVo> getJobList(String taskName, String taskDescription) {
		List<JobDetailVo> jobList = null;
		try {
			String[] jobGroups = scheduler.getJobGroupNames();
			if (jobGroups.length > 0) {
				jobList = new ArrayList();
				for (String groupName : jobGroups) {
					String[] jobs = scheduler.getJobNames(groupName);
					for (String job : jobs) {
						JobDetail jobDetail = scheduler.getJobDetail(job, groupName);
						String desc = jobDetail.getDescription();
						// 此处根据任务描述条件 过滤结果
						if (null == taskDescription || taskDescription.trim().length() < 1 || null == desc || desc.trim().length() < 1 || desc.contains(taskDescription)) {
							// 获取该任务的所有关联触发器
							// TODO 任务大量时是否可以
							List<TriggerVo> triggers = getTaskTriggers(job);
							JobDetailVo detailVo = new JobDetailVo();
							jobList.add(detailVo);
							detailVo.copyFrom(jobDetail);
							detailVo.setTriggers(triggers);
						}
					}
				}
			} else {
				jobList = Collections.emptyList();
			}
		} catch (Exception e) {
			exceptionHandle(e);
		}
		return jobList;
	}

    @Override
    public List<Quartz> getJobPageList(Object page, Map criteria) {
        return null;
    }

    @Deprecated
	public List<Quartz> getJobList(Object page, Map paramMap) {
		throw new RuntimeException("Deprecated");
	}

	@Override
	public JobDetailVo getTask(String taskName) {
		Assert.hasText(taskName, "任务名为空");
		JobDetailVo detailVo = null;
		try {
			JobDetail detail = scheduler.getJobDetail(taskName, TaskConstants.TASK_GROUP);
			if (null != detail) {
				detailVo = new JobDetailVo();
				detailVo.copyFrom(detail);
				detailVo.setTriggers(getTaskTriggers(taskName));
			}
		} catch (SchedulerException e) {
			exceptionHandle(e);
		}
		return detailVo;
	}

	@Override
	public List<String> getTaskBeanNames() {
		return taskNames;
	}

	@Override
	public Map<String, Map<String, String>> getTaskBeanDeatils() {
		return taskWithDescs;
	}

	@Override
	@Deprecated
	public Quartz getTaskInfo(String taskName) {
		Quartz quartz = new Quartz();
		try {
			JobDetail detail = scheduler.getJobDetail(taskName, TaskConstants.TASK_GROUP);
			quartz.setDescription(detail.getDescription());
			quartz.setJobName(detail.getName());
			quartz.setTargetBeanName(detail.getJobDataMap().getString(TaskConstants.TASK_BEAN_NAME_KEY));
			Trigger[] triggers = scheduler.getTriggersOfJob(taskName, TaskConstants.TASK_GROUP);
			if (null != triggers && triggers.length > 0) {
				Trigger trigger = triggers[0];
				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					quartz.setCronExpression(cronTrigger.getCronExpression());
				}
			}

		} catch (SchedulerException e) {
			exceptionHandle(e);
		}
		return quartz;
	}

	@Override
	public List<TriggerVo> getTaskTriggers(String taskName) {
		Assert.hasText(taskName, "任务名为空");
		List<TriggerVo> triggers = null;
		try {
			Trigger[] triggerArr = scheduler.getTriggersOfJob(taskName, TaskConstants.TASK_GROUP);
			if (null != triggerArr && triggerArr.length > 0) {
				triggers = new ArrayList(triggerArr.length);
				for (Trigger trigger : triggerArr) {
					TriggerVo triggerVo = new TriggerVo();
					triggerVo.copyFrom(trigger);
					triggers.add(triggerVo);
					// 获取该触发器的状态
					triggerVo.setStatus(scheduler.getTriggerState(trigger.getName(), trigger.getGroup()));
				}
			}
		} catch (SchedulerException e) {
			exceptionHandle(e);
		}
		if (null == triggers) {
			triggers = Collections.emptyList();
		}
		return triggers;
	}

	@Override
	public void updateTask(String taskName, String taskDescription, String taskBeanName, String param) {
		Assert.hasText(taskName, "任务名为空");
		try {
			JobDetail detail = scheduler.getJobDetail(taskName, TaskConstants.TASK_GROUP);
			Assert.notNull(detail, "指定更新的任务不存在:".concat(taskName));
			checkTaskBeanName(taskBeanName);
			Assert.hasText(taskDescription, "任务描述为空");
			// 没有发生变化不替换
			Object paramOld = detail.getJobDataMap().get(TaskConstants.TASK_PARAM_KEY);
			boolean paramNoChange = false;
			if (param == null || param.trim().length() < 1) {
				paramNoChange = paramOld == null || paramOld.toString().trim().length() < 1;
			} else {
				paramNoChange = param.equals(paramOld);
			}
			if (paramNoChange && taskDescription.equals(detail.getDescription()) && taskBeanName.equals(detail.getJobDataMap().get(TaskConstants.TASK_BEAN_NAME_KEY))) {
				return;
			}
			detail.setDescription(taskName);
			detail.setDescription(taskDescription);
			detail.getJobDataMap().put(TaskConstants.TASK_PARAM_KEY, param);
			detail.getJobDataMap().put(TaskConstants.TASK_BEAN_NAME_KEY, taskBeanName);
			// 使用相同的taskName,加上允许替换,可以做到更新该任务
			scheduler.addJob(detail, true);
		} catch (SchedulerException e) {
			exceptionHandle(e);
		}
	}

	@Override
	public void pauseTask(String taskName) {
		Assert.hasText(taskName, "任务名为空");
		try {
			scheduler.pauseJob(taskName, TaskConstants.TASK_GROUP);
		} catch (Exception e) {
			exceptionHandle(e);
		}
	}

	@Override
	public void pauseTrigger(String triggerName) {
		Assert.hasText(triggerName, "触发器名为空");
		try {
			scheduler.pauseTrigger(triggerName, TaskConstants.TASK_GROUP);
		} catch (Exception e) {
			exceptionHandle(e);
		}
	}

	@Override
	public void removeTask(String taskName) {
		// 删除任务会删除全部关联的触发器
		Assert.hasText(taskName, "任务名为空");
		try {
			scheduler.pauseJob(taskName, TaskConstants.TASK_GROUP);
			scheduler.deleteJob(taskName, TaskConstants.TASK_GROUP);
		} catch (Exception e) {
			exceptionHandle(e);
		}
	}

	@Override
	public void removeTrigger(String triggerName) {
		Assert.hasText(triggerName, "触发器名为空");
		try {
			scheduler.unscheduleJob(triggerName, TaskConstants.TASK_GROUP);
		} catch (Exception e) {
			exceptionHandle(e);
		}
	}

	@Override
	public void resumeTask(String taskName) {
		Assert.hasText(taskName, "任务名为空");
		try {
			scheduler.resumeJob(taskName, TaskConstants.TASK_GROUP);
		} catch (Exception e) {
			exceptionHandle(e);
		}
	}

	@Override
	public void resumeTrigger(String triggerName) {
		Assert.hasText(triggerName, "触发器名为空");
		try {
			scheduler.resumeTrigger(triggerName, TaskConstants.TASK_GROUP);
		} catch (Exception e) {
			exceptionHandle(e);
		}
	}

	@Override
	public void runTask(String taskName) {
		Assert.hasText(taskName, "任务名为空");
		try {
			// 立即执行
			scheduler.triggerJobWithVolatileTrigger(taskName, TaskConstants.TASK_GROUP);
		} catch (SchedulerException e) {
			exceptionHandle(e);
		}
	}

	@Override
	public boolean isValidCronExpression(String cronExpression) {
		return ObjectUtil.isNotEmpty(cronExpression) && CronExpression.isValidExpression(cronExpression);
	}

	@Override
	public List<TaskHistory> getTaskHistories(Object page, Map criteria) {
	
//		List<TaskHistory> taskHistories = taskHistoryDao.getList(page, criteria);
//		if (null != taskHistories && null != taskHistories.getList()) {
//			return taskHistories.getList();
//		}
		return Collections.emptyList();
	}

	@Override
	public SchedulerVo getSchedulerStatus() {
		if (null == scheduler) {
			return null;
		}
		SchedulerVo schedulerVo = new SchedulerVo();
		try {
			schedulerVo.setSchedulerName(scheduler.getSchedulerName());
			schedulerVo.setNumJobsExecuted(String.valueOf(scheduler.getMetaData().getNumberOfJobsExecuted()));

			if (scheduler.getMetaData().isJobStoreSupportsPersistence()) {
				schedulerVo.setPersistenceType("1");
			} else {
				schedulerVo.setPersistenceType("0");
			}
			schedulerVo.setRunningSince(scheduler.getMetaData().getRunningSince());
			if (schedulerFactoryBean.isRunning()) {
				schedulerVo.setState("1");
			} else {
				schedulerVo.setState("0");
			}
			schedulerVo.setThreadPoolSize(String.valueOf(scheduler.getMetaData().getThreadPoolSize()));
			schedulerVo.setVersion(scheduler.getMetaData().getVersion());
			schedulerVo.setSummary(scheduler.getMetaData().getSummary());
			// scheduler.getCurrentlyExecutingJobs()
		} catch (SchedulerException e) {
			exceptionHandle(e);
		}
		return schedulerVo;

	}

	public void start() {
		if (!schedulerFactoryBean.isRunning()) {
			schedulerFactoryBean.start();
		}
	}

	public void stop() {
		schedulerFactoryBean.stop();
	}

	private Class<?>[] suppotClasses = { TaskList.class };
	private Map<Class<?>, JAXBContext> jaxbCache = new HashMap<Class<?>, JAXBContext>(2);

	private String marshal(Object bean) {
		StringWriter writer = null;
		try {
			Marshaller marshaller = jaxbCache.get(bean.getClass()).createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			writer = new StringWriter();
			marshaller.marshal(bean, writer);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
		return writer == null ? null : writer.toString();
	}

	@SuppressWarnings("unchecked")
	private <T> T parse(Class<?> clazz, String xml) {
		T message = null;
		StringReader reader = null;
		try {
			Unmarshaller um = jaxbCache.get(clazz).createUnmarshaller();
			reader = new StringReader(xml);
			message = (T) um.unmarshal(reader);
		} catch (Exception e) {
			throw new RuntimeException("xml解析失败", e);
		} finally {
			if (null != reader) {
				reader.close();
			}
		}
		return message;
	}

	@Override
	public String exportTask() {
		List<JobDetailVo> list = getJobList("", "");
		TaskList taskList = new TaskList();
		taskList.setTasks(list);
		return marshal(taskList);
	}

	@Override
	public void importTask(String xml) {
		Assert.hasText(xml, "任务列表xml不能为空");
		TaskList taskList = parse(TaskList.class, xml);
		List<JobDetailVo> list = taskList.getTasks();
		Assert.notEmpty(list, "任务列表解析失败,请确保文件没有问题,且编码为utf-8");
		if (ObjectUtil.isNotEmpty(list)) {
			for (JobDetailVo jobDetailVo : list) {
				List<TriggerVo> triggerVos = jobDetailVo.getTriggers();
				String taskId = addTask(jobDetailVo.getParam(), jobDetailVo.getDescription(), jobDetailVo.getBeanName(), null);
				if (ObjectUtil.isNotEmpty(triggerVos)) {
					for (TriggerVo triggerVo : triggerVos) {
						addTrigger(taskId, triggerVo.getStartTime(), triggerVo.getStopTime(), triggerVo.getCronExpression());
					}
				}
			}
		}
	}

	@Override
	public void removeAllTask() {
		try {
			String[] taskNames = scheduler.getJobNames(TaskConstants.TASK_GROUP);
			for (String string : taskNames) {
				scheduler.deleteJob(string, TaskConstants.TASK_GROUP);
			}
		} catch (SchedulerException e) {
			exceptionHandle(e);
		}
	}

	/**
	 * 生成服务id
	 * 
	 * @param taskDescription
	 * @param taskBeanName
	 * @return
	 */
	private String buildTaskName(String taskDescription, String taskBeanName) {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 生成触发器id
	 * 
	 * @param taskName
	 * @return
	 */
	private String buildTriggerName(String taskName) {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * cronExpression表达式检查
	 * 
	 * @param cronExpression
	 */
	private void checkCronExpression(String cronExpression) {
		Assert.hasText(cronExpression, "时间表达式为空");
		Assert.isTrue(isValidCronExpression(cronExpression), "cron表达式错误");
	}

	/**
	 * 任务实例检查
	 * 
	 * @param taskBeanName
	 */
	private void checkTaskBeanName(String taskBeanName) {
		Assert.hasText(taskBeanName, "任务bean名字为空");
		// 初始化并检查是否存在该任务
		Task task = tasks.get(taskBeanName);
		Assert.notNull(task, "applicationContext中没有找到任务bean,指定的任务beanName为:".concat(taskBeanName));
	}

	/**
	 * 异常处理
	 * 
	 * @param e
	 */
	private void exceptionHandle(Exception e) {
		logger.error(e.getMessage(), e);
		throw new TaskException(e.getMessage(), e);
	}

}
