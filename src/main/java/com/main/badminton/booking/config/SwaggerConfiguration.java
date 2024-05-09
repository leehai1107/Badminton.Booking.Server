package com.main.badminton.booking.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfiguration {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("Badminton-Booking-api")
                .packagesToScan("com.main.badminton.booking.controller")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public OpenAPI birthdayBuddyOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Badminton Booking API")
                        .description("Badminton booking application")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Badminton Booking Documentation")
                        .url("https://troll.vn"));
    }
}