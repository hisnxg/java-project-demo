package com.kkb.message;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @Author 何世鹏
 * @Date 2021/3/19 22:57
 */
@Data
@Component
public class Message {
    /**
     * 状态码，0正常，-1异常
     */
    private Integer code;
    /**
     * 返回的消息
     */
    private String message;
}
