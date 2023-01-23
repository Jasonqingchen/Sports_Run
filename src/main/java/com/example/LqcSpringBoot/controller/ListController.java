package com.example.LqcSpringBoot.controller;

import com.example.LqcSpringBoot.mapper.SportCpMapper;
import com.example.LqcSpringBoot.mapper.SportGpsMapper;
import com.example.LqcSpringBoot.mapper.SportuserMapper;
import com.example.LqcSpringBoot.model.SportGps;
import com.example.LqcSpringBoot.model.SportUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author liuqingchen
 * @date 2023/1/22 12:43
 */
@RequestMapping("/list")
@Controller
public class ListController {
    @Autowired
    public SportCpMapper scMapper;
    @Autowired
    public SportuserMapper suMapper;
    @Autowired
    public SportGpsMapper sgMapper;
    /**
     * 跳转用户查询页面
     * @return
     */
    @RequestMapping("/tz")
    public String tz() {
        return "listPage";
    }

    /**
     * 根据条件查询选手成绩
     * @param map
     * @return
     */
    @RequestMapping("/seach")
    @ResponseBody
    public List seach(@RequestParam Map map) throws InterruptedException {
        if (map.get("phone")!="") {
            List list = suMapper.selectByPhone(map.get("phone").toString());
            return list;
        } else {
            List<SportUser> sportUsers = suMapper.selectList(null);
            return sportUsers;
        }
    }

    /**
     * 查询每个用户的打卡情况
     * @return
     */
    @RequestMapping("/seachUser")
    @ResponseBody
    public List<SportGps>  seachUser (@RequestParam Map map) {
        List<SportGps> list = sgMapper.selectByPhone(map.get("phone").toString());
        return list;
    }
}