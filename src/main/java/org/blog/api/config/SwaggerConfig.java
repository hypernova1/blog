package org.blog.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melchor
 * Date: 2020/08/30
 * Time: 10:02 PM
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private String version;
    private String title;

    @Bean
    public Docket aptV1() {
        version = "V1";
        title = "Blog API " + version;

        List<ResponseMessage> responseMessages = new ArrayList<>();
        responseMessages.add(new ResponseMessageBuilder().code(200).message("OK").build());
        responseMessages.add(new ResponseMessageBuilder().code(201).message("Created").build());
        responseMessages.add(new ResponseMessageBuilder().code(400).message("Bad Request").build());
        responseMessages.add(new ResponseMessageBuilder().code(401).message("Unauthorized").build());
        responseMessages.add(new ResponseMessageBuilder().code(404).message("Not Found").build());

        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .groupName(version)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.blog.api.controller"))
                .paths(PathSelectors.ant("/v1/api/**"))
                .build()
                .apiInfo(apiInfo(title, version))
                .globalResponseMessage(RequestMethod.GET, responseMessages);
    }

    @Bean
    public Docket apiV2() {
        version = "V2";
        title = "Blog API " + version;

        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .groupName(version)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.blog.api.controller"))
                .paths(PathSelectors.ant("/v2/api/**"))
                .build()
                .apiInfo(apiInfo(title, version));
    }

    private ApiInfo apiInfo(String title, String version) {
        return new ApiInfo(
                title,
                "Swagger Api Docs",
                version,
                "www.example.com",
                new Contact("Contact Me", "https://github.com/hypernova1", "hypemova@gmail.com"),
                "Licensed",
                "www.example.com",
                new ArrayList<>()
        );
    }
}
