package com.kkb.myenum;

/**
 * @author mj
 * @version 1.0
 * @date 2021/3/29 13:38
 */
public enum  HospitalSettleEnum {

    //挂号状态枚举
    SETTLE(1,"已结算"),
    NO_SETTLE(0,"未结算");

    private int code;

    private String msg;

    HospitalSettleEnum(int code, String msg) {
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
