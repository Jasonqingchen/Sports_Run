package com.example.LqcSpringBoot.controller;

import com.example.LqcSpringBoot.mapper.SportuserMapper;
import com.example.LqcSpringBoot.model.SportUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * user 注册相关业务类
 */
@Controller
@RequestMapping("/reg")
public class RegController {

    @Autowired
    public SportuserMapper stmapper;

    public Integer PHONECF = 888; //phone 重复 code
    /**
     * 用户最开始扫码进行注册洁面进行注册 （跳转）
     * @param request
     * @return liuqingchen
     */
    @RequestMapping("/tz")
    public String reg (HttpServletRequest request) {
        return "regPage" ;
    }

    /**
     * 注册信息请求后台 （注意  同一手机号只允许注册一次 前台会提示不可重复注册提示）
     * @return liuqingchen
     */
    @RequestMapping("/reg")
    @ResponseBody
    public Integer regUser (SportUser su){
        //验证手机号是否重复注册 如果重复注册着返回重复注册代码
        if(!stmapper.selectByPhone(su.getPhone()).isEmpty()){
            return PHONECF;
        } else {
            String number = String.valueOf((int) (Math.random()*9000+1000));
            if ("男".equals(su.getSex())) {
                su.setNumber("M"+number);
            } else {
                su.setNumber("F"+number);
            }
            su.setStatus("未开始");
            su.setId(UUID.randomUUID().toString().replace("-", ""));
            return stmapper.insert(su);
        }
    }
}
