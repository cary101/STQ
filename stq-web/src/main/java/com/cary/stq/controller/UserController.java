package com.cary.stq.controller;

import com.cary.stq.service.impl.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource(name = "userService")
    private UserService userService;

    @RequestMapping("/search")
    public ModelAndView search(HttpServletRequest request, ModelMap model) {
        ModelAndView mav = new ModelAndView();
        return mav;
    }

    @RequestMapping("/create")
    public ModelAndView create(HttpServletRequest request, ModelMap model) {
        ModelAndView mav = new ModelAndView();
        return mav;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(HttpServletRequest request, ModelMap model) {
        ModelAndView mav = new ModelAndView();
        return mav;
    }

    @RequestMapping("/delete")
    public ModelAndView delete(HttpServletRequest request, ModelMap model) {
        ModelAndView mav = new ModelAndView();
        return mav;
    }

}