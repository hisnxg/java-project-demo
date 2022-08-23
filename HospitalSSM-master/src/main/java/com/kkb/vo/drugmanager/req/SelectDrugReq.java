package com.kkb.vo.drugmanager.req;

import lombok.Data;

@Data
public class SelectDrugReq {
    /**
     * 当前页
     */
    private Integer pageNum;

    /**
     * 一页信息数
     */
    private Integer pageSize=5;

    /**
     * 药品名字
     */
    private String drugName;

    /**
     * 药品类别
     */
    private String drugType;
}
