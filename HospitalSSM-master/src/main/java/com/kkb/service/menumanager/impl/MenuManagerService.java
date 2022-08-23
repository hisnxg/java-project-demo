package com.kkb.service.menumanager.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kkb.bean.Menu;
import com.kkb.dao.MenuMapper;
import com.kkb.vo.menumanager.req.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuManagerService implements com.kkb.service.menumanager.MenuManagerService {
    @Autowired
    private MenuMapper menuMapper;
    //分页查询
    @Override
    public PageInfo<Menu> queryByParam(SelectMenuReq param) {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<Menu> menuList = menuMapper.selectMenuByParam(param);
        return new PageInfo<>(menuList);

    }
     //添加资源
    @Override
    public void addMenu(AddMenuReq addMenuReq) {
        menuMapper.insert(addMenuReq);
    }
     //通过id更新资源
    @Override
    public void updateMenu(UpdateMenuReq updateMenuReq) {
       menuMapper.updateByPrimaryKey(updateMenuReq);
    }
    //通过id删除资源
    @Override
    public void deleteMenu(DeleteMenuReq deleteMenuReq) {
       menuMapper.deleteByPrimaryKey(deleteMenuReq);

    }
     //通过ids删除多条资源
    @Override
    public void deleteMenuByIds(DeleteMoreMenuReq param) {
        menuMapper.deleteMenuByIds(param);
    }

    @Override
    public Menu selectByPrimaryKey(Integer integer) {
        Menu menu=menuMapper.selectByPrimaryKey(integer);
        return menu;
    }

    @Override
    public Menu selectMenuByName(SelectMenuReq selectMenuReq) {
       Menu menu= menuMapper.selectMenuByName(selectMenuReq);
       return menu;
    }

}
