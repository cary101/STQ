//package com.cary.stq.scheduling;
//
//import com.founder.fasf.beans.Criteria;
//import com.founder.fasf.beans.Page;
//import com.founder.rhip.BaseController;
//import com.founder.rhip.scheduling.entity.TaskHistory;
//import com.founder.rhip.scheduling.service.ITaskService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//
//@RequestMapping("/task/history")
//@Controller
//public class TaskHistoryController extends BaseController {
//
//	private static final String INDEX_PAGE_KEY = "indexPage";// resquest中的当前页的key
//	private static final String PAGE_KEY = "page";// page key
//
//	private Logger logger = LoggerFactory.getLogger(TaskHistoryController.class);
//
//	@Autowired
//	private ITaskService quartzTask;
//
//	@RequestMapping(value = "/list")
//	public String home(Model model) {
//		return "rhip.scheduling.task.history.list";
//	}
//
//	@RequestMapping(value = "/listResult")
//	public String taskList(HttpServletRequest request, Model model, TaskHistoryQueryForm form) {
//		Criteria criteria = form.toCriteria();
//		List<TaskHistory> histories = quartzTask.getTaskHistories(buildPage(request), criteria, null);
//		model.addAttribute("histories", histories);
//		return "rhip.scheduling.task.history.listResult";
//	}
//
//	public Page buildPage(HttpServletRequest request) {
//		String indexPage = request.getParameter(INDEX_PAGE_KEY);
//		int currentPage = 1;
//		try {
//			currentPage = Integer.valueOf(indexPage);
//		} catch (Exception e) {
//			logger.warn("没有当前页数");
//		}
//		Page page = getPage(request, currentPage);
//		request.setAttribute(PAGE_KEY, page);
//		return page;
//	}
//
//
//}
