package com.kkb.service.rolemenu.impl;

import com.kkb.bean.RoleMenu;
import com.kkb.dao.RoleMenuMapper;
import com.kkb.service.rolemenu.RoleMenuService;
import com.kkb.vo.rolemanager.req.SelectRoleByIdReq;
import com.kkb.vo.rolemenu.req.AddRoleMenuReq;
import com.kkb.vo.rolemenu.req.InsertRoleMenuReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleMenuServiceImpl implements RoleMenuService {
    @Autowired
    private RoleMenuMapper roleMenuMapper;


    @Override
    public void insertRoleMenu(InsertRoleMenuReq insertRoleMenuReq) {
          roleMenuMapper.insertRoleMenu(insertRoleMenuReq);
    }

    @Override
    public List<RoleMenu> selectMenuId(Integer roleId) {
      List<RoleMenu> roleMenu= roleMenuMapper.selectRoleMenuById(roleId);
       return roleMenu;
    }

    @Override
    public void deleteRoleMenuById(Integer roleId) {
        roleMenuMapper.deleteRoleMenuById(roleId);
    }

    @Override
    public void addRoleMenu(AddRoleMenuReq addRoleMenuReq) {
        roleMenuMapper.addRoleMenu(addRoleMenuReq);
    }


}
