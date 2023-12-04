package csu.krystal.recommsys.common.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("竞争性关键字推荐系统")
                        .description("竞争性关键字推荐系统接口文档")
                        .version("v1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("外部文档")
                        .url("https://springshop.wiki.github.org/docs"));
    }

//    @Bean
//    public OpenAPI createRestApi(){
//        return new OpenAPI(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }
//    private ApiInfo apiInfo(){
//        return new ApiInfoBuilder()
//                .title("竞争性关键字推荐系统接口文档")
//                .contact(new Contact("竞争性关键字推荐系统","https://github.com/Krysta1ll/CompetitionSystem.git",":"))
//                .version("1.0")
//                .description("竞争性关键字推荐系统")
//                .build();
//    }
}
