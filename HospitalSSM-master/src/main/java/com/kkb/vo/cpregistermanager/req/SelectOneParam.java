package com.kkb.vo.cpregistermanager.req;

import lombok.Data;

@Data
public class SelectOneParam {
    /**
     * 当前页
     */
    private Integer pageNum = 1;

    /**
     * 一页信息数
     */
    private Integer pageSize = 5;

    private Integer hosrId;
}
