package com.kkb.vo.hospitalsettle.resp;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author mj
 * @create 2021-03-23 10:26
 * @describe:
 */
@Data
public class HospitalSettleResp {
    private Integer behId;

    private BigDecimal behAntecedent;

    private Integer behCloseprice;

    private Integer behState;

    private String hosrName;

    private Double balance;
}
