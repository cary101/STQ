package com.cary.stq.scheduling;

import com.cary.scheduler.service.ITaskService;
import com.cary.scheduler.to.JobDetailVo;
import com.cary.scheduler.to.Quartz;
import com.cary.stq.utils.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Cary
 */
@SuppressWarnings("deprecation")
@RequestMapping("/task")
@Controller
public class TaskController {

    private Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private ITaskService quartzTask;

    @RequestMapping(value = "/index")
    public ModelAndView home(Model model) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/scheduler/task/list");
        return mav;
    }

    @RequestMapping(value = "/listResult")
    public String taskList(Model model, int indexPage, String taskName, String taskDescription) {
        List<JobDetailVo> details = quartzTask.getJobList(taskName, taskDescription);
        model.addAttribute("tasks", details);
        return "rhip.scheduling.task.listResult";
    }

    @RequestMapping(value = "/getTask")
    public String getTask(Model model, String taskName) {
        JobDetailVo jobDetail = quartzTask.getTask(taskName);
        if (null != jobDetail) {
            List<String> allTask = quartzTask.getTaskBeanNames();
            model.addAttribute("isRunable", allTask.contains(jobDetail.getBeanName()));
            model.addAttribute("task", jobDetail);
            model.addAttribute("taskTriggers", jobDetail.getTriggers());
        }
        return "rhip.scheduling.task.view";
    }

    @RequestMapping(value = "/editTask")
    public String editTask(Model model, String taskName) {
        model.addAttribute("taskNames", quartzTask.getTaskBeanNames());
        model.addAttribute("taskDeatils", quartzTask.getTaskBeanDeatils());
        model.addAttribute("task", quartzTask.getTask(taskName));
        return "rhip.scheduling.task.edit";
    }

    @RequestMapping(value = "/updateTask")
    @ResponseBody
    public Object updateTask(Model model, String taskName, String taskDescription, String taskBeanName, String param) {
        boolean result = true;
        try {
            quartzTask.updateTask(taskName, taskDescription, taskBeanName, param);
        } catch (Exception e) {
            result = false;
            handleException("添加任务失败", e);
        }
        return result;
    }

    @RequestMapping(value = "/getTaskTriggers")
    public String taskTriggers(Model model, String taskName) {
        model.addAttribute("taskTriggers", quartzTask.getTaskTriggers(taskName));
        return "rhip.scheduling.task.taskTriggersResult";
    }

    @RequestMapping(value = "/isValidCronExpression")
    @ResponseBody
    public String isValidCronExpression(String cronExpression) {
        if (quartzTask.isValidCronExpression(cronExpression)) {
            return "success";
        }
        return "请输入正确的CronExpression";
    }

    @RequestMapping(value = "/add")
    public String edit(Model model, String jobName) {
        model.addAttribute("taskNames", quartzTask.getTaskBeanNames());
        model.addAttribute("taskDeatils", quartzTask.getTaskBeanDeatils());
        return "rhip.scheduling.task.add";
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public boolean save(String param, String cronExpression, String taskDescription, String taskBeanName) {
        boolean result = true;
        try {
            quartzTask.addTask(param, taskDescription, taskBeanName, cronExpression);
        } catch (Exception e) {
            result = false;
            handleException("添加任务失败", e);
        }
        return result;
    }

    @RequestMapping(value = "/remove")
    @ResponseBody
    public Object remove(String jobName) {
        boolean result = true;
        try {
            quartzTask.removeTask(jobName);
        } catch (Exception e) {
            result = false;
            handleException("删除任务失败", e);
        }
        return result;
    }

    @RequestMapping(value = "/removeAll/{confirm}")
    @ResponseBody
    public Object removeAll(@PathVariable("confirm") String confirm) {
        if (!"yes".equals(confirm)) {
            return false;
        }
        boolean result = true;
        try {
            quartzTask.removeAllTask();
        } catch (Exception e) {
            result = false;
            handleException("删除任务失败", e);
        }
        return result;
    }

    @RequestMapping(value = "/run")
    @ResponseBody
    public Object run(String taskName) {
        boolean result = true;
        try {
            quartzTask.runTask(taskName);
        } catch (Exception e) {
            result = false;
            handleException("立即执行任务失败", e);
        }
        return result;
    }

    @RequestMapping(value = "/pause")
    @ResponseBody
    public Object pause(String taskName) {
        boolean result = true;
        try {
            quartzTask.pauseTask(taskName);
        } catch (Exception e) {
            result = false;
            handleException("立即执行任务失败", e);
        }
        return result;
    }

    @RequestMapping(value = "/resume")
    @ResponseBody
    public Object resume(String taskName) {
        boolean result = true;
        try {
            quartzTask.resumeTask(taskName);
        } catch (Exception e) {
            result = false;
            handleException("立即执行任务失败", e);
        }
        return result;
    }

    @RequestMapping(value = "/addTrigger")
    public String addTrigger(Model model, String taskName) {
        model.addAttribute("taskName", taskName);
        return "rhip.scheduling.trigger.edit";
    }

    @RequestMapping(value = "/saveTrigger")
    @ResponseBody
    public Object saveTrigger(Model model, String taskName, String startTime, String stopTime, String cronExpression) {
        boolean result = true;
        try {
            Date startDate = null;
            Date endDate = null;
            SimpleDateFormat dateFormat = null;
            if (ObjectUtil.isNotEmpty(startTime)) {
                dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                startDate = dateFormat.parse(startTime);
            }
            if (ObjectUtil.isNotEmpty(stopTime)) {
                if (null == dateFormat) {
                    dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                }
                endDate = dateFormat.parse(stopTime);
            }
            quartzTask.addTrigger(taskName, startDate, endDate, cronExpression);
        } catch (Exception e) {
            result = false;
            handleException("添加执行计划失败", e);
        }
        return result;
    }

    @RequestMapping(value = "/pauseTrigger")
    @ResponseBody
    public Object pauseTrigger(String triggerName) {
        boolean result = true;
        try {
            quartzTask.pauseTrigger(triggerName);
        } catch (Exception e) {
            result = false;
            handleException("暂停执行计划失败", e);
        }
        return result;
    }

    @RequestMapping(value = "/resumeTrigger")
    @ResponseBody
    public Object resumeTrigger(String triggerName) {
        boolean result = true;
        try {
            quartzTask.resumeTrigger(triggerName);
        } catch (Exception e) {
            result = false;
            handleException("恢复执行计划失败", e);
        }
        return result;
    }

    @RequestMapping(value = "/removeTrigger")
    @ResponseBody
    public Object removeTrigger(String triggerName) {
        boolean result = true;
        try {
            quartzTask.removeTrigger(triggerName);
        } catch (Exception e) {
            result = false;
            handleException("删除执行计划失败", e);
        }
        return result;
    }

    /**
     * 处理异常,打印详细信息
     *
     * @param message
     * @param exception
     * @throws Exception
     */
    protected void handleException(final String message, final Exception exception) {
        String msg = ObjectUtil.isNotEmpty(message) ? message : exception.getMessage();
        logger.error(msg, exception);
    }

    @RequestMapping(value = "/list2")
    @Deprecated
    public String list(Model model, int indexPage) {
//        Page page = new Page(10, indexPage);
//        Criteria criteria = new Criteria();
//        PageList<Quartz> pageList = quartzTask.getJobPageList(page, criteria);
//        model.addAttribute("jobs", pageList.getList());
//        model.addAttribute("page", pageList.getPage());
        return "rhip.job.list";
    }

    @RequestMapping(value = "/export")
    public void export(Model model, HttpServletResponse response) {
        String xml = quartzTask.exportTask();
        String name = "TaskListBak.xml";
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");
        try {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(xml);
        } catch (IOException e) {
            handleException("导出任务列表失败", e);
        }
    }

    @RequestMapping(value = "/import")
    @ResponseBody
    public Object importJobs(@RequestParam("qqfile") MultipartFile file) {
        Map<String, Object> result = new HashMap<>(2);
        try {
            String xml = new String(file.getBytes(), "UTF-8");
            quartzTask.importTask(xml);
            result.put("success", true);
        } catch (Exception e) {
            result.put("error", e.getMessage());
            handleException("删除执行计划失败", e);
        }
        return result;
    }

    @RequestMapping(value = "/importPage")
    public String importPage() {
        return "rhip.scheduling.task.import";

    }

}
