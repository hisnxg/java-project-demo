package com.nxg.vo;

/**
 *  多条件查询药品封装的类
 */
public class QueryDrugVO {
    //药品名称
    private String drugName;
    //药品类型
    private String drugType;

    public QueryDrugVO() {
    }

    public QueryDrugVO(String drugName, String drugType) {
        this.drugName = drugName;
        this.drugType = drugType;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public void setDrugType(String drugType) {
        this.drugType = drugType;
    }

    public String getDrugName() {
        return drugName;
    }

    public String getDrugType() {
        return drugType;
    }

    @Override
    public String toString() {
        return "QueryDrugVO{" +
                "drugName='" + drugName + '\'' +
                ", drugType='" + drugType + '\'' +
                '}';
    }
}
