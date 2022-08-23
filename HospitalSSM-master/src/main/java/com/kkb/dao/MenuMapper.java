package com.kkb.dao;

import com.kkb.bean.Menu;
import com.kkb.vo.menumanager.req.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {
    //根据资源id删除资源
    int deleteByPrimaryKey(DeleteMenuReq deleteMenuReq);

    int insert(AddMenuReq addMenuReq);

    int insertSelective(Menu record);
    //根据用户名称模糊查询所有
    List<Menu> selectMenuByParam(SelectMenuReq selectMenuReq);
    Menu selectByPrimaryKey(Integer integer);
    int updateByPrimaryKeySelective(Menu record);
    Menu selectMenuByName(SelectMenuReq selectMenuReq);
    int updateByPrimaryKey(UpdateMenuReq updateMenuReq);
    //根据ids删除多条
    int deleteMenuByIds(@Param("deleteMoreMenuReq") DeleteMoreMenuReq deleteMoreMenuReq);

    List<Menu> selectByUserId(Integer id);
}