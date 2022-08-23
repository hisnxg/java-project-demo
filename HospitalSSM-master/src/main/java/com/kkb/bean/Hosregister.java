package com.kkb.bean;

import java.math.BigDecimal;
import java.sql.Date;


public class Hosregister {

    private Integer hosrId;

    private String hosrIdcar;

    private String hosrMedical;

    private BigDecimal hosrRegprice;

    private String hosrPhone;

    private Integer hosrSelfprice;

    private Integer hosrSex;

    private Integer hosrAge;

    private String hosrWork;

    private Integer hosrLookdoctor;

    private Integer dId;

    private String hosrRemark;

    private Integer hosrState;

    private String hosrName;

    private Date hosrTime;

    private Doctor doctor;

    public Hosregister() {
    }

    public Hosregister(Integer hosrId, String hosrIdcar, String hosrMedical, BigDecimal hosrRegprice, String hosrPhone, Integer hosrSelfprice, Integer hosrSex, Integer hosrAge, String hosrWork, Integer hosrLookdoctor, Integer dId, String hosrRemark, Integer hosrState, String hosrName, Date hosrTime, Doctor doctor) {
        this.hosrId = hosrId;
        this.hosrIdcar = hosrIdcar;
        this.hosrMedical = hosrMedical;
        this.hosrRegprice = hosrRegprice;
        this.hosrPhone = hosrPhone;
        this.hosrSelfprice = hosrSelfprice;
        this.hosrSex = hosrSex;
        this.hosrAge = hosrAge;
        this.hosrWork = hosrWork;
        this.hosrLookdoctor = hosrLookdoctor;
        this.dId = dId;
        this.hosrRemark = hosrRemark;
        this.hosrState = hosrState;
        this.hosrName = hosrName;
        this.hosrTime = hosrTime;
        this.doctor = doctor;
    }



    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Integer getHosrId() {
        return hosrId;
    }

    public void setHosrId(Integer hosrId) {
        this.hosrId = hosrId;
    }

    public String getHosrIdcar() {
        return hosrIdcar;
    }

    public void setHosrIdcar(String hosrIdcar) {
        this.hosrIdcar = hosrIdcar;
    }

    public String getHosrMedical() {
        return hosrMedical;
    }

    public void setHosrMedical(String hosrMedical) {
        this.hosrMedical = hosrMedical;
    }

    public BigDecimal getHosrRegprice() {
        return hosrRegprice;
    }

    public void setHosrRegprice(BigDecimal hosrRegprice) {
        this.hosrRegprice = hosrRegprice;
    }

    public String getHosrPhone() {
        return hosrPhone;
    }

    public void setHosrPhone(String hosrPhone) {
        this.hosrPhone = hosrPhone;
    }

    public Integer getHosrSelfprice() {
        return hosrSelfprice;
    }

    public void setHosrSelfprice(Integer hosrSelfprice) {
        this.hosrSelfprice = hosrSelfprice;
    }

    public Integer getHosrSex() {
        return hosrSex;
    }

    public void setHosrSex(Integer hosrSex) {
        this.hosrSex = hosrSex;
    }

    public Integer getHosrAge() {
        return hosrAge;
    }

    public void setHosrAge(Integer hosrAge) {
        this.hosrAge = hosrAge;
    }

    public String getHosrWork() {
        return hosrWork;
    }

    public void setHosrWork(String hosrWork) {
        this.hosrWork = hosrWork;
    }

    public Integer getHosrLookdoctor() {
        return hosrLookdoctor;
    }

    public void setHosrLookdoctor(Integer hosrLookdoctor) {
        this.hosrLookdoctor = hosrLookdoctor;
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    public String getHosrRemark() {
        return hosrRemark;
    }

    public void setHosrRemark(String hosrRemark) {
        this.hosrRemark = hosrRemark;
    }

    public Integer getHosrState() {
        return hosrState;
    }

    public void setHosrState(Integer hosrState) {
        this.hosrState = hosrState;
    }

    public String getHosrName() {
        return hosrName;
    }

    public void setHosrName(String hosrName) {
        this.hosrName = hosrName;
    }

    public Date getHosrTime() {
        return hosrTime;
    }

    public void setHosrTime(Date hosrTime) {
        this.hosrTime = hosrTime;
    }
}