package com.kkb.dao;

import com.kkb.bean.RoleMenu;
import com.kkb.vo.rolemanager.req.SelectRoleByIdReq;
import com.kkb.vo.rolemenu.req.AddRoleMenuReq;
import com.kkb.vo.rolemenu.req.InsertRoleMenuReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMenuMapper {
    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);
    int insertRoleMenu(@Param("insertRoleMenuReq") InsertRoleMenuReq insertRoleMenuReq);
   List<RoleMenu> selectRoleMenuById(Integer roleId);
   int deleteRoleMenuById(Integer roleId);
    int addRoleMenu(@Param("addRoleMenuReq") AddRoleMenuReq addRoleMenuReq);
}