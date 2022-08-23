package com.kkb.vo.rolemanager.req;

import lombok.Data;

@Data
public class SelectRoleReq {

  /**
 * 当前页
 */

private Integer pageNum ;

    /**
     * 一页信息数
     */
    private Integer pageSize = 5;

    /**
     * 用户名称
     */
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
