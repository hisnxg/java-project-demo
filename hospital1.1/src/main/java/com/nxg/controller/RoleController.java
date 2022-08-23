package com.nxg.controller;

import com.github.pagehelper.PageInfo;
import com.nxg.pojo.Role;
import com.nxg.pojo.RoleExample;
import com.nxg.service.RoleService;
import com.nxg.vo.QueryUserRoleMenuVO;
import com.nxg.vo.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 角色
 */
@Controller
@RequestMapping("role")
@ResponseBody
public class RoleController {

    @Resource
    private RoleService roleService;

    //1.分页+多条件查询角色
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ResultVO<Role>selectByPage(Integer pageNum, Integer pageSize, QueryUserRoleMenuVO vo){
        if(pageNum==null||pageNum<1) pageNum=1;
        if (pageSize==null||pageSize<1) pageNum=5;
        PageInfo<Role> pageInfo=roleService.selectByPage(pageNum,pageSize,vo);
        return new ResultVO<>(pageInfo);
    }

    //2.添加角色
   @RequestMapping(value = "",method = RequestMethod.POST)
    public ResultVO<Role>insertRole(Role role, HttpServletRequest request){
       String[] menuids = request.getParameterValues("group"); //获取选中的所有菜单id
       boolean flag = roleService.insertRole(role,menuids); //添加到角色表
       if (flag) return new ResultVO<>();
       else return new ResultVO<>(500,"服务器异常，稍后再试");
    }

    //3.根据id查询角色
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public ResultVO<Role> selectById(@PathVariable("id") Integer roleId){
        Role role=roleService.selectById(roleId);
        System.out.println("查询到的角色信息:"+role);
        return new ResultVO<>(role);
    }

    //4.根据Id更新角色
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public ResultVO updateRole(@PathVariable("id") Integer id, Role role,HttpServletRequest request){
        role.setId(id);  //表单更新数据没有角色id，从路径中获取补上
        String[] menuids = request.getParameterValues("group");//获取选中的所有菜单id
        boolean flag=roleService.updateRole(role,menuids);
        if (flag) return new ResultVO<>();
        else return new ResultVO<>(500,"服务器异常，稍后再试");
    }

    //5.批量删除
    @RequestMapping(value = "all",method = RequestMethod.DELETE)
    public ResultVO deleteSelected(@RequestParam(value = "roleIds") String[] roleIds){
        System.out.println("待删除的roleIds:"+roleIds);
        boolean flag = roleService.deleteSelected(roleIds);
        if (!flag)  return new ResultVO(500,"删除失败或服务器没有接收到参数");
        return new ResultVO();
    }

    //6.通过角色名称查询角色 --角色唯一性验证
    @RequestMapping(value = "roleName",method = RequestMethod.GET)
    public ResultVO selectByRoleName(String roleName){
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria = roleExample.createCriteria();
        criteria.andRoleNameEqualTo(roleName);
        boolean flag=roleService.selectByRoleName(roleExample);
        if (flag) return new ResultVO(500,"该角色已存在，请更换");
        return new ResultVO(200,"角色唯一性验证已通过");
    }
}
