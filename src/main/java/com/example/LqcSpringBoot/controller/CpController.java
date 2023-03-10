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
 *
 * @author liuqingchen
 * @date 2022/12/25 20:43
 */
@Controller
@RequestMapping("/cp")
public class CpController {
    @Autowired
    public SportCpMapper scpMapper;

    /**
     * 主界面（导航页）
     */
    @RequestMapping("/tz")
    public String cpTz() {
        return "urlPage";
    }

    /**
     * 跳转到cp点维护界面
     */
    @RequestMapping("/tzset")
    public String cpTzSet() {
        return "cpSet";
    }

    /**
     * 清空Sport_cp table
     */
    @RequestMapping("/delcpall")
    @ResponseBody
    public String delCptable() {
        int delete = scpMapper.delete(null);
        return String.valueOf(delete);
    }
    /**
     * 添加cp点内容
     */
    @RequestMapping("/cpset")
    @ResponseBody
    public String cpSet(@RequestParam Map map) {
        List<SportCp> list = JSONObject.parseArray(map.get("list").toString(), SportCp.class);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        list.forEach(cp -> {
            cp.setStarttime(map.get("starttime").toString());//开始时间
            cp.setEndtime(map.get("endtime").toString());//结束时间
            cp.setCpendtime(cp.getCpendtime().toString());
            cp.setId(UUID.randomUUID().toString().replace("-", ""));
            cp.setUserid("0");
            if ("".equals(cp.getIndexid())) {
                cp.setIndexid("0");
            }
            scpMapper.insert(cp);
        });
        return "1";
    }

    /**
     * 生成二维码列表 列表查询
     *
     * @return
     */
    @RequestMapping("/cpselect")
    @ResponseBody
    public List<SportCp> cpSelect() {
        return scpMapper.selectList(null);
    }

    /**
     * 是否开启关门不可打卡功能
     */
    @RequestMapping("/issw")
    @ResponseBody
    public String isTrueFalse(SportCp sc) {
        int i = scpMapper.updateById(sc);
        return String.valueOf(i);
    }

    /**
     * 设置新的开始时间
     */
    @RequestMapping("/uptime")
    @ResponseBody
    public String newTime(String time) {
        int i = scpMapper.updateTime(time);
        return String.valueOf(i);
    }
}