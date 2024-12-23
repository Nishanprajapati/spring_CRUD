package net.java.springboot_backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        System.out.println("SwaggerConfig initialized");
       return new OpenAPI()
               .info(new Info()
               .title("Spring Boot Backend")
                       .description("This is API documentation"));
    }
}
