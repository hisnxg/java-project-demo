package com.kkb.myenum;

/**
 * @author mj
 * @create 2021-03-23 18:51
 * @describe: 住院结算状态
 */
public enum HospitalClosePriceStatusEnum {
    //挂号状态枚举
    UNSETTLED(0,"未结算"),
    SETTLEMENT(1,"已结算"),
    ;

    private int code;

    private String msg;

    HospitalClosePriceStatusEnum(int code, String msg) {
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


    public static String intToStr(Integer num){
        if (num.equals(UNSETTLED.getCode())){
            return UNSETTLED.getMsg();
        }else if (num.equals(SETTLEMENT.getCode())){
            return SETTLEMENT.getMsg();
        }
        return "";
    }
}
