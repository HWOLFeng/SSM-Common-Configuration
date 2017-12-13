package cn.hwolf.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author hwolf
 * @email h.wolf@qq.com
 * @date 2017/12/3.
 */

@Configuration
@EnableSwagger2
//TODO
@ComponentScan(basePackages = {})
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo());
    }


    private ApiInfo apiInfo() {
        // TODO
        return new ApiInfoBuilder()
                .title("")
                .description("")
                .termsOfServiceUrl("")
                .contact("hwolf")
                .version("1.0.0").build();
    }
}
