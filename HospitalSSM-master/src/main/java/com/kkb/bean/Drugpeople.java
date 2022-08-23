package com.kkb.bean;

import java.util.Date;

public class Drugpeople {

    /**
     * 中间表id
     */
    private Integer dpId;
    /**
     * 药品的编号
     */
    private Integer drId;

    /**
     * 病人的编号
     */
    private Integer peopleId;

    /**
     * 负责人（医生姓名）
     */
    private String dName;

    /**
     * 药品的数量
     */
    private Integer drNumber;

    /**
     * 已发药品的数量
     */
    private Integer drNumberIssued;

    /**
     * 未发药品的数量
     */
    private Integer drNumberWeifa;

    /**
     * 创建时间
     */
    private Date drCreateTime;

    /**
     * 关联药品表
     */
    private Drug drug;

    /**
     * 关联挂号表
     */
    private Hosregister hosregister;

    private Integer hosrId;

    private String beHNursePeoPle;

    private String hosrName;


    private String drName;

    public Integer getHosrId() {
        return hosrId;
    }

    public void setHosrId(Integer hosrId) {
        this.hosrId = hosrId;
    }

    public String getDrName() {
        return drName;
    }

    public void setDrName(String drName) {
        this.drName = drName;
    }

    public Integer getDpId() {
        return dpId;
    }

    public void setDpId(Integer dpId) {
        this.dpId = dpId;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public Hosregister getHosregister() {
        return hosregister;
    }

    public void setHosregister(Hosregister hosregister) {
        this.hosregister = hosregister;
    }

    public String getBeHNursePeoPle() {
        return beHNursePeoPle;
    }

    public void setBeHNursePeoPle(String beHNursePeoPle) {
        this.beHNursePeoPle = beHNursePeoPle;
    }

    public String getHosrName() {
        return hosrName;
    }

    public void setHosrName(String hosrName) {
        this.hosrName = hosrName;
    }

    public Integer getDrNumberWeifa() {
        return drNumberWeifa;
    }

    public void setDrNumberWeifa(Integer drNumberWeifa) {
        this.drNumberWeifa = drNumberWeifa;
    }

    public Date getDrCreateTime() {
        return drCreateTime;
    }

    public void setDrCreateTime(Date drCreateTime) {
        this.drCreateTime = drCreateTime;
    }

    public Integer getDrId() {
        return drId;
    }

    public void setDrId(Integer drId) {
        this.drId = drId;
    }

    public Integer getDrNumber() {
        return drNumber;
    }

    public void setDrNumber(Integer drNumber) {
        this.drNumber = drNumber;
    }

    public Integer getDrNumberIssued() {
        return drNumberIssued;
    }

    public void setDrNumberIssued(Integer drNumberIssued) {
        this.drNumberIssued = drNumberIssued;
    }

    public Integer getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(Integer peopleId) {
        this.peopleId = peopleId;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }
}