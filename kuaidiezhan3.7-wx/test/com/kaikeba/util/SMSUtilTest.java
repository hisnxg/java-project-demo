package com.kaikeba.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class SMSUtilTest {

    @Test
    public void send() {
        boolean flag = SMSUtil.send("16607259654", "123123");
        System.out.println(flag);
    }

    @Test
    public void loginSMS() {
        boolean flag = SMSUtil.loginSMS("16607259654", "123123");
        System.out.println(flag);
    }
}