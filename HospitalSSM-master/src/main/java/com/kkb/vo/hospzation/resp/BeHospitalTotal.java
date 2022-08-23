package com.kkb.vo.hospzation.resp;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author mj
 * @create 2021-03-23 0:55
 * @describe: 住院信息综合
 */
@Data
public class BeHospitalTotal {
    private Integer behId;

    private String behNursepeople;

    private String behPatbed;

    private BigDecimal behAntecedent;

    private String behIllness;

    private Integer behCloseprice;

    private Integer behState;

    private String behIntime;

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

    private Integer hosrState;
    /**
     * 医生id
     */
    private Integer doctorId;

    private String doctorName;

    private String keShi;

    private String hosrName;

    /**
     * 该id是否已经住院,1:已住院，0：未住院,2：已退号
     */
    private Integer primaryStatus;
}
