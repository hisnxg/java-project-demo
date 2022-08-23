package com.nxg.travel.config;

import com.github.pagehelper.PageInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author nxg
 * date 2022/1/16
 * @apiNote 整合mybatis plus
 * 分页拦截器
 */
@Configuration
@MapperScan("com.nxg.travel.dao")
public class MybatisPlusConfig {

    @Bean
    public PageInterceptor pageInterceptor(){
        return new PageInterceptor();
    }
}
