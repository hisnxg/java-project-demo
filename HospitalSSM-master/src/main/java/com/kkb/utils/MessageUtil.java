package com.kkb.utils;

import com.alibaba.fastjson.JSON;
import com.kkb.message.Message;

/**
 * @Author 何世鹏
 * @Date 2021/3/19 23:06
 * 转成JSON字符串
 */
public final class MessageUtil {

    public static String toJson(Message message) {
        String m = JSON.toJSONString(message);
        return m;
    }
}
