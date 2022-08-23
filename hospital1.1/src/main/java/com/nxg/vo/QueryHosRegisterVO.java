package com.nxg.vo;

public class QueryHosRegisterVO {
    private int hosR_id;//病历号
    private String hosR_name;//挂号姓名
    private String dept;//科室表

    public int getHosR_id() {
        return hosR_id;
    }

    public void setHosR_id(int hosR_id) {
        this.hosR_id = hosR_id;
    }

    public String getHosR_name() {
        return hosR_name;
    }

    public void setHosR_name(String hosR_name) {
        this.hosR_name = hosR_name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
}
