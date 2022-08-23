package com.kkb.controller.menumanager;


import com.github.pagehelper.PageInfo;
import com.kkb.bean.Menu;
import com.kkb.service.menumanager.MenuManagerService;
import com.kkb.vo.BaseResp;

import com.kkb.vo.menumanager.req.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuManagerController {
    @Autowired
    private MenuManagerService menuManagerService;
    private Integer menuId;
    //按照名称模糊查询所有并分页
    @PostMapping("/selectMenu")
    public BaseResp<Map<String,Object>> selectMenu(SelectMenuReq param){
        if (param.getPageNum()==null){
            param.setPageNum(1);
        }
        PageInfo<Menu> menuPage = menuManagerService.queryByParam(param);
        Map<String,Object> map = new HashMap<>();
        map.put("menuPage",menuPage);
        map.put("param",param);

        return new BaseResp<>(map);
    }
    //添加资源
    @PostMapping("addMenu")
    public BaseResp<Map<String,Object>> addMenu(AddMenuReq param){
        System.out.println(param);
        try {
            menuManagerService.addMenu(param);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1000",true,"添加库存失败");
        }
        return new BaseResp<>();
    }
     //更新资源
    @PostMapping("/updateMenu")
    public BaseResp<Map<String,Object>> updateMenu(UpdateMenuReq param){
        System.out.println(param);
        try {
            param.setMenuId(menuId);
            menuManagerService.updateMenu(param);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1000",true,"更新失败");
        }
        return new BaseResp<>();
    }
    //根据资源id删除资源
    @PostMapping("/deleteMenu")
    public BaseResp<Map<String,Object>> deleteMenu( DeleteMenuReq param){
        System.out.println(param);
        try {
           menuManagerService.deleteMenu(param);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1000",true,"删除失败");
        }
        return new BaseResp<>();
    }
    //根据资源ids删除多条数据
    @PostMapping("/deleteMenuByIds")
    public BaseResp<Map<String,Object>> deleteMenuByIds(DeleteMoreMenuReq param){
        System.out.println(param);
        try {
            menuManagerService.deleteMenuByIds(param);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1000",true,"删除失败");
        }
        return new BaseResp<>();
    }
    @PostMapping("/selectByPrimaryKey")
    public BaseResp<Map<String,Object>> selectByPrimaryKey(Integer integer){
        this.menuId=integer;
        System.out.println(integer);
        Menu menu;
        try {
          menu= menuManagerService.selectByPrimaryKey(integer);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1000",true,"查询失败");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("menu", menu);
        return new BaseResp<>(map);
    }
    @PostMapping("/selectMenuByName")
    public BaseResp<Map<String,Object>> selectMenuByName(SelectMenuReq param){
        System.out.println(param);
        Menu menu;
        try {
            menu= menuManagerService.selectMenuByName(param);
        } catch (Exception e) {
            e.printStackTrace();
            return new BaseResp<>("1000",true,"删除失败");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("menu",menu);
        return new BaseResp<>(map);
    }
}
