package com.kkb.vo.hospzation.resp;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author mj
 * @create 2021-03-23 11:45
 * @describe:
 */
@Data
public class BeHospitalChargeResp {

    private Integer chapId;

    private String chapName;

    private BigDecimal chapMoney;

    private Date chapStime;

    private Integer behId;
}
