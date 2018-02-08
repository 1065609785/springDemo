package com.zsy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置信息
 * 
 * @author 赵守云
 *
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				// 为当前包路径
				.apis(RequestHandlerSelectors.basePackage("com.zsy.controller")).paths(PathSelectors.any()).build();
	}

	// 构建 api文档的详细信息函数
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				// 页面标题
				.title("Spring Boot 使用 Swagger2 构建 RESTful API")
				// 创建人
				.contact(new Contact("zhaoshouyun", "http://www.apache.org/", "1065609785@qq.com"))
				// 版本号
				.version("1.0")
				// 描述
				.description("API 描述").build();
	}
}
