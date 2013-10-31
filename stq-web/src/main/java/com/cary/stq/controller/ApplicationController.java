package com.cary.stq.controller;

import com.cary.stq.STQConstant;
import com.cary.stq.service.IApplicationService;
import com.cary.stq.service.impl.CodeService;
import com.cary.stq.to.Application;
import com.cary.stq.to.User;
import com.cary.stq.utils.DateUtil;
import com.cary.stq.utils.StringUtils;
import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/application")
public class ApplicationController extends BaseController{
    @Resource(name = "applicationService")
    private IApplicationService applicationService;

    @Resource(name = "codeService")
    private CodeService codeService;

    @RequestMapping("/index")
    public ModelAndView index() throws IOException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/application/index");
        return mav;
    }

    @RequestMapping("/searchApp")
    @ResponseBody
    public Object searchApp(Application application, String id, String applyId,  int page, int rows) throws IOException {
        Map<String, Object> result = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(applyId)) {
            application.setApplyId(applyId);
        }
        if (!StringUtils.isEmpty(id)) {
            application.setId(Integer.valueOf(id));
        }
        Long total = applicationService.getTotalCount(application);
        List<Application> items = new ArrayList<Application>();
        if (total > 0) {
            items = applicationService.search(application, page, rows);
        }
        result.put("rows", items);
        result.put("total", total);
        return result;
    }

    @RequestMapping("/initCreateApp")
    public ModelAndView initCreateApp() throws IOException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/application/popup");
        mav.addObject("model", "create");
        return mav;
    }

    @RequestMapping("/initEditApp")
    public ModelAndView initEditApp(String id, String applyId) throws IOException {
        ModelAndView mav = new ModelAndView();

        Application application = new Application();
        HashMap<String, String> paramMap = new HashMap<>();
        paramMap.put("id",id);
        paramMap.put("applyId",applyId);
        application = applicationService.getAppDetails(paramMap);
        mav.addObject("application", application);
        mav.setViewName("/application/popup");
        mav.addObject("model", "edit");
        return mav;
    }

    @RequestMapping("/initAdSearch")
    public ModelAndView initAdSearch() throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/application/query");
        return mav;
    }

    @RequestMapping("/createApp")
    public void createApp(Application application, HttpServletRequest request, HttpServletResponse response, String saveType) throws IOException {
        application.setSampleName(application.getSample().getName());
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        application.setCreateBy(currentUser.getUserName());
        application.setCreateDt(new Date());
        if("submit".equals(saveType)){
            String nextSeq = codeService.getNextSeq();
//            application.setApplyId("AP" + DateUtil.format(new Date(), "yyyyMM") + nextSeq);
//            application.setStatus(STQConstant.STATUS_APPPLY_SUBMIT);
        }else {
            application.setStatus(STQConstant.STATUS_APPPLY_DRAFT);
        }
        try{
            application.setApplyId(null);
            applicationService.insert(application);
            response.getWriter().write("1");
        }catch(Exception e){
            e.printStackTrace();
            response.getWriter().write("error \n" + e.getMessage()+"}");
        }

    }

    @RequestMapping("/updateApp")
    public void updateApp(Application application, HttpServletRequest request, HttpServletResponse response, String saveType) throws IOException{
//        application.setSampleReceivedDt(DateUtil.parse(application.getSampleReceivedDtInput()));
//        application.setReportDt(DateUtil.parse(application.getReportDtInput()));
        application.setSampleId(application.getSample().getSampleId());
        application.setSampleName(application.getSample().getName());
        User currentUser = (User) request.getSession().getAttribute("currentUser");
        application.setModifiedBy(currentUser.getUserName());
        application.setModifiedDt(new Date());
        if("submit".equals(saveType)){
            application.setStatus(STQConstant.STATUS_APPPLY_SUBMIT);
//            String nextSeq = codeService.getNextSeq();
//            application.setApplyId("AP" + DateUtil.format(new Date(), "yyyyMM") + nextSeq);
        }else {
            application.setStatus(STQConstant.STATUS_APPPLY_DRAFT);
        }
        try{
            applicationService.updateAll(application);
            response.getWriter().write("1");
        }catch (Exception e){
            e.printStackTrace();
            response.getWriter().write("error \n" + e.getMessage()+"}");
        }
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

//    @RequestMapping("/generateReport")
    public void generateReport(String applyId, HttpServletRequest request, HttpServletResponse response) throws IOException, JRException {
//        Application application = applicationService.getAppSingle(applyId);
//        List ds = new ArrayList<>();
//        ds.add(application);
//
////        OutputStream outputStream = response.getOutputStream();
//        response.setContentType("application/pdf");
//        response.setCharacterEncoding("UTF-8");
//        //jasper data source
//        JRDataSource dataSource = new JRBeanCollectionDataSource(ds);
//
//        //JasperPrint
//        try {
//            String rootPath2 = request.getSession().getServletContext().getRealPath("/");
//            rootPath2 = rootPath2.replace("\\", "/");
//            String reportFilePath = rootPath2 + "WEB-INF/classes/jasper/application.jasper";
//            JasperReport report = (JasperReport) JRLoader.loadObjectFromFile(reportFilePath);
//            JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, dataSource);
//
//            JRPdfExporter exporter = new JRPdfExporter();
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
////        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
////            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "D:\\Jaspersoft\\report\\"+applyId+".pdf");
//            exporter.exportReport();
////            outputStream.close();
//            response.getWriter().write("{\"success\":1}");
//        } catch (Exception e) {
//            response.getWriter().write("{\"success\":0 , \"message\": " + e.getMessage() + "}");
//        }
    }

    @RequestMapping("/generateReport")
    public void exportPdf(String applyId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String filePath = request.getSession().getServletContext().getRealPath("/WEB-INF/classes/jasper/application.jasper");
//        Application application = applicationService.getAppSingle(applyId);
//        List ds = new ArrayList<>();
//        ds.add(application);
//        //jasper data source
//        JRDataSource dataSource = new JRBeanCollectionDataSource(ds);
//
//        JasperPrint jasperPrint = null;
//        try {
//            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(filePath);
//            jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
//        } catch (JRException e) {
//            e.printStackTrace();
//        }
//        if (null != jasperPrint) {
//            FileBufferedOutputStream fbos = new FileBufferedOutputStream();
//            JRPdfExporter exporter = new JRPdfExporter();
//            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fbos);
//            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//            try {
//                exporter.exportReport();
//                fbos.close();
//                if (fbos.size() > 0) {
//                    response.setContentType("application/pdf");
//                    response.setContentLength(fbos.size());
//                    ServletOutputStream ouputStream = response.getOutputStream();
//                    try {
//                        fbos.writeData(ouputStream);
//                        fbos.dispose();
//                        ouputStream.flush();
//                    } finally {
//                        if (null != ouputStream) {
//                            ouputStream.close();
//                        }
//                    }
//                }
//            } catch (JRException e1) {
//                e1.printStackTrace();
//            } finally {
//                if (null != fbos) {
//                    fbos.close();
//                    fbos.dispose();
//                }
//            }
//        }
    }
}