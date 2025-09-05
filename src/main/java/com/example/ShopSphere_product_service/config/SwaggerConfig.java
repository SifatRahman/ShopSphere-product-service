package com.example.ShopSphere_product_service.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ShopSphere Documentation")
                        .version("1.0")
                        .description("API documentation for ShopSphere")
                        .contact(new Contact()
                                .name("Sifat")
                                .email("sifat@example.com")
                                .url("https://sifat.com"))
                );
    }

    @Bean
    public GroupedOpenApi AllApi() {
        return GroupedOpenApi.builder()
                .group("All API")
                .pathsToMatch("/api/**")
                .build();
    }
}
