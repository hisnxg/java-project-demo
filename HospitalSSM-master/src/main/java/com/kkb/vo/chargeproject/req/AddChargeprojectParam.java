package com.kkb.vo.chargeproject.req;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AddChargeprojectParam {


    private Integer chapId;

    private String chapName;

    private BigDecimal chapMoney;

    private Date chapStime;


}
