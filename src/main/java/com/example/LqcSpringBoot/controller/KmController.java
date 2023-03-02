package com.example.LqcSpringBoot.controller;

import cn.hutool.core.lang.UUID;
import com.example.LqcSpringBoot.mapper.SportKmMapper;
import com.example.LqcSpringBoot.mapper.SportuserMapper;
import com.example.LqcSpringBoot.model.SportKm;
import com.example.LqcSpringBoot.model.SportUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 对里程的维护列表
 * @author liuqingchen
 * @date 2023/1/24 18:18
 */
@RequestMapping("/km")
@Controller
public class KmController {
    @Autowired
    private SportKmMapper skmapper;

    /**
     * by userid del km table
     * @param map
     * @return
     */
    @RequestMapping("/del")
    @ResponseBody
     public String delete(@RequestParam Map map) {
        int id = skmapper.deleteById(map.get("id").toString());
         return String.valueOf(id);
     }

    /**
     * 新增里程
     * @param name
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
     public Integer addKm(String name ) {
        SportKm sk  = new SportKm();
        sk.setName(name);
        sk.setId(UUID.randomUUID().toString());
        return skmapper.insert(sk);
    }

    /**
     * user list
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<SportKm> kmList() {
        List<SportKm> sportKm = skmapper.selectList(null);
        return sportKm;
    }
}