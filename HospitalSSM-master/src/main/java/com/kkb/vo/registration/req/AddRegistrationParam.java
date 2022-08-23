package com.kkb.vo.registration.req;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class AddRegistrationParam {
    private Integer hosrId;

    private String hosrIdcar;

    private String hosrMedical;

    private BigDecimal hosrRegprice;

    private String hosrPhone;

    private Integer hosrSelfprice;

    private Integer hosrSex;

    private Integer hosrAge;

    private String hosrWork;

    private Integer hosrLookdoctor;

    private Integer dId;

    private String hosrRemark;

   // private Integer hosrState;

    private String hosrName;

    //private Date hosrTime;

    //private String dName;


}
