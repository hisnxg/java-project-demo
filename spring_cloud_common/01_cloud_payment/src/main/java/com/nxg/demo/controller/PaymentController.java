package com.nxg.demo.controller;

import com.nxg.demo.entity.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author nxg
 * date 2022/1/19
 * @apiNote
 */
@RestController
@RequestMapping("/payment")
@RefreshScope
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    //使用Payment 实体类 需要引入公共工程

    @GetMapping("/{id}")
    public ResponseEntity<Payment> payment(@PathVariable("id") Integer id){
        //Payment payment = new Payment(id,"支付成功!");
        Payment payment = new Payment(id,"支付成功，服务端口="+serverPort);
        return ResponseEntity.ok(payment);//ResponseEntity 工具类
    }


}
