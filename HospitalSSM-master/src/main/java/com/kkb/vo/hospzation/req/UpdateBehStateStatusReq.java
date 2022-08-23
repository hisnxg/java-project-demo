package com.kkb.vo.hospzation.req;

import lombok.Data;

/**
 * @author mj
 * @create 2021-03-23 0:41
 * @describe:
 */
@Data
public class UpdateBehStateStatusReq {
    /**
     * 病人编号
     */
    private Integer[] hosrId;

    /**
     * 挂号状态
     */
    private Integer hosrState;
}
