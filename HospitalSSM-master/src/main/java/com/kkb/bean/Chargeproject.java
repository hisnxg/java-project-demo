package com.kkb.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Chargeproject {
    private Integer chapId;

    private String chapName;

    private BigDecimal chapMoney;

    private Date chapStime;


    public Date getChapStime() {
        return chapStime;
    }


    public void setChapStime(Date chapStime) {
        this.chapStime = chapStime;
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
        this.chapName = chapName == null ? null : chapName.trim();
    }

    public BigDecimal getChapMoney() {
        return chapMoney;
    }

    public void setChapMoney(BigDecimal chapMoney) {
        this.chapMoney = chapMoney;
    }
}