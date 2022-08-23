package com.kkb.controller.hsp.login;

import com.alibaba.druid.support.json.JSONUtils;
import com.kkb.bean.User;
import com.kkb.message.Message;
import com.kkb.service.hsp.usermanager.UserService;
import com.kkb.service.usermanager.impl.UserManagerServiceImpl;
import com.kkb.utils.Md5Utils;
import com.kkb.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author 何世鹏
 * @Date 2021/3/18 16:11
 *
 */
@RestController
public class LoginController {
    @Autowired
    private UserService userManagerService;

    @Autowired
    private Message message;

    private Md5Utils md5 = new Md5Utils();

    @RequestMapping(value = "/userLogin")
    public String userLogin( String userName,  String password, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("前端获取的账号：" + userName);
        System.out.println("前端获取的密码：" + password);
        User user = new User();
        user.setuLoginname(userName);
        //进行md5加密
        String s = null;
        try {
            s = md5.EncoderByMd5(password);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("异常1");
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            System.out.println("异常2");
            e.printStackTrace();
        }
        System.out.println("加密后的密码" + s);
        user.setuPassword(s);

        //根据前台传入的账号密码查询用户ID
        Integer integer = userManagerService.queryByParam(user);
        user.setuId(integer);
        //根据ID查询用户状态
        Integer state = userManagerService.queryById(user);
        System.out.println(state);
        HttpSession session = request.getSession();

        if (state != null && state == 1){
            System.out.println("被禁用的用户尝试登录");
            message.setMessage("Banned!!!");
            message.setCode(1);
        }else {
            if (integer != null){
                session.setAttribute("id" ,integer);
                System.out.println(" controller " + session.getAttribute("id"));
                message.setCode(0);
                message.setMessage("Login successful");
            }else {
                message.setCode(-1);
                message.setMessage("Login failure");
            }
        }

        return MessageUtil.toJson(message);
    }

    @GetMapping("/quit")
    public void userQuit(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        try {
            response.sendRedirect("login.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/updatePassword")
    public String updatePassword(String p1,String p2,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        User user = new User();
        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute("id");
        user.setuId(id);
        //查询到原来的密码
        String s = userManagerService.selectUserPassword(user);
        boolean flag = md5.checkPassword(p1, s);
        if (s != null && flag) {
            //进行加密
            String s1 = md5.EncoderByMd5(p2);
            user.setuPassword(s1);
            //更新密码
            userManagerService.updateUserPassword(user);

            message.setCode(0);
            message.setMessage("update,ok!");
            System.out.println("更新成功");
        }else {
            message.setCode(-1);
            message.setMessage("update,err!");
            System.out.println("更新失败");
        }
        String s1 = MessageUtil.toJson(message);
        System.out.println(s1);
        return s1;
    }

    @RequestMapping("/passwordCorrect")
    public String passwordCorrect(String p1,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        User user = new User();
        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute("id");
        user.setuId(id);
        //查询到原来的密码
        String s = userManagerService.selectUserPassword(user);
        boolean flag = md5.checkPassword(p1, s);
        if (s != null && flag) {
            message.setCode(0);
            message.setMessage("update,ok!");
            System.out.println("密码正确");
        }else {
            message.setCode(-1);
            message.setMessage("update,err!");
            System.out.println("密码错误");
        }
        String s1 = MessageUtil.toJson(message);
        System.out.println(s1);
        return s1;
    }

    @GetMapping(value = "/getUserName",produces = "application/json; charset=utf-8")
    public Message getUserName(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Integer id =(Integer) session.getAttribute("id");
        if(id != null){
            String name = userManagerService.selectUserName(id);
            message.setMessage(name);
            message.setCode(0);
            return message;
        }else {
            message.setMessage("未登录");
            message.setCode(-1);
            return message;
        }
    }

}
