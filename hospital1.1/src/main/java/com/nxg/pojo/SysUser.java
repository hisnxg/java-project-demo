package com.nxg.pojo;

public class SysUser {
    private Integer id;

    private String loginName;

    private String password;

    private String trueName;

    private String email;

    private Integer status;

    private Integer roleId;

    //对一关系字段
    private com.nxg.pojo.Role role;

    public SysUser(Integer id, String password) {
        this.id = id;
        this.password = password;
    }

    public com.nxg.pojo.Role getRole() {
        return role;
    }

    public void setRole(com.nxg.pojo.Role role) {
        this.role = role;
    }

    public SysUser() { }

    public SysUser(String loginName, String password, String trueName, String email, Integer status, Integer roleId) {
        this.loginName = loginName;
        this.password = password;
        this.trueName = trueName;
        this.email = email;
        this.status = status;
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                ", trueName='" + trueName + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", roleId=" + roleId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName == null ? null : trueName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}