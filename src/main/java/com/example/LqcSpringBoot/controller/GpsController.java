package com.example.LqcSpringBoot.controller;

import cn.hutool.core.lang.UUID;
import com.example.LqcSpringBoot.mapper.SportCpMapper;
import com.example.LqcSpringBoot.mapper.SportGpsMapper;
import com.example.LqcSpringBoot.mapper.SportuserMapper;
import com.example.LqcSpringBoot.model.SportCp;
import com.example.LqcSpringBoot.model.SportGps;
import com.example.LqcSpringBoot.model.SportUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * this is the controller  user gps
 *
 * @author liuqingchen
 * @date 2023/1/18 09:20
 */

@Controller
@RequestMapping("/gps")
public class GpsController {
    @Autowired
    public SportGpsMapper sgMapper;
    @Autowired
    public SportCpMapper scMapper;
    @Autowired
    public SportuserMapper suMapper;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    /**
     * 跳转 url
     *
     * @return gps page
     */
    @RequestMapping(value = "/tz", method = RequestMethod.GET)
    public String gpsTzPage(HttpServletRequest request, ModelMap map, @RequestParam("id") String id) {
        if (id != null) {
            map.addAttribute("id", id);
        } else {
            map.addAttribute("id", "9999999");
        }
        return "gpsPage";
    }

    /**
     * 打卡
     *
     * @return type 1：打卡成功 2.选手已经打卡 3.该选手不存在 4.选手被关门
     */
    @RequestMapping("/save")
    @ResponseBody
    public String gpsSet(@RequestParam Map map) throws Exception {
        String cpid = (String) map.get("cpid");
        String phone = (String) map.get("phone");
        //判断该补给点是否有关门时间要求 无则继续 有则判断是否在规定时间内完成 没有在设定时间内完成则返回状态 4
        SportCp sscp = scMapper.selectById(cpid);
        if ("1".equals(sscp.getSw())) {
            Date now = new Date(); // 创建一个Date对象，获取当前时间
            String strDateFormat = "yyyy-MM-dd HH:mm:ss";
            LocalDateTime localDateTime = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            String gpsdata = localDateTime.format(DateTimeFormatter.ofPattern(strDateFormat));
            String s = this.min(gpsdata, sscp.getCpendtime());
            if (Integer.valueOf(s)<0){
                return "4";
            }
        }
        //先去用户表查询看是否已经注册
        List<SportUser> list1 = suMapper.selectByPhone(phone);
        if ("退赛".equals(list1.get(0).getStatus())) {
            return "3";
        }
        if (list1.size() > 0) {
            //判断是否已经打卡
            List<SportGps> sgps = (List<SportGps>) sgMapper.selectGpByPhoneAndCpid(phone,cpid);
            if (sgps.size() > 0) {
                //有选手打卡数据返回姓名
                String uname = sgps.get(0).getUsername();
                return uname;
            } else {
                //无数据表示选手还未打卡 则进行开始逻辑
                //select cp
                SportCp sportCp = scMapper.selectById(cpid);
                //select user
                List<SportUser> list = suMapper.selectByPhone(phone);

                SportGps sg = new SportGps();
                sg.setCpid(cpid);
                sg.setCpname(sportCp.getCpname());
                sg.setUsername(list.get(0).getName());
                sg.setGpsid(UUID.randomUUID().toString().replace("-", ""));
                sg.setSex(list.get(0).getSex());
                sg.setGroupzb(list.get(0).getGroupzb());
                sg.setPhone(phone);
                Date now = new Date(); // 创建一个Date对象，获取当前时间
                String strDateFormat = "yyyy-MM-dd HH:mm:ss";
                LocalDateTime localDateTime = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                String gpsdata = localDateTime.format(DateTimeFormatter.ofPattern(strDateFormat));
                sg.setGpstime(gpsdata);//打卡时间
                String s = this.calculateTimeDifferenceBySimpleDateFormat(sportCp.getStarttime(), gpsdata);
                sg.setSumtime(s);//当前用时
                String min = this.min(sportCp.getStarttime(), gpsdata);
                list1.forEach(ite->{
                    //判断当前到卡是否为重点打卡 如果是 则赋值 完赛 否则为 比赛中 退赛需要收到修改
                    Integer integer = scMapper.selectCount(null)-1;
                    SportCp sportCp1 = scMapper.selectById(cpid);
                    if (String.valueOf(integer).equals(sportCp1.getIndexid())) {
                        ite.setStatus("完赛");
                    } else {
                        ite.setStatus("比赛中");
                    }
                    ite.setMin(min);
                    ite.setBz(sportCp.getIndexid());
                    suMapper.updateById(ite);
                });
               // sg.setBz(min);
                sgMapper.insert(sg);
                return "1";
            }
        } else {
            return "2";
        }

    }

    /**
     * 用SimpleDateFormat计算时间差
     *
     * @throws ParseException T1 开始时间， T2 当前时间
     */
    public static String calculateTimeDifferenceBySimpleDateFormat(String T1, String T2) throws ParseException {
        Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(T1);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(T2);

        // 相差的毫秒值
        Long milliseconds = endDate.getTime() - startDate.getTime();

        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数

        long day = milliseconds / nd; // 计算相差多少天
        long hour = milliseconds % nd / nh; // 计算相差剩余多少小时
        long min = milliseconds % nd % nh / nm; // 计算相差剩余多少分钟
        long sec = milliseconds % nd % nh % nm / ns; // 计算相差剩余多少秒
        getTimeString(milliseconds);
        //return hour + ":" + min + ":" + sec + ":";
        return getTimeString(milliseconds);
    }

    /**
     * 总的分钟时间 做参数作为排名的查询条件
     *
     * @throws ParseException T1 开始时间， T2 当前时间
     */
    public String min(String T1, String T2) throws ParseException {
        Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(T1);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(T2);

        // 相差的毫秒值
        Long milliseconds = endDate.getTime() - startDate.getTime();
        return String.valueOf(milliseconds/1000);
    }

    /**
     * 把时间戳转换为：时分秒
     *
     * @param millisecond ：毫秒，传入单位为毫秒
     */
    public static String getTimeString(final long millisecond) {
        if (millisecond < 1000) {
            return "0" + "";
        }
        long second = millisecond / 1000;
        long seconds = second % 60;
        long minutes = second / 60;
        long hours = 0;
        if (minutes >= 60) {
            hours = minutes / 60;
            minutes = minutes % 60;
        }
        String timeString = "";
        String secondString = "";
        String minuteString = "";
        String hourString = "";
        if (seconds < 10) {
            secondString = "0" + seconds;
        } else {
            secondString = seconds + "";
        }
        if (minutes < 10 && hours < 1) {
            minuteString = minutes + ":";
        } else if (minutes < 10){
            minuteString =  "0" + minutes + ":";
        } else {
            minuteString = minutes + ":";
        }
        if (hours < 10) {
            hourString = hours + ":";
        } else {
            hourString = hours + "" + ":";
        }
        if (hours != 0) {
            timeString = hourString + minuteString + secondString;
        } else {
            timeString = minuteString + secondString;
        }
        return timeString;
    }

}