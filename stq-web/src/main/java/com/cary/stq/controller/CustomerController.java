package com.cary.stq.controller;

import com.cary.stq.service.impl.CustomerService;
import com.cary.stq.to.Customer;
import com.cary.stq.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Resource(name = "customerService")
    private CustomerService customerService;

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/customer/index");
        return mav;
    }

    @RequestMapping("/search")
    @ResponseBody
    public Object search(Customer customer, String companyName, String q){
        if (!StringUtils.isEmpty(companyName)) {
            customer.setCompanyName(companyName);
        }
        if (!StringUtils.isEmpty(q)) {
            customer.setHelp(q);
        }
        List<Customer> result = new ArrayList<>();
        try{
            result = customerService.search(customer);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("/initCreate")
    public ModelAndView initCreate() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("model", "create");
        mav.setViewName("/customer/popup");
        return mav;
    }

    @RequestMapping("/create")
    public void create(Customer customer) {
        try{
            customerService.insert(customer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping("/initUpdate")
    public ModelAndView initUpdate(int companyId){
        Customer customer = new Customer();
        customer.setCompanyId(companyId);
        List<Customer> result = customerService.search(customer);

        ModelAndView mav = new ModelAndView();
        mav.addObject("customer", result.get(0));
        mav.addObject("model", "update");
        mav.setViewName("/customer/popup");
        return mav;
    }

    @RequestMapping("/update")
    public void update(Customer customer){
        try{
            customerService.update(customer);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(int companyId){
        int result = 0;
        try{
            result = customerService.delete(companyId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}