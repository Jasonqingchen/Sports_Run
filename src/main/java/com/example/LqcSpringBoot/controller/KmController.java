package com.example.LqcSpringBoot.controller;

import cn.hutool.core.lang.UUID;
import com.example.LqcSpringBoot.mapper.SportKmMapper;
import com.example.LqcSpringBoot.model.SportKm;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


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
        sk.setLabel(name);
        sk.setValue(name);
        sk.setId(UUID.randomUUID().toString());
        return skmapper.insert(sk);
    }

    /**
     * 里程 list
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<Map<String,String>>  kmList() {
        List<SportKm> sportKm = skmapper.selectList(null);
        List<Map<String,String>> list = new ArrayList();
        sportKm.forEach(sk->{
            Map map = new HashMap();
            map.put("label",sk.getLabel());
            map.put("value",sk.getValue());
            list.add(map);
        });
        return list;
    }
    /**
     * 里程 list
     */
    @RequestMapping("/newlist")
    @ResponseBody
    public List<SportKm> newlist() {
        List<SportKm> sportKm = skmapper.selectList(null);
        return sportKm;
    }
}