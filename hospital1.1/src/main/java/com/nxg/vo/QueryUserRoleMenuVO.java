package com.nxg.vo;

/**
 * 多条件查询的类：用户，角色，菜单 放在一起了
 */
public class QueryUserRoleMenuVO {
    private String loginName; //账户
    private String roleName;//角色名称
    private String menuName;//菜单名称

    public QueryUserRoleMenuVO() {
    }

    public QueryUserRoleMenuVO(String loginName, String roleName, String menuName) {
        this.loginName = loginName;
        this.roleName = roleName;
        this.menuName = menuName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Override
    public String toString() {
        return "QueryUserRoleMenuVO{" +
                "loginName='" + loginName + '\'' +
                ", roleName='" + roleName + '\'' +
                ", menuName='" + menuName + '\'' +
                '}';
    }
}
