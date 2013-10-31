package com.cary.stq.controller;

import com.cary.stq.service.IWorkdayService;
import com.cary.stq.to.WorkDayTo;
import com.cary.stq.utils.DateUtil;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Controller
@RequestMapping("/workday")
public class WorkDayController {

    @Resource(name = "workdayService")
    private IWorkdayService workDayService;

    /**
     * 查询某月工作日设定
     */
    @RequestMapping("/index")
    public ModelAndView getWorkDays(String dateStr) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/workday/workday");
        if (StringUtils.isEmpty(dateStr)) {
            dateStr = DateUtil.format(new Date(), "yyyy-MM-dd");
        }
        List<WorkDayTo> workDays = workDayService.getWorkDays(dateStr);
        String isSetted = "1";
        String workDaysJson = "";
        if (null == workDays || workDays.size() == 0) {
            isSetted = "0";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (WorkDayTo workDay : workDays) {
                sb.append("{\"date\":" + workDay.getNaturalDay().substring(8, 10) + "," + "\"isworkday\":" + workDay.getIsWorkDay() + "},");
            }
            workDaysJson = sb.toString().substring(0, sb.length() - 1);
            workDaysJson = workDaysJson + "]";
        }
        mav.addObject("workDaysJson", workDaysJson);
        mav.addObject("isSetted", isSetted);
        mav.addObject("month", dateStr.substring(0, 7));
        return mav;
    }

    @RequestMapping("/changeMonth")
    public void changeMonth(String dateStr, HttpServletResponse response) throws Exception {
        List<WorkDayTo> workDays = workDayService.getWorkDays(dateStr);
        String isSetted = "1";
        String workDaysJson = "";
        if (null == workDays || workDays.size() == 0) {
            isSetted = "0";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (WorkDayTo workDay : workDays) {
                String dayTemp = workDay.getNaturalDay().substring(8, 10);
                dayTemp = dayTemp.indexOf("0") == 0 ? dayTemp.replace("0", "") : dayTemp;
                sb.append("{\'date\':\'" + dayTemp + "\'," + "\'isworkday\':\'" + workDay.getIsWorkDay() + "\'},");
            }
            workDaysJson = sb.toString().substring(0, sb.length() - 1);
            workDaysJson = workDaysJson + "]";
        }
        response.getWriter().write("{\"isSetted\":\"" + isSetted + "\"," + "\"workDaysJson\":\"" + workDaysJson + "\"," + "\"loadDate\":\"" + dateStr + "\"}");
    }

    /**
     * 设定某月工作日
     */
    @RequestMapping("/saveMonth")
    public void saveMonth(String month, String isSetted, String workDaysJson, HttpServletResponse response) throws IOException {
        List<WorkDayTo> workDays = new ArrayList<WorkDayTo>();
        JSONArray jsonObjects = JSONArray.fromObject(workDaysJson);
        for (int i = 0; i < jsonObjects.size(); i++) {
            Map<String, String> monthMap = (Map) jsonObjects.get(i);
            WorkDayTo workDay = new WorkDayTo();
            workDay.setNaturalDay(month + "-" + monthMap.get("date"));
            workDay.setIsWorkDay(monthMap.get("isworkday"));
            workDays.add(workDay);
        }
        try {
            workDayService.insertWorkDays(isSetted, workDays);
            response.getWriter().write("{\"code\":1}");
        } catch (Exception e) {
            response.getWriter().write("{\"code\":0 , \"message\":\""+e.getMessage()+"\"}");
        }
    }

    /**
     * 修改某天的工作日设定
     */
    @RequestMapping("/updateWorkDay")
    public void updateWorkDay(String workDayJson) {

    }
}