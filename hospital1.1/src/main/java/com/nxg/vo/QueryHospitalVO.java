package com.nxg.vo;

/**
 *  多条件分页查询住院信息的包装，检查收费项目登记页面使用
 */
public class QueryHospitalVO {

    private Integer id;

    private String registerName;

    @Override
    public String toString() {
        return "QueryHospitalVO{" +
                "id=" + id +
                ", registerName='" + registerName + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName;
    }

}
