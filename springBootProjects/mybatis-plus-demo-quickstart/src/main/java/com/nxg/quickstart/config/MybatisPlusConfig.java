package com.nxg.quickstart.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.github.pagehelper.PageInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author nxg
 * date 2022/1/12
 * @apiNote
 */
@Configuration
@MapperScan("com.nxg.quickstart.mapper")
public class MybatisPlusConfig {
    /**
     * mp分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
// 开启 count 的 join 优化,只针对 left join !!!
        return new PaginationInterceptor().setCountSqlParser(new JsqlParserCountOptimize(true));
    }
    /**
     * 两个分页插件都配置,不会冲突
     * pagehelper的分页插件
     */
    @Bean
    public PageInterceptor pageInterceptor() {
        return new PageInterceptor();
    }

}
