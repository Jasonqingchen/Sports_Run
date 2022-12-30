package com.example.LqcSpringBoot.controller;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSONObject;
import com.example.LqcSpringBoot.mapper.SportCpMapper;
import com.example.LqcSpringBoot.model.SportCp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * 对cp点对维护类
 * @author liuqingchen
 * @date 2022/12/25 20:43
 */
@Controller
@RequestMapping("/cp")
public class CpController {
        @Autowired
        public SportCpMapper scpMapper;

    /**
     * 跳转到cp点维护界面
     */
    @RequestMapping("/tz")
    public String cpTz() {
        return "cpSet";
    }
    /**
     * 添加cp点内容
     */
    @RequestMapping("/cpset")
    @ResponseBody
    public String cpSet(@RequestParam Map map){
        List<SportCp> list = JSONObject.parseArray(map.get("list").toString(), SportCp.class);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        list.forEach(cp->{
                cp.setStarttime(map.get("starttime").toString());//开始时间
                cp.setEndtime(map.get("endtime").toString());//结束时间
                cp.setCpendtime(cp.getCpendtime().toString());
                cp.setId(UUID.randomUUID().toString().replace("-",""));
                cp.setUserid("0");
                scpMapper.insert(cp);
        });
        return "1";
    }

}