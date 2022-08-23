package com.kkb.vo.hospitalsettle.req;

import lombok.Data;

/**
 * @author mj
 * @create 2021-03-23 10:26
 * @describe:
 */
@Data
public class HospitalSettleReq {
    /**
     * 挂号id
     */
    private Integer id;

    /**
     * 医生名字
     */
    private String name;

    /**
     * 当前页
     */
    private Integer pageNum = 1;

    /**
     * 一页信息数
     */
    private Integer pageSize = 5;

    /**
     * 挂号id，根据挂号id，查询余额信息
     */
    private Integer behId;

}
