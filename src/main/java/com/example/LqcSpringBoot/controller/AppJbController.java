package com.example.LqcSpringBoot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 安全预警
 */
@Controller
@RequestMapping("/jb")
public class AppJbController {


    /**
     * 跳转预警页面
     * @return
     */
    @RequestMapping("/jbtz")
    public String jvTz (ModelMap map){
        map.addAttribute("text","入侵预警,请注意 2 号房间");
        return "alarm";
    }
}
