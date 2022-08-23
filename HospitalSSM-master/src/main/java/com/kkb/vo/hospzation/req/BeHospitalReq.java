package com.kkb.vo.hospzation.req;

import lombok.Data;

/**
 * @author mj
 * @create 2021-03-22 19:23
 * @describe:
 */
@Data
public class BeHospitalReq {
    /**
     * 挂号id
     */
    private Integer behId;

    /**
     * 医生名字
     */
    private String doctorName;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 科室
     */
    private String keShi;

    /**
     * 当前页
     */
    private Integer pageNum = 1;

    /**
     * 一页信息数
     */
    private Integer pageSize = 5;
}
