package com.kkb.vo.menumanager.req;

import lombok.Data;

@Data
public class UpdateMenuReq {
    private Integer menuId;
    private String menuName;
    private String menuUrl;
    private Integer menuState;

}
