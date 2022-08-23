package com.kkb.bean;

import java.math.BigDecimal;

public class Behospital {
    private Integer behId;

    private String behNursepeople;

    private String behPatbed;

    private BigDecimal behAntecedent;

    private String behIllness;

    private Integer behCloseprice;

    private Integer behState;

    private String behIntime;

    public String getBehIntime() {
        return behIntime;
    }

    public void setBehIntime(String behIntime) {
        this.behIntime = behIntime;
    }

    public Integer getBehId() {
        return behId;
    }

    public void setBehId(Integer behId) {
        this.behId = behId;
    }

    public String getBehNursepeople() {
        return behNursepeople;
    }

    public void setBehNursepeople(String behNursepeople) {
        this.behNursepeople = behNursepeople == null ? null : behNursepeople.trim();
    }

    public String getBehPatbed() {
        return behPatbed;
    }

    public void setBehPatbed(String behPatbed) {
        this.behPatbed = behPatbed == null ? null : behPatbed.trim();
    }

    public BigDecimal getBehAntecedent() {
        return behAntecedent;
    }

    public void setBehAntecedent(BigDecimal behAntecedent) {
        this.behAntecedent = behAntecedent;
    }

    public String getBehIllness() {
        return behIllness;
    }

    public void setBehIllness(String behIllness) {
        this.behIllness = behIllness == null ? null : behIllness.trim();
    }

    public Integer getBehCloseprice() {
        return behCloseprice;
    }

    public void setBehCloseprice(Integer behCloseprice) {
        this.behCloseprice = behCloseprice;
    }

    public Integer getBehState() {
        return behState;
    }

    public void setBehState(Integer behState) {
        this.behState = behState;
    }
}