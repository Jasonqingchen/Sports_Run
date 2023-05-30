package com.example.LqcSpringBoot.controller;

import com.example.LqcSpringBoot.mapper.SportTbMapper;
import com.example.LqcSpringBoot.model.SportTb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
        List<SportTb>  SportTb;
        if (sportTb.getName()==null || sportTb.getName()==""){
            SportTb = stm.selectList(null);
        } else {
            SportTb = stm.selecNameLike(sportTb.getName());
        }
        return SportTb;
    }


}
