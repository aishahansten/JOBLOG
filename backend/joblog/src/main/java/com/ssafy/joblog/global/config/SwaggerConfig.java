package com.ssafy.joblog.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 접속 : http://localhost:8080/swagger-ui/index.html
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openApi() {
        Info info = new Info()
                .title("job 서비스 API Document")
                .version("v0.0.1")
                .description("job API 명세서입니다.");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}