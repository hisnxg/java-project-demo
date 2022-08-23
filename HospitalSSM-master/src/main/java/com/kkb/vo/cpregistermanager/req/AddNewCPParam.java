package com.kkb.vo.cpregistermanager.req;

import lombok.Data;

@Data
public class AddNewCPParam {

    private Integer hosrId;

    private Integer chapId;

    private String chapName;

    private Integer chapMoney;

    private String hosrName;
}
