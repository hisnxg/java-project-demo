package com.kkb.vo.cpregistermanager.req;

import lombok.Data;

@Data
public class SelectCpregisterReq {
    /**
     * 当前页
     */
    private Integer pageNum;

    /**
     * 一页信息数
     */
    private Integer pageSize = 5;
    private Integer hosrId;
    private String hosrName;
}
