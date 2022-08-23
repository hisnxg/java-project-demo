package com.kaikeba.bean;

import java.sql.Timestamp;

public class BootStrapTableCourier {
    private int id;//id
    private String username;//姓名
    private String userPhone;//手机号码
    private String identifyCard;//身份证
    private String password;//密码
    private String dispatches;//派件数
    private String registrationTime;//注册时间
    private String loginTime;//上次登录时间

    public BootStrapTableCourier() {
    }

    public BootStrapTableCourier(int id, String username, String userPhone, String identifyCard, String password, String dispatches, String registrationTime, String loginTime) {
        this.id = id;
        this.username = username;
        this.userPhone = userPhone;
        this.identifyCard = identifyCard;
        this.password = password;
        this.dispatches = dispatches;
        this.registrationTime = registrationTime;
        this.loginTime = loginTime;
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

    public String getDispatches() {
        return dispatches;
    }

    public void setDispatches(String dispatches) {
        this.dispatches = dispatches;
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
