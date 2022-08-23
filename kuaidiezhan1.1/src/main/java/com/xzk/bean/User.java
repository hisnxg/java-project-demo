package com.xzk.bean;

import java.sql.Timestamp;
import java.util.Objects;

public class User {
    private int id;//id
    private String username;//用户昵称
    private String userPhone;//手机号码
    private String identifyCard;//身份证
    private String password;//密码
    private Timestamp registrationTime;//注册时间
    private Timestamp loginTime;//上次登录时间
    private  boolean user; //是否为用户
    private int expressNumber;//用户快递数



    public User(String username, String userPhone, String identifyCard, String password) {
        this.username = username;
        this.userPhone = userPhone;
        this.identifyCard = identifyCard;
        this.password = password;
    }

    public User(String userPhone) {
        this.userPhone = userPhone;
    }

    public User() {
    }

    public User(int id, String username, String userPhone, String identifyCard, String password, Timestamp registrationTime, Timestamp loginTime) {
        this.id = id;
        this.username = username;
        this.userPhone = userPhone;
        this.identifyCard = identifyCard;
        this.password = password;
        this.registrationTime = registrationTime;
        this.loginTime = loginTime;
    }

    public int getExpressNumber() {
        return expressNumber;
    }

    public void setExpressNumber(int expressNumber) {
        this.expressNumber = expressNumber;
    }
    public boolean isUser() {
        return user;
    }

    public void setUser(boolean user) {
        this.user = user;
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

    public Timestamp getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Timestamp registrationTime) {
        this.registrationTime = registrationTime;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", identifyCard='" + identifyCard + '\'' +
                ", password='" + password + '\'' +
                ", registrationTime=" + registrationTime +
                ", loginTime=" + loginTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(username, user.username) &&
                Objects.equals(userPhone, user.userPhone) &&
                Objects.equals(identifyCard, user.identifyCard) &&
                Objects.equals(password, user.password) &&
                Objects.equals(registrationTime, user.registrationTime) &&
                Objects.equals(loginTime, user.loginTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, userPhone, identifyCard, password, registrationTime, loginTime);
    }
}
