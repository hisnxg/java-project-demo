package com.kkb.service.rolemanager;

import com.github.pagehelper.PageInfo;
import com.kkb.bean.Menu;
import com.kkb.bean.Role;
import com.kkb.vo.menumanager.req.SelectMenuReq;
import com.kkb.vo.rolemanager.req.*;
import com.kkb.vo.rolemenu.req.AddRoleMenuReq;

public interface RoleManagerService {
    //分页
    PageInfo<Role> queryByParam(SelectRoleReq param);
    //添加角色
    void addRole(AddRoleReq param);
    //更新角色
    void editRole(EditRoleReq editRoleReq);
    //通过角色id删除角色
    void deleteRole(DeleteRoleReq param);
    //通过角色ids删除多个角色
    void deleteRoleByIds(DeleteMoreRoleReq param);
    Role selectRidByName(String roleName);
    Role SelectRoleById(Integer roleId);
    Role selectRoleByName(SelectRoleReq selectRoleReq);


}