package com.kkb.vo.rolemanager.req;

import lombok.Data;

import java.util.ArrayList;

@Data
public class EditRoleReq {
    private String roleName;
    private int state;
    private int []rmIds;
    private Integer roleId;



    public EditRoleReq() {
    }
}
