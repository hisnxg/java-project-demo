package com.nxg.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nxg
 * date 2022/1/19
 * @apiNote
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    /**
     * 订单编号
     */
    private Integer id;

    /**
     * 支付状态
     */
    private String message;
}
