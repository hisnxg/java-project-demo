package com.kkb.vo.rolemanager.req;

import com.kkb.bean.Menu;
import lombok.Data;



@Data
public class AddRoleReq {
    private String roleName;

    private int state;
    private int []roleIds;
}
