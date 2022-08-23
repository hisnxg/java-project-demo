package com.nxg;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.nxg.mapper") //注意需要导入 import tk.mybatis.spring.annotation.MapperScan;
public class CloudBillManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudBillManagerApplication.class, args);
    }

}
