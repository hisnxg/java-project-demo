package com.kkb.myenum;

/**
 * @author mj
 * @create 2021-03-23 16:59
 * @describe:
 */
public enum HosrStateEnum {
    //挂号状态枚举
    REGISTER(0,"挂号"),
    HOSPITALIZED(1,"已住院"),
    DISCHARGED(2,"已出院"),
    NO_REGISTER(3,"已退号"),
    TUI_YUAN(4,"已退院"),
    ;

    private int code;

    private String msg;

    HosrStateEnum(int code, String msg) {
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
        if (num.equals(REGISTER.getCode())){
            return REGISTER.getMsg();
        }else if (num.equals(HOSPITALIZED.getCode())){
            return HOSPITALIZED.getMsg();
        }else if (num.equals(DISCHARGED.getCode())){
            return DISCHARGED.getMsg();
        }else if (num.equals(NO_REGISTER.getCode())){
            return NO_REGISTER.getMsg();
        }
        return "";
    }
}
