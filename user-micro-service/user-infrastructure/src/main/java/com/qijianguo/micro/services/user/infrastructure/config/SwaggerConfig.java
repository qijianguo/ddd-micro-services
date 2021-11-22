package com.qijianguo.micro.services.user.infrastructure.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qijianguo
 */
@Configuration
//@ConditionalOnProperty(prefix = "swagger",value = {"basic.enable"},havingValue = "true")
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(path -> !"/error".equals(path))
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, apiResponseCode())
                .globalResponseMessage(RequestMethod.POST, apiResponseCode())
                .globalResponseMessage(RequestMethod.PUT, apiResponseCode())
                .globalResponseMessage(RequestMethod.DELETE, apiResponseCode())
                //.ignoredParameterTypes(CurrentUser.class)
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("user-micro-services[用户服务接口]")
                .description("用户服务接口")
                .contact(new Contact("Angus", "http://localhost:8081/doc.html", "1633972602@qq.com"))
                .version("1.0-SNAPSHOT")
                .build();
    }

    private List<ResponseMessage> apiResponseCode() {
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        /*responseMessageList.add(new ResponseMessageBuilder().code(EmBusinessError.PARAMETER_ERROR.getErrorCode())
                .message(EmBusinessError.PARAMETER_ERROR.getErrorMsg()).responseModel(
                new ModelRef(EmBusinessError.PARAMETER_ERROR.getErrorMsg())).build());*/
        return responseMessageList;
    }

}