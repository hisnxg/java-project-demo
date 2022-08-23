package com.kaikeba.controller;

import com.kaikeba.pojo.SysUser;
import com.kaikeba.service.SystemService;
import com.kaikeba.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
@RequestMapping("system")
@ResponseBody
public class SystemController {
    @Autowired
    private SystemService systemService;

    /**
     * 系统登录
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public ResultVO<SysUser> login(String userName, String password, HttpSession session){
        SysUser sysUser = systemService.login(userName, password);
        if (sysUser==null){
            ResultVO resultVO = new ResultVO(100, "用户名或密码错误，请检查！");
            return resultVO;
        }else{
            if (sysUser.getStatus()==1){
                return new ResultVO(300,"此用户已被禁用，请联系管理员！");
            }else {
                session.setAttribute("loginName",sysUser.getLoginName());
                ResultVO<SysUser> sysUserResultVO = new ResultVO<>();
                sysUserResultVO.setObj(sysUser);
                return sysUserResultVO;
            }
        }
    }

    /**
     * 获取随机验证码
     * @return
     */
    @RequestMapping(value = "random",method = RequestMethod.GET)
    public Integer random(){
        Random random=new Random();
        int x=random.nextInt(9000)+1000;
        return x;
    }

    /**
     * 用户退出
     * @param session
     * @return
     */
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public ResultVO out(HttpSession session){
        session.invalidate();
        return new ResultVO();
    }
}
