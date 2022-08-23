package com.kkb.vo.usermanager.req;

import lombok.Data;

/**
 * 根据ids删除多条用户
 */
@Data
public class DeleteMoreUserReq {
    private int[] userIds;
}
