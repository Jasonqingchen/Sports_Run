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
     * @return type 1：打卡成功 2.选手已经打卡 3.该选手不存在
     */
    @RequestMapping("/save")
    @ResponseBody
    public String gpsSet(@RequestParam Map map) throws Exception {
        String cpid = (String) map.get("cpid");
        String phone = (String) map.get("phone");
        //先去用户表查询看是否已经注册
        List list1 = suMapper.selectByPhone(phone);
        if (list1.size() > 0) {
            //判断是否已经打卡
            List<SportGps> sgps = (List<SportGps>) sgMapper.selectGpByPhone(phone);
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
                sg.setBz(min);
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
    public String calculateTimeDifferenceBySimpleDateFormat(String T1, String T2) throws ParseException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        /*天数差*/
        Date fromDate1 = simpleFormat.parse(T1);
        Date toDate1 = simpleFormat.parse(T2);
        long from1 = fromDate1.getTime();
        long to1 = toDate1.getTime();
        int days = (int) ((to1 - from1) / (1000 * 60 * 60 * 24));
        String t1 = String.valueOf(days);
        //System.out.println("两个时间之间的天数差为：" + days);

        /*小时差*/
        Date fromDate2 = simpleFormat.parse(T1);
        Date toDate2 = simpleFormat.parse(T2);
        long from2 = fromDate2.getTime();
        long to2 = toDate2.getTime();
        int hours = (int) ((to2 - from2) / (1000 * 60 * 60));
        String t2 = String.valueOf(hours);
        //System.out.println("两个时间之间的小时差为：" + hours);

        /*分钟差*/
        Date fromDate3 = simpleFormat.parse(T1);
        Date toDate3 = simpleFormat.parse(T2);
        long from3 = fromDate3.getTime();
        long to3 = toDate3.getTime();
        int minutes = (int) ((to3 - from3) / (1000 * 60));
        String t3 = String.valueOf(minutes);
        //System.out.println("两个时间之间的分钟差为：" + minutes);
        return minutes / 24 / 60 + "天" + minutes / 60 % 24 + "小时" + minutes % 60 + "分钟";
    }

    /**
     * 总的分钟时间 做参数作为排名的查询条件
     *
     * @throws ParseException T1 开始时间， T2 当前时间
     */
    public String min(String T1, String T2) throws ParseException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        /*天数差*/
        Date fromDate1 = simpleFormat.parse(T1);
        Date toDate1 = simpleFormat.parse(T2);
        long from1 = fromDate1.getTime();
        long to1 = toDate1.getTime();
        int days = (int) ((to1 - from1) / (1000 * 60 * 60 * 24));
        String t1 = String.valueOf(days);
        //System.out.println("两个时间之间的天数差为：" + days);

        /*小时差*/
        Date fromDate2 = simpleFormat.parse(T1);
        Date toDate2 = simpleFormat.parse(T2);
        long from2 = fromDate2.getTime();
        long to2 = toDate2.getTime();
        int hours = (int) ((to2 - from2) / (1000 * 60 * 60));
        String t2 = String.valueOf(hours);
        //System.out.println("两个时间之间的小时差为：" + hours);

        /*分钟差*/
        Date fromDate3 = simpleFormat.parse(T1);
        Date toDate3 = simpleFormat.parse(T2);
        long from3 = fromDate3.getTime();
        long to3 = toDate3.getTime();
        int minutes = (int) ((to3 - from3) / (1000 * 60));
        String t3 = String.valueOf(minutes);
        //System.out.println("两个时间之间的分钟差为：" + minutes);
        return t3;
    }

}