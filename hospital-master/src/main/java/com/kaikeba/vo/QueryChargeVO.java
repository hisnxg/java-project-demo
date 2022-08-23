package com.kaikeba.vo;

/**
 *  多条件分页查询收费项目信息的包装，收费项目管理页面使用
 */
public class QueryChargeVO {
    private String chargeName;

    public String getChargeName() {
        return chargeName;
    }

    public void setChargeName(String chargeName) {
        this.chargeName = chargeName;
    }
}
