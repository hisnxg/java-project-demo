package com.kkb.vo.hospzation.req;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author mj
 * @create 2021-03-23 1:11
 * @describe: 缴纳押金实体类
 */
@Data
public class UpdateBeHospitalPriceReq {
    /**
     * 病人编号
     */
    private Integer hosrId;

    /**
     * 挂号状态
     */
    private BigDecimal price;
}
