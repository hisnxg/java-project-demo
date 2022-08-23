package com.kkb.vo.registration.req;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateRegistrationParam {

    private Integer hosrId;

    private String hosrName;

    private String hosrIdcar;

    private String hosrMedical;

    private BigDecimal hosrRegprice;

    private String hosrPhone;

    private Integer hosrSelfprice;

    private Integer hosrSex;

    private Integer hosrAge;

    private String hosrWork;

    private Integer hosrLookdoctor;

    private String hosrRemark;




}
