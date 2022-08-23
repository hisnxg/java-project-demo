package com.nxg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CloudEurekaServiceCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudEurekaServiceCenterApplication.class, args);
    }

}
