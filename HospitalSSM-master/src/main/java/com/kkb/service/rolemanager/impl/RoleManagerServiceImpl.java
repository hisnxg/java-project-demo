package com.kkb.service.rolemanager.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kkb.bean.Role;
import com.kkb.dao.RoleMapper;
import com.kkb.service.rolemanager.RoleManagerService;
import com.kkb.vo.rolemanager.req.*;
import com.kkb.vo.rolemenu.req.AddRoleMenuReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class RoleManagerServiceImpl implements RoleManagerService {
    @Autowired
    private RoleMapper roleMapper;
    //分页
    @Override
    public PageInfo<Role> queryByParam(SelectRoleReq param) {
         PageHelper.startPage(param.getPageNum(), param.getPageSize());
         List<Role> listRole=roleMapper.selectRoleByParam(param);
         PageInfo<Role> pageInfo=new PageInfo<>(listRole);
         return pageInfo;
    }
    //添加角色
    @Override
    public void addRole(AddRoleReq param) {
         roleMapper.insert(param);
    }
    //通过角色id更新
    @Override
    public void editRole(EditRoleReq editRoleReq) {
        roleMapper.updateByRoleId(editRoleReq);

    }

    @Override
    public void deleteRole(DeleteRoleReq param) {
     roleMapper.deleteByPrimaryKey(param);

    }

    @Override
    public void deleteRoleByIds(DeleteMoreRoleReq param) {
        roleMapper.deleteRoleByIds(param);
    }

    @Override
    public Role selectRidByName(String roleName) {
      Role role=  roleMapper.selectByName(roleName);
        return role;
    }

    @Override
    public Role SelectRoleById(Integer roleId) {
      Role role= roleMapper.selectRoleById(roleId);
      return role;
    }

    @Override
    public Role selectRoleByName(SelectRoleReq selectRoleReq) {
        Role role=roleMapper.selectRoleByName(selectRoleReq);
        return role;
    }


}

