package com.kaikeba.pojo;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Charge {
    private Integer id;

    private String chargeName;

    private Double chargeMoney;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date chargeDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    private Integer isDel;

    private Register register;

    private String chargeDateFormat;

    public String getChargeDateFormat() {
        return chargeDateFormat;
    }

    public void setChargeDateFormat(String chargeDateFormat) {
        this.chargeDateFormat = chargeDateFormat;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChargeName() {
        return chargeName;
    }

    public void setChargeName(String chargeName) {
        this.chargeName = chargeName == null ? null : chargeName.trim();
    }

    public Double getChargeMoney() {
        return chargeMoney;
    }

    public void setChargeMoney(Double chargeMoney) {
        this.chargeMoney = chargeMoney;
    }

    public Date getChargeDate() {
        return chargeDate;
    }

    public void setChargeDate(Date chargeDate) {
        this.chargeDate = chargeDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}