package com.kkb.vo.menumanager.req;

import lombok.Data;

@Data
public class SelectMenuReq {
    /**
     * 当前页
     */
    private Integer pageNum ;

    /**
     * 一页信息数
     */
    private Integer pageSize =5;

    /**
     * 用户名称
     */
    private String menuName;


}

