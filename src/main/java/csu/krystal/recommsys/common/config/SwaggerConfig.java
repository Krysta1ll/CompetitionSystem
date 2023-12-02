package csu.krystal.recommsys.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springdocOpenApi() {
        return new OpenAPI().info(new Info()
                        .title("竞争性关键字推荐系统接口文档")
                        .license(new License()
                                .name("竞争性关键字推荐系统")))
                .externalDocs(new ExternalDocumentation())
                .components(new Components().addSecuritySchemes("bearer-key",
                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")));
    }
}
