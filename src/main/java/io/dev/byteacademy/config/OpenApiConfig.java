package io.dev.byteacademy.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
    
    public OpenAPI openAPI(){
        return new OpenAPI()
            .info(new Info()
                .title("ByteAcademy")
                .version("1.0.0")
                .description("API REST")
                .contact(new Contact())
        );
    }

}
