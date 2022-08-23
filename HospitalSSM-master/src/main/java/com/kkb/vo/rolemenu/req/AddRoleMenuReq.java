package com.kkb.vo.rolemenu.req;

import com.kkb.vo.rolemanager.req.SelectRoleByIdReq;
import lombok.Data;

@Data
public class AddRoleMenuReq {

    private int rid;
    private int[]roleIds;

    public AddRoleMenuReq(int rid, int[] roleIds) {
        this.rid = rid;
        this.roleIds = roleIds;
    }

    public AddRoleMenuReq(){}
}
