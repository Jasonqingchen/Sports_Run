package com.example.LqcSpringBoot.controller;

import com.example.LqcSpringBoot.mapper.SportuserMapper;
import com.example.LqcSpringBoot.model.SportUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 对用户的维护列表
 * @author liuqingchen
 * @date 2023/1/24 18:18
 */
@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    private SportuserMapper sumapper;


    /**
     * 签到跳转页面
     */
    @RequestMapping("/tzsignin")
    public String issignin() {
        return "signin";

    }

    /**
     * by userid del user table
     * @param map
     * @return
     */
    @RequestMapping("/del")
    @ResponseBody
     public String delete(@RequestParam Map map) {
        int id = sumapper.deleteById(map.get("id").toString());
         return String.valueOf(id);
     }

    /**
     * 退赛
     * @param map
     * @return
     */
    @RequestMapping("/out")
    @ResponseBody
    public String out(@RequestParam Map map) {
        SportUser su= sumapper.selectById(map.get("id").toString());
        su.setStatus("退赛");
        int id = sumapper.updateById(su);
        return String.valueOf(id);
    }

    /**
     * 取消退赛
     * @param map
     * @return
     */
    @RequestMapping("/delout")
    @ResponseBody
    public String delout(@RequestParam Map map) {
        SportUser su= sumapper.selectById(map.get("id").toString());
        su.setStatus("比赛中");
        int id = sumapper.updateById(su);
        return String.valueOf(id);
    }

    /**
     * 签到 更新 字段状态为未签到
     */
    @RequestMapping("/sign")
    @ResponseBody
    public String sign(@RequestParam Map map) {
        List phone = sumapper.selectByPhone(map.get("phone").toString());
        if (phone.size()==0) {
            return "2";
        }
        int res = sumapper.updateByPhone(map.get("phone").toString());
        return String.valueOf(res);
    }

    /**
     * user list
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<SportUser> userList() {
        List<SportUser> sportUsers = sumapper.selectList(null);
        return sportUsers;
    }
}