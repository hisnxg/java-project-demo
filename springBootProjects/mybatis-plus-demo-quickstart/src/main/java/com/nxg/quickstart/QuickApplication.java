package com.nxg.quickstart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.nxg.quickstart.mapper")
public class QuickApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuickApplication.class, args);
    }
}
