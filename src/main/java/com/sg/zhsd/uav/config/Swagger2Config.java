package com.sg.zhsd.uav.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 //开启swager2
public class Swagger2Config implements WebMvcConfigurer{
	
    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

    @Bean
    public Docket createWorkingSituationRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sg.zhsd.uav.controller"))
                .paths(PathSelectors.any())
                .build().groupName("用户管理服务");
    }
    
    /**
                  * 创建API基本信息（这些基本信息会展现在文档页面中）
    * @return
     */
    private ApiInfo apiInfo() {
        	return new ApiInfoBuilder()
                .title("用户管理服务微服务 RESTFul APIs")
                .description("更多内容请关注用户管理服务官方文档：http://10.176.20.246/docs")
                .termsOfServiceUrl("")
                .contact(new Contact("", "", ""))
                .version("1.0")
                .build();
    }
}
