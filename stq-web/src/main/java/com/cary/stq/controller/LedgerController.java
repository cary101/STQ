package com.cary.stq.controller;

import com.cary.stq.STQConstant;
import com.cary.stq.service.IApplicationService;
import com.cary.stq.service.impl.CodeService;
import com.cary.stq.to.Application;
import com.cary.stq.to.User;
import com.cary.stq.utils.DateUtil;
import com.cary.stq.utils.StringUtils;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ledger")
public class LedgerController {
    @Resource(name = "applicationService")
    private IApplicationService applicationService;

    @Resource(name = "codeService")
    private CodeService codeService;

    @RequestMapping("/index")
    public ModelAndView initApp() throws IOException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/ledger/index");
        return mav;
    }

    @RequestMapping("/searchApp")
    @ResponseBody
    public Object searchApp(Application application, String applyId, int page, int rows) throws IOException {
        Map<String, Object> result = new HashMap<String, Object>();
        if(!StringUtils.isEmpty(applyId)){
            application.setApplyId(applyId);
        }
        List<Application> items = getApplications(application, page, rows);
        //TODO get total count
        List<Application> temp = getApplications(application);
        result.put("rows", items);
        result.put("total", temp.size());
        return result;
    }

    @RequestMapping("/initCreateApp")
    public ModelAndView initCreateApp() throws IOException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/application/create");
        mav.addObject("model","create");
        return mav;
    }

    @RequestMapping("/createApp")
    public void createApp(Application application, HttpServletRequest request) throws IOException {

        String nextSeq = codeService.getNextSeq();
        application.setApplyId("AP" + DateUtil.format(new Date(), "yyyyMM") + nextSeq);
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        application.setCreateBy(currentUser.getUserName());
        application.setCreateDt(new Date());
        applicationService.insert(application);
    }


    @RequestMapping("/initEditApp")
    public ModelAndView initEditApp(String applyId) throws IOException {
        ModelAndView mav = new ModelAndView();

        Application application = new Application();
        application.setApplyId(applyId);
        List<Application> result = getApplications(application);
        if (null != result && result.size() > 0) {
            mav.addObject("application", result.get(0));
        }
        mav.setViewName("/application/create");
        mav.addObject("model","edit");
        return mav;
    }

    @RequestMapping("/initAdSearch")
    public ModelAndView initAdSearch() throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/application/query");
        return mav;
    }

    @RequestMapping("/updateApp")
    @ResponseBody
    public void updateApp(Application application, HttpServletRequest request) {

        User currentUser = (User) request.getSession().getAttribute("currentUser");
        application.setModifiedBy(currentUser.getUserName());
        application.setModifiedDt(new Date());
        applicationService.updateByPKSelective(application);
    }

    @RequestMapping("/delApp")
    @ResponseBody
    public Object delApp(String applyId) {
        Application application = new Application();
        application.setApplyId(applyId);
        application.setStatus(STQConstant.STATUS_CANCEL);
        applicationService.updateByPKSelective(application);
        return 0;
    }

    @RequestMapping("/generateReport")
    public void generateReport(HttpServletRequest request, HttpServletResponse response) throws IOException, JRException {

        List<Application> result = applicationService.search(new Application());

        OutputStream outputStream = response.getOutputStream();
        response.setContentType("application/pdf");
        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Disposition", "attachment; filename=" + "URLEncoder.encode(\"PDF报表\"，\"UTF-8\"+\".pdf\"");
//        response.setHeader("Content-Disposition","attachment;filename=\"" + new String("", "iso-8859-1") + "\"");
        //jasper data source
        JRDataSource dataSource = new JRBeanCollectionDataSource(result);

        //JasperPrint
//        String rootPath = request.getContextPath();
        String rootPath2 = request.getSession().getServletContext().getRealPath("/");
        System.out.print(rootPath2);
        rootPath2 = rootPath2.replace("\\", "/");
        String reportFilePath = rootPath2 + "WEB-INF/classes/jasper/test1.jasper";
        JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(reportFilePath);
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, dataSource);

        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "F:\\test1.pdf");
        exporter.exportReport();

        outputStream.close();
    }

    public List<Application> getApplications(Application application, int page, int rows) {
        int start = (page - 1) * rows;
//        int limit = page * rows;
        List<Application> results = applicationService.search(application, start, rows);
        return results;
    }

    public List<Application> getApplications(Application application) {
        List<Application> results = applicationService.search(application);
        return results;
    }
}