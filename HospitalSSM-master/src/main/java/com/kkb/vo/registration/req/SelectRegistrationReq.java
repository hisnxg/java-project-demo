package com.kkb.vo.registration.req;

import lombok.Data;

import java.util.Date;


@Data
public class SelectRegistrationReq {

    /**
     * 当前页
     */
    private Integer pageNum ;

    /**
     * 一页信息数
     */
    private Integer pageSize = 5;

    /**
     * 病历号
     */
    private Integer hosrId;

    /**
     * 科室
     */
    private String dKeshi;

    /**
     * 医生名字
     */
    private String dName;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

}
