package com.nxg.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class Hospital {
    private Integer id;

    private Integer bedNumber;

    private Double deposit;

    private String introduction;

    private Integer endStatus;

    private Integer infoStatus;

    private Integer registerId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date hospitalDate;

    private Double totalCost;

    private Double restCost;
    /**
     * 登记的病人
     */
    private com.nxg.pojo.Register register;

    /**
     * 一个住院的病人有多个药
     */
    List<Drug> drugs;

    public List<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<Drug> drugs) {
        this.drugs = drugs;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "id=" + id +
                ", bedNumber=" + bedNumber +
                ", deposit=" + deposit +
                ", introduction='" + introduction + '\'' +
                ", endStatus=" + endStatus +
                ", infoStatus=" + infoStatus +
                ", registerId=" + registerId +
                ", hospitalDate=" + hospitalDate +
                ", totalCost=" + totalCost +
                ", restCost=" + restCost +
                ", register=" + register +
                ", drugs=" + drugs +
                '}';
    }

    public com.nxg.pojo.Register getRegister() {
        return register;
    }

    public void setRegister(com.nxg.pojo.Register register) {
        this.register = register;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(Integer bedNumber) {
        this.bedNumber = bedNumber;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public Integer getEndStatus() {
        return endStatus;
    }

    public void setEndStatus(Integer endStatus) {
        this.endStatus = endStatus;
    }

    public Integer getInfoStatus() {
        return infoStatus;
    }

    public void setInfoStatus(Integer infoStatus) {
        this.infoStatus = infoStatus;
    }

    public Integer getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Integer registerId) {
        this.registerId = registerId;
    }

    public Date getHospitalDate() {
        return hospitalDate;
    }

    public void setHospitalDate(Date hospitalDate) {
        this.hospitalDate = hospitalDate;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public Double getRestCost() {
        return restCost;
    }

    public void setRestCost(Double restCost) {
        this.restCost = restCost;
    }
}