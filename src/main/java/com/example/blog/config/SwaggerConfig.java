package com.example.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
@ComponentScan("com.example.blog")
public class SwaggerConfig {

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.any()).build()
                .apiInfo(generateApiInfo());
    }

    private ApiInfo generateApiInfo() {
        return new ApiInfo("Exemple d'api REST qui consiste à servir un Blog", "", "Version 0.0.1-SNAPSHOT", "urn:tos",
                new Contact("Ibrahim AMRANI", "https://www.linkedin.com/in/ibrahimamrani/", "ibrahimamrani@gmail.com"), "", "", Collections.emptyList());
    }
}
