package com.viseo.starter.configuration;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.*;
import static com.google.common.base.Predicates.*;

import java.time.LocalDate;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket backendApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select() // select exposed endpoints
                //.apis(RequestHandlerSelectors.basePackage("com.viseo.starter.web.v1"))
                //.apis(RequestHandlerSelectors.basePackage("org.springframework.security.oauth2"))
                .apis(RequestHandlerSelectors.any())
                .paths(paths())
                .build()
                .pathMapping("/")
                .useDefaultResponseMessages(false) // disable 401, 403, ...
                .globalResponseMessage(RequestMethod.GET, globalResponseMessages())
                .globalResponseMessage(RequestMethod.PUT, globalResponseMessages())
                .globalResponseMessage(RequestMethod.POST, globalResponseMessages())
                .globalResponseMessage(RequestMethod.DELETE, globalResponseMessages())
                .directModelSubstitute(LocalDate.class, String.class) // replace LocalDate with String
                .genericModelSubstitutes(ResponseEntity.class) // replace ResponseEntity<T> with T
                ;
    }

    private Predicate<String> paths() {
        return or(regex("/oauth/token.*"),
                regex("/api.*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Backend - Starter")
                .description("Backend")
                .termsOfServiceUrl("")
                .license("MIT")
                .licenseUrl("")
                .version("1.0")
                .build();
    }

    private List<ResponseMessage> globalResponseMessages() {
        return Lists.newArrayList(
                new ResponseMessageBuilder()
                        .code(400)
                        .message("The server cannot or will not process the request due to an apparent client error ")
                        .build(),
                new ResponseMessageBuilder()
                        .code(500)
                        .message("Internal server error")
                        .build());
    }

}
