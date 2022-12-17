package com.example.LqcSpringBoot.controller;

import com.example.LqcSpringBoot.mapper.KunyueyeuserMapper;
import com.example.LqcSpringBoot.model.Kunyueyeuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/kunyueye")
public class LoginController {

    @Autowired
    public KunyueyeuserMapper kmapper;

    /**
     * 退出
     * @param request
     * @return
     */
    @RequestMapping("/out")
    @ResponseBody
    public String out (HttpServletRequest request) {
        String flag ="success";
        request.getSession().removeAttribute("token");
        return flag ;
    }
    /**
     * 登录
     * @return
     */
    @CrossOrigin
    @RequestMapping("/loginsubmit")
    @ResponseBody
    public Object loginsubmit (HttpSession session, HttpServletResponse response , @RequestParam Map map) {
        String flag ="success";
            String code = (String)session.getAttribute("valistr");
            if (code==null) {
                return "yzmerrornull";
            } else {
                if (!map.get("code").toString().toLowerCase().equals(code.toLowerCase())) {
                    System.out.println("返回前台");
                    return "yzmerror";
                }
            }
        List<Kunyueyeuser> list = kmapper.selectUserByNameAndPassword(map.get("name").toString(),map.get("password").toString());
        if (list.size()>0) {
            session.setAttribute("token", list.get(0));
            /*try {
                response.sendRedirect( "http://localhost:8081/membe/index" );
            } catch (IOException e) {
                e.printStackTrace();
            }*/
        } else {
            session.setAttribute("token", null);
            flag="error";
        }
        return flag;
    }
}
