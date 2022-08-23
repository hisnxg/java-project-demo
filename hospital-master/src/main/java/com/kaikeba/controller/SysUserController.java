package com.kaikeba.controller;

import com.github.pagehelper.PageInfo;
import com.kaikeba.pojo.Role;
import com.kaikeba.pojo.SysUser;
import com.kaikeba.service.SysUserService;
import com.kaikeba.vo.QueryUserRoleMenuVO;
import com.kaikeba.vo.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户
 */
@Controller
@RequestMapping("sysUser")
@ResponseBody
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    //1.分页+多条件查询
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ResultVO<SysUser> selectByPage(Integer pageNum, Integer pageSize, QueryUserRoleMenuVO vo){
        System.out.println("pageNum:"+pageNum);
        if(pageNum==null||pageNum<1) pageNum=1;
        if (pageSize==null||pageSize<1) pageNum=5;
        PageInfo<SysUser> pageInfo=sysUserService.selectByPage(pageNum,pageSize,vo);
        System.out.println("分页数据是:"+pageInfo);
        return new ResultVO<>(pageInfo);
    }

    //2.添加用户
    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResultVO<SysUser> insertSysUser(SysUser user){
        System.out.println("待添加的用户信息:"+user);
        int insert = sysUserService.insertUser(user);
        if (insert==1) return new ResultVO<>();
        else return new ResultVO<>(500,"服务器异常，稍后再试");
    }


    //3.更新用户
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public ResultVO updateSysUser(@PathVariable("id") Integer id,SysUser sysUser){
        sysUser.setId(id); //id根据路径传递，更新数据根据表单传递,sysUser中没有id值
        int updadte = sysUserService.updateSysUser(sysUser);
        if (updadte==1) return new ResultVO<>();
        else return new ResultVO<>(500,"服务器异常，稍后再试");
    }

    //4.查询所有角色 添加/更新用户时角色的下拉菜单
    @RequestMapping(value = "roles",method = RequestMethod.GET)
    public ResultVO<Role>selectAllRole(){
        List<Role> roles = sysUserService.selectAllRole();
        return new ResultVO<>(roles);
    }

    //5.根据id查询用户信息  用于更新用户时进行回显
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public ResultVO<SysUser>selectSysUserById(@PathVariable("id") Integer id){
        SysUser sysUser=sysUserService.selectSysUser(id);
        return new ResultVO<>(sysUser);
    }


    //6.删除用户 --逻辑删除，禁用
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public ResultVO deleteSysUsesr(@PathVariable("id") Integer id){
        int delete=sysUserService.deleteSysUser(id);
        if (delete==1) return new ResultVO<>();
        else return new ResultVO<>(500,"服务器异常，稍后再试");
    }

    //7.批量删除
    @RequestMapping(value = "all",method = RequestMethod.DELETE)
    public ResultVO deleteSelected(String[] userIds){
        System.out.println("待删除的userId:"+userIds);
        boolean flag = sysUserService.deleteSelected(userIds);
        if (flag) return  new ResultVO<>();
        else return new ResultVO(500,"服务器异常，稍后再试");
    }

    //8.根据账号获取用户  包含角色 菜单
    @RequestMapping(value = "loginName",method = RequestMethod.GET)
    public ResultVO<SysUser>selectByLoginName(@RequestParam("loginName") String loginName){
        SysUser sysUser=sysUserService.selectByLoginName(loginName);
        return new ResultVO<>(sysUser);
    }

    //9.核实用户账号是否存在 --账号唯一性验证
    @RequestMapping(value = "checkName",method = RequestMethod.GET)
    public ResultVO checkByLoginName(String loginName){
        boolean flag=sysUserService.checkByLoginName(loginName);
        if (flag) return new ResultVO<>(500,"该账号名已存在，请更换!");
        else return new ResultVO(200,"用户账号唯一性验证通过!");
    }
}
