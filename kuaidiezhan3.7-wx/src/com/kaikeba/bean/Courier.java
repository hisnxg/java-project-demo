package com.kaikeba.bean;

import java.sql.Timestamp;
import java.util.Objects;

public class Courier {
    private int id;//id
//    private String number;//编号
    private String username;//姓名
    private String userPhone;//手机号码
    private String identifyCard;//身份证
    private String password;//密码
    private int dispatches;//派件数
    private Timestamp registrationTime;//注册时间
    private Timestamp loginTime;//上次登录时间

    public Courier(String username, String userPhone, String identifyCard, String password) {
        this.username = username;
        this.userPhone = userPhone;
        this.identifyCard = identifyCard;
        this.password = password;
    }

    public Courier(int id, String username, String userPhone, String identifyCard, String password) {
        this.id = id;
        this.username = username;
        this.userPhone = userPhone;
        this.identifyCard = identifyCard;
        this.password = password;
    }

    public Courier() {
    }

    public Courier(int id, String username, String userPhone, String identifyCard, String password, int dispatches, Timestamp registrationTime, Timestamp loginTime) {
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

    public int getDispatches() {
        return dispatches;
    }

    public void setDispatches(int dispatches) {
        this.dispatches = dispatches;
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
        return "Courier{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", identifyCard='" + identifyCard + '\'' +
                ", password='" + password + '\'' +
                ", dispatches=" + dispatches +
                ", registrationTime=" + registrationTime +
                ", loginTime=" + loginTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Courier courier = (Courier) o;
        return id == courier.id &&
                dispatches == courier.dispatches &&
                Objects.equals(username, courier.username) &&
                Objects.equals(userPhone, courier.userPhone) &&
                Objects.equals(identifyCard, courier.identifyCard) &&
                Objects.equals(password, courier.password) &&
                Objects.equals(registrationTime, courier.registrationTime) &&
                Objects.equals(loginTime, courier.loginTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, userPhone, identifyCard, password, dispatches, registrationTime, loginTime);
    }
}
