package com.xzk.wx.controller;

import com.xzk.bean.Courier;
import com.xzk.bean.Message;
import com.xzk.bean.User;

import com.xzk.service.CourierService;
import com.xzk.service.UserService;
import com.xzk.util.JSONUtil;
import com.xzk.util.UserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @RequestMapping("/wx/loginSms.do")
    @ResponseBody
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
    @RequestMapping("/wx/login.do")
    @ResponseBody
    public String login(HttpServletRequest req, HttpServletResponse resp){
        String userPhone = req.getParameter("userPhone");
        String userCode = req.getParameter("code");
        String sysCode = UserUtil.getLoginSms(req.getSession(), userPhone);
        Message msg = new Message();

            //已注册,可以直接登录
            if(sysCode == null){
                //这个手机号码未获取短信
                msg.setStatus(-1);
                msg.setResult("手机号码未获取短信");
            }else if (sysCode.equals(userCode)){
                //这里是手机号和短信验证码一致,登录成功
                Date loginTime = new Date();
                User user = new User();
                Courier c = CourierService.findByCourierPhone(userPhone);
                User u = UserService.findByUserPhone(userPhone);
                //User u = UserService.findByUserPhone(userPhone);
                UserUtil.setWxUser(req.getSession(), u);//存用户
                if (c != null){
                    //这是快递员
                    //UserService.updateUserLoginTime(loginTime,userPhone);
                    CourierService.updateCourierLoginTime(loginTime,userPhone);
                    msg.setStatus(1);
                    user.setUser(false);
                }else if (u != null){
                    //这是用户
                    UserService.updateUserLoginTime(loginTime,userPhone);
                    int id = u.getId();
                    String username = u.getUsername()==null?"":u.getUsername();
                    String identifyCard = u.getIdentifyCard()==null?"":u.getIdentifyCard();
                    String password = u.getPassword()==null?"":u.getPassword();
                    u.setUsername(username);
                    u.setUserPhone(userPhone);
                    u.setIdentifyCard(identifyCard);
                    u.setPassword(password);
                    msg.setStatus(0);
                    u.setUser(true);
                }else {
                    //该用户未注册,需要注册然后直接登录
                        user.setUserPhone(userPhone);
                        //boolean flag = UserService.insertUserRegistration(user);
                        boolean flag = UserService.insertUser(user);
                        if (flag){
                            //注册成功
                            msg.setStatus(2);
                            msg.setResult("注册成功");
                            msg.setData(user);
                            user.setUser(true);
                        }else{
                            //注册失败
                            msg.setStatus(-3);
                            msg.setResult("注册失败,请重新注册");
                        }

                }
                /*
                 //TODO 这个判断应该替换为快递员表格查询手机号的结果
                if ("16607259654".equals(userPhone)){
                    //快递员
                    //CourierService.updateCourierLoginTime(loginTime,userPhone);
                    UserService.updateUserLoginTime(loginTime,userPhone);
                    msg.setStatus(1);
                    user.setUser(false);
                }else{
                    //用户
                    UserService.updateUserLoginTime(loginTime,userPhone);
                    msg.setStatus(0);
                    user.setUser(true);
                }*/
            }else{
                //这里是验证码不一致,登录失败
                msg.setStatus(-2);
                msg.setResult("验证码不一致,请检查");
            }
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    //获取用户身份
    @RequestMapping("/wx/userInfo.do")
    @ResponseBody
    public String userInfo(HttpServletRequest req, HttpServletResponse resp){
        User user = UserUtil.getWxUser(req.getSession());
        boolean isUser = user.isUser(); //判断是否为用户
        Message msg = new Message();
        if(isUser){//判断是否为用户
            //是用户
            msg.setData(user);
            msg.setStatus(0);
        }else{
            //不是用户
            msg.setStatus(1);
            msg.setData(user);
        }
        msg.setResult(user.getUserPhone());
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    /**
     * 认证短信发送
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping("/wx/sendUserSms.do")
    @ResponseBody
    public String sendUserSms(HttpServletRequest req, HttpServletResponse resp){
        //获取参数
        //String userPhone = req.getParameter("userPhone");
        User user = UserUtil.getWxUser(req.getSession());
        String userPhone = user.getUserPhone();
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
        UserUtil.setUserIdentificationSms(req.getSession(),userPhone,code);
        String json = JSONUtil.toJSON(msg);
        return json;
    }
    /**
     * 用户认证个人信息
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping("/wx/userInsertPerson.do")
    @ResponseBody
    public String userInsertPerson(HttpServletRequest req,HttpServletResponse resp){
        //需要判断输入的验证码与手机短信发送的验证码是否一致
        String username = req.getParameter("username");
        String userPhone = req.getParameter("userPhone");
        String password = req.getParameter("password");
        String code = req.getParameter("code");//获取的验证码
        User user = new User();//需要传相应的参数值
        Message msg = new Message();
        if (user!=null) {
                boolean flag = UserService.insertUser(user);
                if (flag){
                    msg.setStatus(0);
                    msg.setResult("认证成功");
                    msg.setData(user);
                }else{
                    msg.setStatus(-1);
                    msg.setResult("认证失败");
                }
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    /**
     * 用户实名认证
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping("/wx/userIdentification.do")
    @ResponseBody
    public String userIdentification(HttpServletRequest req, HttpServletResponse resp) {
        User wxUser = UserUtil.getWxUser(req.getSession());
        String userPhone = wxUser.getUserPhone();
        int id = wxUser.getId();
        String sysCode = UserUtil.getUserIdentificationSms(req.getSession(), userPhone);
        String username = req.getParameter("username");
        String identifyCard = req.getParameter("identifyCard");
        String password = req.getParameter("password");
        String userCode = req.getParameter("code");//验证码
        //String code = RandomUtil.getCode()+"";//获取验证码
        //boolean flag = SMSUtil.loginSMS(userPhone, code);
        Message msg = new Message();
        if (sysCode == null) {
            //这个手机号码未获取短信
            msg.setStatus(-1);
            msg.setResult("手机号码未获取短信");
        } else if (sysCode.equals(userCode)) {
            //这里是手机号和短信验证码一致,登录成功

            //这里是手机号和短信验证码一致,登录成功
            //Date loginTime = new Date();
            //User user = new User();
            //Courier c = CourierService.findByCourierPhone(userPhone);
            User u = UserService.findByUserPhone(userPhone);
            if ( u != null){
                //这是用户
                //UserService.updateUserLoginTime(loginTime,userPhone);
                u.setUsername(username);
                u.setUserPhone(userPhone);
                u.setIdentifyCard(identifyCard);
                u.setPassword(password);

                UserService.updateUser(id, u);

                msg.setResult("认证成功");
                msg.setStatus(0);
                u.setUser(true);
            }else{
                //用户
                /*user.setUsername(username);
                user.setUserPhone(userPhone);
                user.setIdentifyCard(identifyCard);
                user.setPassword(password);

                UserService.updateUser(id, user);
                user.setUser(true);*/
                msg.setStatus(-1);
                msg.setResult("认证失败");

            }
           /* //TODO 这个判断应该替换为快递员表格查询手机号的结果
            User user = new User();
            if ("16607259654".equals(userPhone)) {
                //快递员
                user.setUsername(username);
                user.setUserPhone(userPhone);
                user.setIdentifyCard(identifyCard);
                user.setPassword(password);
                UserService.updateUser(id, user);
                msg.setStatus(1);
                user.setUser(false);
                msg.setResult("认证成功");
            } else {
                //用户
                user.setUsername(username);
                user.setUserPhone(userPhone);
                user.setIdentifyCard(identifyCard);
                user.setPassword(password);
                UserService.updateUser(id, user);
                user.setUser(true);
                msg.setStatus(0);
                msg.setResult("认证失败");
            }*/

            UserUtil.setWxUser(req.getSession(),u);//存用户

        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    /**
     * 已经实名认证  --TODO 此方法暂时没有用到
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping("/wx/confirmationUserInformation.do")
    @ResponseBody
    public String confirmationUserInformation(HttpServletRequest req, HttpServletResponse resp) {
        User wxUser = UserUtil.getWxUser(req.getSession());
        String userPhone = wxUser.getUserPhone();
        /*String username = wxUser.getUsername();
        String identifyCard = wxUser.getIdentifyCard();
        String password = wxUser.getPassword();*/
        User u = UserService.findByUserPhone(userPhone);
        Courier c = CourierService.findByCourierPhone(userPhone);
        Message msg = new Message();
        if (u!=null){
            //用户
            String username = u.getUsername();
            String identifyCard = u.getIdentifyCard();
            String password = u.getPassword();
            //boolean flag = SMSUtil.loginSMS(userPhone, code);
            if (username!=null && userPhone!=null && identifyCard!=null &&password!=null) {
                wxUser.setUsername(username);
                wxUser.setIdentifyCard(identifyCard);
                wxUser.setPassword(password);
                msg.setStatus(0);
                msg.setResult("已认证");
                msg.setData(wxUser);
            }else{
                msg.setStatus(-1);
            }
        }else if (c!=null){
            //快递员
            String username = c.getUsername();
            String identifyCard = c.getIdentifyCard();
            String password = c.getPassword();
            if (username!=null && userPhone!=null && identifyCard!=null &&password!=null) {
                wxUser.setUsername(username);
                wxUser.setIdentifyCard(identifyCard);
                wxUser.setPassword(password);
                msg.setStatus(0);
                msg.setResult("已认证");
                msg.setData(wxUser);
            }else{
                msg.setStatus(-1);
            }
        }
        String json = JSONUtil.toJSON(msg);
        return json;
    }

    /**
     * 懒人排行
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping("/wx/lazyboard.do")
    @ResponseBody
    public String lazyBoard(HttpServletRequest req, HttpServletResponse resp){
        int type = Integer.valueOf(req.getParameter("type"));
        List<User> lazyboard = UserService.lazyboard(type);
        Message msg = new Message();
        if (lazyboard != null) {
            msg.setStatus(0);
            msg.setData(lazyboard);
        }else{
            msg.setStatus(-1);
            msg.setResult("查询失败");
        }
        return JSONUtil.toJSON(msg);
    }
    /**
     * 用户退出
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping("/wx/logout.do")
    @ResponseBody
    public String logout(HttpServletRequest req, HttpServletResponse resp){
        //1.    销毁session
        req.getSession().invalidate();
        //2.    给客户端回复消息
        Message msg = new Message(0);
        return JSONUtil.toJSON(msg);
    }
}
