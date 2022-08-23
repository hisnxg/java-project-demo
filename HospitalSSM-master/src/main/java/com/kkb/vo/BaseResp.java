package com.kkb.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mj
 * @create 2021-03-19 11:45
 * @describe:
 */
public class BaseResp<T> {

    @JsonProperty("data")
    private T data;

    @JsonProperty("response")
    private Response response = new Response();

    static class Response{
        @JsonProperty("code")
        String code;
        @JsonProperty("flag")
        Boolean flag;
        @JsonProperty("errorMsg")
        String errorMsg;

        public Response(){

        }

        public Response(String code, Boolean flag, String errorMsg) {
            this.code = code;
            this.flag = flag;
            this.errorMsg = errorMsg;
        }
    }

    public BaseResp() {
        this.data = null;
        this.response = new Response("200", true, "");
    }

    public BaseResp(T data) {
        this.data = data;
        this.response = new Response("200", true, "");
    }

    public BaseResp(T data,String code,Boolean flag,String errorMsg) {
        this.data = data;
        this.response = new Response(code, flag, errorMsg);
    }

    public BaseResp(String code,Boolean flag,String errorMsg) {
        this.data = null;
        this.response = new Response(code, flag, errorMsg);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public String getCode() {
        return response.code;
    }

    public void setCode(String code) {
        this.response.code = code;
    }

    public Boolean getFlag() {
        return response.flag;
    }

    public void setFlag(Boolean flag) {
        this.response.flag = flag;
    }

    public String getErrorMsg() {
        return response.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.response.errorMsg = errorMsg;
    }

}
