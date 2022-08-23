package com.kkb.service.rolemenu;

import com.kkb.bean.RoleMenu;
import com.kkb.vo.rolemanager.req.SelectRoleByIdReq;
import com.kkb.vo.rolemenu.req.AddRoleMenuReq;
import com.kkb.vo.rolemenu.req.InsertRoleMenuReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleMenuService {
    void insertRoleMenu(InsertRoleMenuReq insertRoleMenuReq);
    List<RoleMenu> selectMenuId(Integer roleId);
    void deleteRoleMenuById (Integer roleId);
    void addRoleMenu(AddRoleMenuReq addRoleMenuReq);
}
