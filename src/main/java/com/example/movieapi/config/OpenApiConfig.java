package com.example.movieapi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mahdi Sharifi
 * @since 10/4/22
 *
 * Change Swagger Header UI
 */
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openApi() {

        return new OpenAPI()
                .info(new Info()
                        .title("Movie API")
                        .description("Movie API V1 Definition ")
                        .version("v1")
                        .contact(new Contact()
                                .name("Mahdi Sharifi")
                                .url("https://www.linkedin.com/in/mahdisharifi/")
                                .email("mahdi.elu@gmail.com"))
                );
    }
}
