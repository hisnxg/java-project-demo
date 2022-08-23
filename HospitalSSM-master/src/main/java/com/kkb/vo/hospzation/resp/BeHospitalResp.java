package com.kkb.vo.hospzation.resp;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author mj
 * @create 2021-03-23 0:01
 * @describe:
 */
@Data
public class BeHospitalResp {
    private Integer behId;

    private String behNursepeople;

    private String behPatbed;

    private BigDecimal behAntecedent;

    private String behIllness;

    private Integer behCloseprice;

    private Integer hosrState;

    private String behIntime;

    /**
     * 病人名字
     */
    private String hosrName;

    /**
     * 病人手机号
     */
    private String hosrPhone;

    /**
     * 医生id
     */
    private Integer doctorId;

    private String doctorName;

    private String keShi;

    /**
     * 判断是否需要缴费，1：需要，2：不需要
     */
    private Integer priceStatus;

}
