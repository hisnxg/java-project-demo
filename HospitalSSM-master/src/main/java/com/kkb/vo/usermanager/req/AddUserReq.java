package com.kkb.vo.usermanager.req;


import com.kkb.utils.Md5Utils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class AddUserReq {
    Md5Utils md5Utils=new Md5Utils();
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户密码
     */
    private String passWord;

    /**
     *用户真实姓名
     */
    private String trueName;

    /**
     *用户邮箱
     */
    private String  email;

    /**
     *用户状态
     */
    private Integer ustate;
    /**
     * 角色名称
     */
    private Integer  roleId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String PW) throws UnsupportedEncodingException, NoSuchAlgorithmException {
       String passWord=md5Utils.EncoderByMd5(PW);
        this.passWord= passWord;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUstate() {
        return ustate;
    }

    public void setUstate(Integer ustate) {
        this.ustate = ustate;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
