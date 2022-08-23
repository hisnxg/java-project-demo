package com.xzk.bean;

public class BootStrapTableUser {
    private int id;//id
    private String username;//用户昵称
    private String userPhone;//手机号码
    private String identifyCard;//身份证
    private String password;//密码
    private String registrationTime;//注册时间
    private String loginTime;//上次登录时间
    private  boolean user; //是否为用户
    private int expressNumber;//用户快递数

    public BootStrapTableUser() {
    }

    public BootStrapTableUser(int id, String username, String userPhone, String identifyCard, String password, String registrationTime, String loginTime) {
        this.id = id;
        this.username = username;
        this.userPhone = userPhone;
        this.identifyCard = identifyCard;
        this.password = password;
        this.registrationTime = registrationTime;
        this.loginTime = loginTime;
    }

    public boolean isUser() {
        return user;
    }

    public void setUser(boolean user) {
        this.user = user;
    }

    public int getExpressNumber() {
        return expressNumber;
    }

    public void setExpressNumber(int expressNumber) {
        this.expressNumber = expressNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getIdentifyCard() {
        return identifyCard;
    }

    public void setIdentifyCard(String identifyCard) {
        this.identifyCard = identifyCard;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }
}
