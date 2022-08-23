package com.kkb.myenum;

/**
 * @author mj
 * @version 1.0
 * @date 2021/3/29 12:17
 */
public enum  ShowJiaoFeiEnum {

    //缴费显示状态
    SHOW(1,"展示"),
    NO_SHOW(0,"不展示");
    private int code;

    private String msg;

    ShowJiaoFeiEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
