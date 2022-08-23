package com.kkb.exception;

/**
 * @author mj
 * @version 1.0
 * @date 2021/3/29 9:44
 */
public class PrimaryException extends RuntimeException{
    public PrimaryException(){
        super("主键存在异常");
    }
}
