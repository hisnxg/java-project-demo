package com.kkb.vo.Drugpeople.req;

import lombok.Data;

/**
 * @author sufenglong
 * @date 2021/3/25 18:47
 * @describe:
 */
@Data
public class SelectDrugpeopleReq {


    private Integer peopleId;
    private Integer drNumber;
    private Integer drNumberIssued;
    private Integer drNumberWeifa;
    private Integer PageNum = 1;
    private Integer PageSize = 5;


}
