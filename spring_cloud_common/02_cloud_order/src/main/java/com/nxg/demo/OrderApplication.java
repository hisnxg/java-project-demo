package com.nxg.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients  //可以指定需要扫描的包，默认是注解所在的包
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    /**
     * Spring提供了⼀个 RestTemplate模板⼯具类
     * 目前支持三种:
     * HttpClient
     * OkHttp
     * JDK原⽣的URLConnection（默认的）
     * @return
     */
    @Bean
    @LoadBalanced  //负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
