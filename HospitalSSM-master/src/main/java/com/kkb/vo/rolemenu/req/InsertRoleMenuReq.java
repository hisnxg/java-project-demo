package com.kkb.vo.rolemenu.req;

import com.kkb.vo.rolemanager.req.SelectRoleByIdReq;
import lombok.Data;

@Data
public class InsertRoleMenuReq {
    private Integer roleId;
    private int[]roleIds;

    public InsertRoleMenuReq(Integer roleId, int[] roleIds) {
        this.roleId = roleId;
        this.roleIds = roleIds;
    }

    public InsertRoleMenuReq(){}
}
