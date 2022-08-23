package com.xzk.util;

import com.xzk.bean.User;

import javax.servlet.http.HttpSession;

public class UserUtil {
    public static String getUserName(HttpSession session){
        return (String) session.getAttribute("adminUserName");
    }
    // TODO : 还没有编写柜子端,未存储任何的录入人信息
    public static String getSysPhone(HttpSession session){
        return "16666669654";
    }

    /**
     * 用户登录
     * 存验证码
     * @param session
     * @return
     * set没有返回值
     */
    public static void setLoginSms(HttpSession session,String userPhone,String code){
        //根据手机号码存验证码,键:手机号 值:验证码
       session.setAttribute(userPhone,code);
    }
    //取验证码
    public static String getLoginSms(HttpSession session,String userPhone){
        return (String) session.getAttribute(userPhone);
    }
    //微信用户
    public static  void setWxUser(HttpSession session,User user){

        session.setAttribute("wxUser",user);
    }
    public static  User getWxUser(HttpSession session){
       return (User) session.getAttribute("wxUser");
    }

    /**
     * 用户 实名 认证
     * 存验证码
     * @param session
     * @return
     * set没有返回值
     */
    public static void setUserIdentificationSms(HttpSession session,String userPhone,String code){
        //根据手机号码存验证码,键:手机号 值:验证码
        session.setAttribute(userPhone,code);
    }
    //取验证码
    public static String getUserIdentificationSms(HttpSession session,String userPhone){
        //根据手机号码取验证码
        return (String) session.getAttribute(userPhone);
    }

}
