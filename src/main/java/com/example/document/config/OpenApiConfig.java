package com.example.document.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Document API Demo",
                version = "1.0",
                description = "A sample REST API project demonstrating Clean Architecture with Spring Boot and Swagger.",
                contact = @Contact(
                        name = "Gözde Temizhan",
                        email = "gozdetemizhan@example.com",
                        url = "https://github.com/gozdetemizhan"
                ),
                license = @License(
                        name = "MIT License",
                        url = "https://opensource.org/licenses/MIT"
                )
        )
)
public class OpenApiConfig {
}
