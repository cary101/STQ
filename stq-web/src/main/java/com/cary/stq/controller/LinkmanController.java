package com.cary.stq.controller;

import com.cary.stq.service.impl.LinkmanService;
import com.cary.stq.to.Linkman;
import com.cary.stq.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/linkman")
public class LinkmanController {
    @Resource(name = "linkmanService")
    private LinkmanService linkmanService;

    @RequestMapping("/index")
    public ModelAndView index() throws IOException {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/customer/index");
        return mav;
    }

    @RequestMapping("/search")
    @ResponseBody
    public Object search(String linkmanIds, String refComId) throws IOException {
        List<Linkman> result = new ArrayList<>();
        HashMap<String, String> paramMap = new HashMap<String, String>();
        if(!StringUtils.isEmpty(linkmanIds)){
            paramMap.put("linkmanIds", linkmanIds);
        }
        if(!StringUtils.isEmpty(refComId)){
            paramMap.put("refComId", refComId);
        }

        result = linkmanService.search(paramMap);
        return result;
    }

//    @RequestMapping("/initCreate")
//    public ModelAndView initCreate() throws IOException {
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("model", "create");
//        mav.setViewName("/customer/popup");
//        return mav;
//    }
//
//    @RequestMapping("/create")
//    public void create(Customer customer) throws IOException {
//        customerService.insert(customer);
//
//    }
//
//    @RequestMapping("/initUpdate")
//    public ModelAndView initUpdate(int companyId) throws IOException {
//        Customer customer = new Customer();
//        customer.setCompanyId(companyId);
//        List<Customer> result = customerService.search(customer);
//
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("customer", result.get(0));
//        mav.addObject("model", "update");
//        mav.setViewName("/customer/popup");
//        return mav;
//    }
//
//    @RequestMapping("/update")
//    public void update(Customer customer) throws IOException {
//        customerService.update(customer);
//    }
//
//    @RequestMapping("/delete")
//    @ResponseBody
//    public Object delete(int companyId) throws IOException {
//        int result = customerService.delete(companyId);
//        return result;
//    }
}