package com.hcl.cloud.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This class will configure Swagger documentation.
 * @author shikhar.a || ankit-kumar
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * ApiInfo.
     */
    @SuppressWarnings("deprecation")
    public static final ApiInfo DEFAULT_CUSTOM_INFO = new ApiInfo("Order "
            + "Microservice API Documents",
            "These APIs are deployed on cloud foundry and"
                    + " will process order related request",
            "1.0.0",
            null,
            "shikhar.a@hcl.com",
            "HCL",
            null);

    /**
     * ApiInfo.
     * @return tag for postsapi
     */
    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_CUSTOM_INFO);
    }

}
