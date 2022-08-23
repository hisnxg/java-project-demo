package com.nxg.upload.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author nxg
 * date 2022/3/9
 * @apiNote
 * Swagger配置文件
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.nxg.upload")) //swagger搜索的包
                .paths(PathSelectors.any()) //swagger路径匹配
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("微服务文件上传文档")
                .description("使用FastDfs文件上传")
                .version("version 1.0")
                .build();
    }

}
