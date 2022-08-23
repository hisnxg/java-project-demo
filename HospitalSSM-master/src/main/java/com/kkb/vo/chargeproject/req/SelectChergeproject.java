package com.kkb.vo.chargeproject.req;

import lombok.Data;

@Data
public class SelectChergeproject {


    /**
     * 当前页
     */
    private Integer pageNum = 1;

    /**
     * 一页信息数
     */
    private Integer pageSize = 5;

    /**
     * 收费项目名称
     */
    private String chapName;

    private Integer chapId;



}
