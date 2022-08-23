package com.nxg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

/**
 * @author nxg
 * date 2022/1/8
 * @apiNote
 */
@RestController
public class HelloController {
    @Autowired
    private DataSource dataSource;

    @GetMapping("/helloWorld")
    public String hello(){
        return "Hello SpringBoot!!!"+dataSource;
    }
}
