package com.kkb.utils;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

/**
 * @Author 何世鹏
 * @Date 2021/3/24 11:40
 * md5加密计算测试
 */
public class Md5UtilsTest {

    Md5Utils md5Utils = new Md5Utils();

    @Test
    public void encoderByMd5() {
        try {
            System.out.println(md5Utils.EncoderByMd5("123456"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkPassword() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String s = md5Utils.EncoderByMd5("123");
        boolean b = md5Utils.checkPassword("123", s);
        System.out.println(b);
    }
}