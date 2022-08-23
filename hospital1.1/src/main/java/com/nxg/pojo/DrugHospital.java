package com.nxg.pojo;

public class DrugHospital {
    private Integer id;

    private Integer drugId;

    private Integer hospitalId;

    private Integer need;

    private Integer own;

    private com.nxg.pojo.Drug drug;

    public com.nxg.pojo.Drug getDrug() {
        return drug;
    }

    public void setDrug(com.nxg.pojo.Drug drug) {
        this.drug = drug;
    }

    public Integer getOwn() {
        return own;
    }

    public void setOwn(Integer own) {
        this.own = own;
    }

    public Integer getNeed() {
        return need;
    }

    public void setNeed(Integer need) {
        this.need = need;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDrugId() {
        return drugId;
    }

    public void setDrugId(Integer drugId) {
        this.drugId = drugId;
    }

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    @Override
    public String toString() {
        return "DrugHospital{" +
                "id=" + id +
                ", drugId=" + drugId +
                ", hospitalId=" + hospitalId +
                ", need=" + need +
                ", own=" + own +
                ", drug=" + drug +
                '}';
    }
}