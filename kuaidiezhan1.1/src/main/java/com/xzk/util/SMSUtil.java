package com.xzk.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Random;

public class SMSUtil {
    public static void main(String[] args) {
        //随机数产生,来生成短信验证码
        Random r = new Random();
        int num = r.nextInt(899999)+100000;//100000~999999
        send("16607259654",num+"");//注意需要拼接上字符串
    }
    public static boolean send(String phoneNumber,String code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "秘钥id", "秘钥值");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", "phoneNumber");
        request.putQueryParameter("SignName", "签名名称");
        request.putQueryParameter("TemplateCode", "短信模板code");
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");//结束字符串,和开始字符串
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            String json = response.getData();
            Gson g = new Gson();
            HashMap result = g.fromJson(json, HashMap.class);
            if("OK".equals(result.get("Message"))) {
                return true;
            }else{
                System.out.println("短信发送失败，原因："+result.get("Message"));
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean loginSMS(String phoneNumber,String code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "秘钥id", "秘钥值");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", "phoneNumber");
        request.putQueryParameter("SignName", "签名名称");
        request.putQueryParameter("TemplateCode", "短信模板code");
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");//结束字符串,和开始字符串
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            String json = response.getData();
            Gson g = new Gson();
            HashMap result = g.fromJson(json, HashMap.class);
            if("OK".equals(result.get("Message"))) {
                return true;
            }else{
                System.out.println("短信发送失败，原因："+result.get("Message"));
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
