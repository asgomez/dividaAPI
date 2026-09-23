package com.alexandregomez.dividaapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI dividaApiOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Dívida API")
                        .description("API REST para controle de dívidas")
                        .version("1.0.0"));
    }
}