package com.joaogodoi.SpringBoot3.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAp() {
        return new OpenAPI().info(new Info()
                .title("Restful API with Java 17 and Spring Boot 3")
                .version("v1.0")
                .description("API created using Java 17 and Spring Boot 3"));
    }
}
