package org.asendar.url.shortener.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author asendar
 *
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Autowired
	private Environment environment;

    @Bean
    public Docket apiDocket() {
		boolean prod = Arrays.stream(environment.getActiveProfiles()).anyMatch(env -> env.equalsIgnoreCase("prod"));

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.asendar.url.shortener"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .enable(!prod);
    }

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("URL Shortener").description("Shortens URLs").build();
	}
}
