package com.lwdevelop.backend;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@EnableOpenApi
@Configuration
public class SwaggerConfig {
    @Value("${springfox.documentation.swagger-ui.enabled}")
    private boolean enabled;

    @Bean
    Docket createRestApi() {
        return new Docket(
                DocumentationType.OAS_30)
                .enable(enabled)
                .apiInfo(apiInfo())
                .select()
                // 对所有api进行监控
                .apis(RequestHandlerSelectors.any())
                // 对所有路径进行监控
                .paths(PathSelectors.any())
                .paths(PathSelectors.regex("/error.*").negate())
                .paths(PathSelectors.regex("/actuator.*").negate())
                .build()
                .securityContexts(Arrays.asList(securityContext()));
    }
    private SecurityContext securityContext(){
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth(){
        AuthorizationScope authorizationScope = new AuthorizationScope("global","accessEverything");
        AuthorizationScope[] authorizationScopes=new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(new SecurityReference("token", authorizationScopes));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 文档标题
                .title("Api Documentation for Test")
                // 文档描述
                .description("Api Documentation by Leo")
                // 文档版本
                .version("0.0.1")
                // 设置许可声明信息
                .license("Apache LICENSE 2.0")
                // 设置许可证URL地址
                .licenseUrl("https://github/my-dlq")
                // 设置管理该API人员的联系信息
                .contact(new Contact("超级小豆丁", "http://www.mydlq.club", "mynamedlq@163.com"))
                .build();
    }
    
}
