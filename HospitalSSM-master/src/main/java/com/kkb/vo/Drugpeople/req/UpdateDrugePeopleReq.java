package com.kkb.vo.Drugpeople.req;

import lombok.Data;

/**
 * @author sufenglong
 * @date 2021/3/25 15:18
 * @describe:
 */
@Data
public class UpdateDrugePeopleReq {

    /**
     * 病历号
     */
    private Integer peopleId;
    /**
     *药品数量
     */
    private Integer drNumber;

    /**
     * 已发数量
     */
    private Integer drNumberIssued;


}
