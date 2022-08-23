package com.nxg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author nxg
 * date 2022/1/8
 * @apiNote
 */
@SpringBootApplication
@MapperScan("com.nxg.dao") //注意使用的是tk-mybatis包
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
