package cn.edu.whut.sept.zuul.config;

import cn.edu.whut.sept.zuul.interceptor.CheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author AnthonyCJ
 * @version 1.0
 * @description 配置跨域以及拦截器，Web相关配置
 * @date 2023/06/21 00:15
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private CheckInterceptor checkInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加鉴权拦截器，拦截所有请求，排除登录相关的API调用
        registry.addInterceptor(checkInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login", "/user/register", "/test/**",
                        "/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 项目中的所有接口都支持跨域访问
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedMethods("*")
                .maxAge(3600);
    }

}
