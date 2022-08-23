package com.kkb.service.menumanager;

import com.github.pagehelper.PageInfo;
import com.kkb.bean.Menu;

import com.kkb.vo.menumanager.req.*;
import org.springframework.stereotype.Service;

@Service
public interface MenuManagerService {
    //分页查询
    PageInfo<Menu> queryByParam(SelectMenuReq param);
    //添加资源接口
    void addMenu(AddMenuReq param);
    //更新某一个资源
    void updateMenu(UpdateMenuReq updateMenuReq);
    //删除某一个资源
    void deleteMenu(DeleteMenuReq deleteMenuReq);
    //删除多条资源
    void deleteMenuByIds(DeleteMoreMenuReq param);
    Menu selectByPrimaryKey(Integer integer);
    Menu selectMenuByName(SelectMenuReq selectMenuReq);

}
