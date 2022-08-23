package com.nxg.controller;

import com.nxg.service.SysUserService;
import com.nxg.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 更新密码
 */
@RestController
@RequestMapping("password")
public class UpdatePasswordController {

    @Autowired
    private SysUserService sysUserService;

    //核实原密码
    @RequestMapping(value = "{sysUserId}",method = RequestMethod.GET)
    public ResultVO<Object> checkPassword(@PathVariable("sysUserId") Integer sysUserId,String password){
        boolean check = sysUserService.checkPassword(sysUserId,password);
        if(check) return new ResultVO<>(200,"原密码输入正确");
        else return new ResultVO<>(500,"原密码输入错误");
    }

    //更新新密码
   @RequestMapping(value = "update",method = RequestMethod.PUT)
    public ResultVO<Object> updatePassword(Integer sysUserId, String newPassword){
        int update = sysUserService.updatePassword(sysUserId, newPassword);
        ResultVO<Object> data = new ResultVO<>();
        if(update==1) return new ResultVO<>(200,"更新成功");
        return null;
    }
}
