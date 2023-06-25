package cn.edu.whut.sept.zuul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author AnthonyCJ
 * @version 1.0
 * @description 配置Swagger API，自动生成文档
 * @date 2023/06/21 00:01
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(Environment environment) {
        // 只在dev环境下使用swagger
        Profiles profiles = Profiles.of("dev");
        boolean flag = environment.acceptsProfiles(profiles);

        ApiSelectorBuilder builder = new Docket(DocumentationType.SWAGGER_2)        // 定义 Swagger 2.0 规范
                .apiInfo(apiInfo())                                                 // 定义全局描述信息
                .enable(true)                                                       // 是否开启
                .tags(new Tag("用户操作", "关于用户的注册、登录"),       // 定义 tag，对应 CODING API 文档的菜单 API 分组
                        new Tag("游戏操作", "关于用户游戏动作的执行"))
                // 定义 API 基础域名
                .select();
        // 指定只扫描 @RestController 注解的 Controller 的 API
        builder.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class));
        // 排除扫描 @RequestAttribute 注解
        return builder.build()
                .ignoredParameterTypes(RequestAttribute.class);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("World Of Zuul API 文档")                   // 定义文档标题
                .description("这是关于Zuul游戏的API文档")    // 定义文档描述
                .version("1.0.0")                         // 定义 API 版本
                .build();
    }
}
