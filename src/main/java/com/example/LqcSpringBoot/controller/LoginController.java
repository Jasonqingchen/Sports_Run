package com.example.LqcSpringBoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
@Controller
@RequestMapping("/kunyueye")
public class LoginController {


    /**
     * 退出
     * @param request
     * @return
     */
    @RequestMapping("/out")
    @ResponseBody
    public String out (HttpServletRequest request) {
        String flag ="success";
        request.getSession().removeAttribute("token");
        return flag ;
    }
}
