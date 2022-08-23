package com.kaikeba.controller;

import com.github.pagehelper.PageInfo;
import com.kaikeba.pojo.Menu;
import com.kaikeba.service.MenuService;
import com.kaikeba.vo.QueryUserRoleMenuVO;
import com.kaikeba.vo.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单处理器
 */
@Controller
@RequestMapping("menu")
@ResponseBody
public class MenuController {

    @Resource
    private MenuService menuService;

    //1.菜单添加
    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResultVO insertMenu(Menu menu){
        int insert =menuService.insertMenu(menu) ;
        if (insert==1) return new ResultVO();
        else return new ResultVO(500,"服务器异常，稍后再试");
    }

    //2.根据id更新菜单
    @RequestMapping(value = "{id}",method = RequestMethod.PUT)
    public ResultVO updateMenu(@PathVariable("id") Integer id, Menu menu){
        menu.setId(id);  //表单获取到的菜单没有id
        int insert = menuService.updateMenu(menu);
        if (insert==1) return new ResultVO();
        else return new ResultVO(500,"服务器异常，稍后再试");
    }

    //3.根据id删除菜单
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public ResultVO updateMenu(@PathVariable("id") Integer id){
        int insert = menuService.deleteMenu(id);
        if (insert==1) return new ResultVO();
        else return new ResultVO(500,"服务器异常，稍后再试");
    }

    //4.批量删除
    @RequestMapping(value = "all",method = RequestMethod.DELETE)
    public ResultVO deleteAll(@RequestParam("menuIds") String[] menuIds){
        System.out.println("待删除的ids:"+menuIds);
        boolean flag= menuService.deleteSelected(menuIds);
        if (flag) return new ResultVO();
        else return new ResultVO(500,"服务器异常，稍后再试");
    }

    //5.分页+多条件查询
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ResultVO<Menu> selectByPage(Integer pageNum, Integer pageSize, QueryUserRoleMenuVO vo){
        if(pageNum==null||pageNum<1) pageNum=1;
        if (pageSize==null||pageSize<1) pageNum=5;
        PageInfo<Menu> pageInfo=menuService.selectByPage(pageNum,pageSize,vo);
        return new ResultVO<>(pageInfo);
    }

    //6.根据id查询菜单 --更新菜单时的回显
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public ResultVO selectById(@PathVariable("id") Integer menuId){
        Menu menu =menuService.selectById(menuId);
        return new ResultVO(menu);
    }

    //7.获取所有菜单  --添加/修改角色时的回显
    @RequestMapping(value = "all",method = RequestMethod.GET)
    public ResultVO selectAll(){
        System.out.println("开始获取所有菜单");
        List<Menu> menus = menuService.selectAllMenu();
        System.out.println("获取到的所有菜单:"+menus);
        return new ResultVO(menus);
    }

    //8.通过菜单名称查询菜单 --菜单名称校验
    @RequestMapping(value = "menuName",method = RequestMethod.GET)
    public ResultVO selectMenuName(String menuName){
        boolean flag=menuService.selectByMenuName(menuName);
        if (flag) return new ResultVO<>(500,"该菜单已存在，请更换");
        return new ResultVO(200,"菜单唯一性验证通过");
    }

}
