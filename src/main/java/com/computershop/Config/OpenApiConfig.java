package com.computershop.Config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Computer Shop API",
                version = "1.0",
                description = "API documentation for the Computer Shop management system"
        )
)
public class OpenApiConfig {
}
