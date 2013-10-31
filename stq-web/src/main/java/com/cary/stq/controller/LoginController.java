package com.cary.stq.controller;

import com.cary.stq.service.impl.CodeService;
import com.cary.stq.service.IApplicationService;
import com.cary.stq.service.impl.UserService;
import com.cary.stq.to.User;
import com.cary.stq.utils.MD5Encoder;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@RequestMapping("/access")
public class LoginController {

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "applicationService")
    private IApplicationService applicationService;

    @Resource(name = "codeService")
    private CodeService codeService;

    @RequestMapping("/initPage")
    public String index() throws Exception {
        return "login/login";
    }

    @RequestMapping("/login")
    public String login(String loginId, String password, HttpServletRequest request, ModelMap model) {
        String result = "";
        if(StringUtils.isEmpty(loginId) || StringUtils.isEmpty(password)) {
            return "redirect:initPage";
        }
        password = MD5Encoder.getMD5Str(password);
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("loginId", loginId);
        paramMap.put("password", password);

        if (request.getMethod().equalsIgnoreCase("post")) {
            User currentUser = userService.login(paramMap);
            if (currentUser == null) {
                model.addAttribute("message", "登陆失败，用户名或密码不正确！");
//                mav.setViewName("/login/login");
                result = "redirect:initPage";
            } else {
                request.getSession().setAttribute("currentUser", currentUser);
                result = "/login/welcome";
            }
        }
        return result;
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) throws Exception {
        request.getSession().invalidate();
        return "access/index";
    }
}