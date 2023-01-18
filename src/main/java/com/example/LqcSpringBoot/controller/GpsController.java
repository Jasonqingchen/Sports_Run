package com.example.LqcSpringBoot.controller;

import com.sun.javafx.collections.MappingChange;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.Map;

/**
 *  this is the controller  user gps
 * @author liuqingchen
 * @date 2023/1/18 09:20
 */

@Controller
@RequestMapping("/gps")
public class GpsController {


    /**
     *  跳转 url
     * @return gps page
     */
    @RequestMapping(value="/tz",method= RequestMethod.GET)
    public String gpsTzPage (HttpServletRequest request, ModelMap map,@RequestParam("id") String id){
        //String id = request.getParameter("id");//get on the url parameter
        if (id!= null){
            map.addAttribute("id", id);
        } else {
            map.addAttribute("id", "9999999");
        }
        return "gpsPage";
    }

    /**
     *  打卡
     * @return type 1：打卡成功 2.选手已经打卡 3.该选手不存在
     */
    @RequestMapping("/save")
    @ResponseBody
     public String gpsSet (@RequestParam Map map) {
        String cpid = (String)map.get("cpid");
        String phone = (String)map.get("phone");

        return "1";
    }
}