package com.kkb.vo.menumanager.req;

import lombok.Data;

@Data
public class AddMenuReq {
    private String menuName;
    private String menuUrl;
    private Integer menuState;

}
