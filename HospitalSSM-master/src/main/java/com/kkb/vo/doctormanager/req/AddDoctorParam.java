package com.kkb.vo.doctormanager.req;

import lombok.Data;

import java.util.Date;

/**
 * @author mj
 * @create 2021-03-19 17:58
 * @describe:
 */
@Data
public class AddDoctorParam {
    private Integer dId;

    private String dIdcar;

    private String dPhone;

    private String dTelphone;

    private Integer dSex;

    private String dBirthday;

    private Integer dAge;

    private String dEmail;

    private String dKeshi;

    private String dXueli;

    private String dDesc;

    private Date dIntime;

    private Integer dState;

    private String dName;
}
