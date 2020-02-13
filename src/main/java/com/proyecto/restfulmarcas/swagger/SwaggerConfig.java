package com.proyecto.restfulmarcas.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(
                        RequestHandlerSelectors
                                .basePackage("com.proyecto.restfulmarcas.controlador"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "Spring Boot Swagger Example API",
                "Spring Boot Swagger Example API for Restful Service",
                "1.0",
                "Terms of Service",
                new Contact("ReftfulMarcas", "https://github.com/AMD031/restfulmarca",
                        "moodle.iesfranciscodelosrios.es/").toString(),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html"
        );

        
        return apiInfo;
    }
}