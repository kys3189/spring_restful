package com.app.restful.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class SwaggerConfig {
    
//    클래스 = 컴포넌트 / 메서드 = 빈
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SpringBoot Rest API Documentation")
                        .description("Member Info Test API")
                        .version("0.1")
                );
    }
}
