package com.nxg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 启动器
 */
@SpringBootApplication
@MapperScan("com.nxg.mapper") //注意需要导入 import tk.mybatis.spring.annotation.MapperScan;
public class BillApplication {
    public static void main(String[] args) {
        SpringApplication.run(BillApplication.class, args);
    }
}
