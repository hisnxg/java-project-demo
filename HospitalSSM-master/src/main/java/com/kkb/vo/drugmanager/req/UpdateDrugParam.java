package com.kkb.vo.drugmanager.req;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateDrugParam {
    private Integer drId;

    private String drUrl;

    private BigDecimal drInprice;

    private BigDecimal drOutprice;

    private String drName;

    private String drType;

    private String drSimdesc;

    private String drQgp;

    private String drDetadesc;

    private String drFatory;

    private String drDirection;

    private String drRemark;

    private Integer drNumber;
}
