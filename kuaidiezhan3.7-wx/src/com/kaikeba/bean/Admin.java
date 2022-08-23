package com.kaikeba.bean;

import java.sql.Timestamp;

public class Admin {
    private int id;
    private String username;
    private String password;
    private String ip;
    private Timestamp loginTime;

    public Admin() {
    }

    public Admin(int id, String username, String password, String ip, Timestamp loginTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.ip = ip;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }
}
