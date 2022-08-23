package com.xzk.controller;

import com.xzk.bean.Message;

import com.xzk.service.AdminService;
import com.xzk.util.JSONUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
public class AdminController {

    @RequestMapping("/admin/login.do")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response){
        //1.    接参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //2.    调用Service传参数，并获取结果
        boolean result = AdminService.login(username, password);
        //3.    根据结果，准备不同的返回数据
        Message msg = null;
        if(result){
            msg = new Message(0,"登录成功");//{status:0,result:"登录成功"}
            //登录时间 和 ip的更新
            Date date = new Date();
            //Date format = DateFormatUtil.format(date);
            String  ip = request.getRemoteAddr();//获取ip地址
            AdminService.updateLoginTimeAndIP(username,date,ip);
            request.getSession().setAttribute("adminUserName","username");
        }else{
            msg = new Message(-1,"登录失败");//{status:-1,result:"登录失败"}
        }
        //4.    将数据转换为JSON
        String json = JSONUtil.toJSON(msg);
        //5.    将JSON回复给ajax
        return json;
    }
    @RequestMapping("/admin/logout.do")
    @ResponseBody
    public String logout(HttpServletRequest request, HttpServletResponse response){
        //1.销毁session
        request.getSession().invalidate();
        //2.给客户端回复消息
        Message msg = new Message();
        msg.setStatus(0);
        String json = JSONUtil.toJSON(msg);
        return json;
    }

}
