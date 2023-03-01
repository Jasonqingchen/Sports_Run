package com.example.LqcSpringBoot.controller;

import com.example.LqcSpringBoot.mapper.SportCpMapper;
import com.example.LqcSpringBoot.mapper.SportGpsMapper;
import com.example.LqcSpringBoot.mapper.SportuserMapper;
import com.example.LqcSpringBoot.model.SportCp;
import com.example.LqcSpringBoot.model.SportGps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.Map;

/**
 * @author liuqingchen
 * @date 2023/1/22 12:43
 */
@RequestMapping("/list")
@Controller
public class ListController {

    public Double  pskm =0.00;//总里程
    public String sumtime;//总时间
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
        boolean pho = this.isPhone(map.get("tj").toString());
        String name = "";
        String phone = "";
        if (pho){
            phone = map.get("tj").toString();
        } else {
            name = map.get("tj").toString();
        }
        List list = suMapper.selectByMap(phone,map.get("sex").toString(),name);
            return list;
    }
    /**
     * @param phone 字符串类型的手机号
     * 传入手机号,判断后返回
     * true为手机号,false相反
     * */
    public static boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            return m.matches();
        }
    }
    /**
     * 查询每个用户的打卡情况
     * @return
     */
    @RequestMapping("/seachUser")
    @ResponseBody
    public List  seachUser (@RequestParam Map map) {
        List<SportGps> sg = sgMapper.selectByPhones(map.get("phone").toString());
            List<SportCp> sportCps = scMapper.selectList(null);
            sportCps.forEach(sportCp ->{
                sg.forEach(sportSg ->{
                    if (sportCp.getId().equals(sportSg.getCpid())) {
                        sportCp.setGpstime(sportSg.getGpstime());
                        sportCp.setSumtime(sportSg.getSumtime());
                        sumtime = sportSg.getSumtime();
                    }
                });
                if  (sportCp.getSumtime()!=null) {
                    pskm += Double.parseDouble(sportCp.getCpkm().trim());
                    //将已经打卡的cp点设置成为
                    String[] my =sumtime.split(":");
                    int hour =Integer.parseInt(my[0]);
                    int min =Integer.parseInt(my[1]);
                    int sec =Integer.parseInt(my[2]);
                    int zong =hour*3600+min*60+sec;
                    int ps = (int) (zong / pskm);
                    int mm = (ps % 3600) / 60;
                    int ss = (ps % 3600) % 60;
                    String  pps = mm + "'" + ss;
                    sportCp.setBz(pps);
                    sportCp.setJl(String.valueOf(pskm)+"km");
                } else {
                    sportCp.setBz("-");
                }

            });
        pskm=0.0;
        return sportCps;
    }
}