package com.kkb.vo.doctormanager.req;

import lombok.Data;

/**
 * @author mj
 * @create 2021-03-18 18:16
 * @describe:
 */
@Data
public class SelectDoctorReq {
    /**
     * 当前页
     */
    private Integer pageNum = 1;

    /**
     * 一页信息数
     */
    private Integer pageSize = 5;

    /**
     * 用户名字
     */
    private String userName;

    /**
     * 科室
     */
    private String keShi;

    /**
     * 医生编号
     */
    private String doctorId;
}
