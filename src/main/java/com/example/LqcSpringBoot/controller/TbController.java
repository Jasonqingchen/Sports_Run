package com.example.LqcSpringBoot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.LqcSpringBoot.mapper.SportTbMapper;
import com.example.LqcSpringBoot.model.SportTb;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 团报
 */
@Controller
@RequestMapping("/tb")
@CrossOrigin
public class TbController {

    @Autowired
    private SportTbMapper stm;

    @RequestMapping("/save")
    @ResponseBody
    public String gpsSet(SportTb sportTb) throws Exception {
        List<SportTb> st = stm.selectBySfzhm(sportTb.getSfzhm());
        if (st.size() > 0) {
            return "2";
        }
        sportTb.setId(UUID.randomUUID().toString().replace("-", ""));
        stm.insert(sportTb);
        return "1";
    }

    /**
     * 选手查询
     *
     * @param sportTb
     * @return
     */
    @RequestMapping("/search")
    @ResponseBody
    public List<SportTb> tz(SportTb sportTb) {
        List<SportTb> sportTb1 = stm.selectBySfzhm(sportTb.getSfzhm());
        if (sportTb1.size() > 0) {
            return sportTb1;
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * 赛事查询 接口下拉
     *
     * @param
     * @return
     */
    @RequestMapping("/selectbysport")
    @ResponseBody
    public String selectbysport() {
        return "";
    }

    /**
     * 选手列表查询
     *
     * @param
     * @return
     */
    @RequestMapping("/selectUser")
    @ResponseBody
    public List<SportTb> selectUser(SportTb sportTb) {
        List<SportTb> SportTb;
        if (sportTb.getName() == null || sportTb.getName() == "") {
            SportTb = stm.selectList(null);
        } else {
            SportTb = stm.selecNameLike(sportTb.getName());
        }
        return SportTb;
    }

    /**
     * openai接口
     *
     * @param
     * @return
     */
    @RequestMapping("/openai")
    @ResponseBody
    public String selectUser(String texts) {
        if (texts==null || texts ==""){
            return "未输入内容";
        }
        Unirest.setTimeouts(0, 0);
        try {
            HttpResponse<String> response = Unirest.post("https://api.openai.com/v1/completions")
                    .header("Authorization", "Bearer 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")
                    .header("Content-Type", "application/json")
                    .body("{\n    \"model\": \"text-davinci-003\",\n    \"prompt\":\"" +
                            texts+
                            "\",\n    \"max_tokens\": 1025,\n    \"temperature\": 0\n}")
                    .asString();
            HashMap hashMap = JSON.parseObject(response.getBody(), HashMap.class);
            List<Object> choices = (List<Object>) hashMap.get("choices");
            String text = (String) ((JSONObject) choices.get(0)).get("text");
            return text;

        } catch (UnirestException e) {
            throw new RuntimeException(e);
        }

    }


}
