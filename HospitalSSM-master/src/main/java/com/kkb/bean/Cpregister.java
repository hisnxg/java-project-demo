package com.kkb.bean;


import java.util.Date;

public class Cpregister {
    private Integer cprId;

    private Integer hosrId;

    private Integer chapId;

    private String chapName;

    private Integer chapMoney;

    private Date cprTime;

    private String hosrName;

    public String getHosrName() {
        return hosrName;
    }

    public void setHosrName(String hosrName) {
        this.hosrName = hosrName;
    }

    public Integer getCprId() {
        return cprId;
    }

    public void setCprId(Integer cprId) {
        this.cprId = cprId;
    }

    public Integer getHosrId() {
        return hosrId;
    }

    public void setHosrId(Integer hosrId) {
        this.hosrId = hosrId;
    }

    public Integer getChapId() {
        return chapId;
    }

    public void setChapId(Integer chapId) {
        this.chapId = chapId;
    }

    public String getChapName() {
        return chapName;
    }

    public void setChapName(String chapName) {
        this.chapName = chapName;
    }

    public Integer getChapMoney() {
        return chapMoney;
    }

    public void setChapMoney(Integer chapMoney) {
        this.chapMoney = chapMoney;
    }

    public Date getCprTime() {
        return cprTime;
    }

    public void setCprTime(Date cprTime) {
        this.cprTime = cprTime;
    }
}