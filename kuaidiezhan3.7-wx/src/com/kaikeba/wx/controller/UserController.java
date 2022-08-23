package com.kaikeba.wx.controller;

import com.kaikeba.bean.Message;
import com.kaikeba.bean.User;
import com.kaikeba.mvc.ResponseBody;
import com.kaikeba.util.JSONUtil;
import com.kaikeba.util.RandomUtil;
import com.kaikeba.util.SMSUtil;
import com.kaikeba.util.UserUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserController {

    @ResponseBody("/wx/loginSms.do")
    public String sendSms(HttpServletRequest req, HttpServletResponse resp){
        //获取参数
         String userPhone = req.getParameter("userPhone");
        //String code = RandomUtil.getCode()+"";//获取验证码
        //boolean flag = SMSUtil.loginSMS(userPhone, code);

        //后期需要短信发送,取消上面三条注释即可,然后删除下面三条即可.
        String code = "123456";
        boolean flag = true;
      //String userPhone = "15897916969";

        Message msg = new Message();
        if(flag){
            //短信发送成功
            msg.setStatus(0);
            msg.setResult("验证码发送,请查收!");
        }else{
            //短信发送失败
            msg.setStatus(1);
            msg.setResult("验证码下发失败,请检查手机号或稍后再试");
        }
        //存验证码
        UserUtil.setLoginSms(req.getSession(),userPhone,code);
        String json = JSONUtil.toJSON(msg);
        return json;
    }
    /**
     * 用户登录
     * @param req
     * @param resp
     * @return
     */
    @ResponseBody("/wx/login.do")
    public String login(HttpServletRequest req, HttpServletResponse resp){
        String userPhone = req.getParameter("userPhone");
        String userCode = req.getParameter("code");
        String sysCode = UserUtil.getLoginSms(req.getSession(), userPhone);
        Message msg = new Message();
        if(sysCode == null){
            //这个手机号码未获取短信
            msg.setStatus(-1);
            msg.setResult("手机号码未获取短信");
        }else if (sysCode.equals(userCode)){
            //这里是手机号和短信验证码一致,登录成功
            //TODO 这个判断应该替换为快递员表格查询手机号的结果
            User user = new User();
            if ("16607259654".equals(userPhone)){
                //快递员
                msg.setStatus(1);
                user.setUser(false);
            }else{
                //用户
                msg.setStatus(0);
                user.setUser(true);
            }
            user.setUserPhone(userPhone);
            UserUtil.setWxUser(req.getSession(),user);//存用户
        }else{
            //这里是验证码不一致,登录失败
            msg.setStatus(-2);
            msg.setResult("验证码不一致,请检查");
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    //获取用户身份
    @ResponseBody("/wx/userInfo.do")
    public String userInfo(HttpServletRequest req, HttpServletResponse resp){
        User user = UserUtil.getWxUser(req.getSession());
        boolean isUser = user.isUser(); //判断是否为用户
        Message msg = new Message();
        if(isUser){//判断是否为用户
            //是用户
            msg.setStatus(0);
        }else{
            //不是用户
            msg.setStatus(1);
        }
        msg.setResult(user.getUserPhone());
        String json = JSONUtil.toJSON(msg);
        return json;
    }
    /**
     * 用户退出
     * @param req
     * @param resp
     * @return
     */
    @ResponseBody("/wx/logout.do")
    public String logout(HttpServletRequest req, HttpServletResponse resp){
        //1.    销毁session
        req.getSession().invalidate();
        //2.    给客户端回复消息
        Message msg = new Message(0);
        return JSONUtil.toJSON(msg);
    }
}
