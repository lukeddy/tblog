package com.tangzq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger Bean对象构建配置
 * @author tangzhiqiang
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tangzq.controller.api"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("TBlog API文档")
                .description("TBlog，一款开源的个人博客，基于SpringBoot,MongoDB")
//                .termsOfServiceUrl("http://www.example.com")
//                .contact(new Contact("tblog","http://www.example.com","example@gmail.com"))
//                .license("Apache2.0 开源协议")
//                .licenseUrl("http://www.github.com/tzq668766")
                .version("1.0")
                .build();
    }

}
