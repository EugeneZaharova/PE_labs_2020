package ru.eugene.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringfoxConfig {

    @Bean
    Docket docket() {
        return create("ru.eugene.backend");
    }

    private static Docket create(String basePackage) {
        return new Docket(DocumentationType.SWAGGER_2)
                .tags(
                        new Tag("Vouchers", "Vouchers"),
                        new Tag("Files", "Files")
                )
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .build()
                .apiInfo(apiInfo());
    }

    private static ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Vouchers")
                .version("1")
                .build();
    }
}

