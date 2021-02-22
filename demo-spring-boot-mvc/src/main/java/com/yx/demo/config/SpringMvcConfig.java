package com.yx.demo.config;

import com.yx.demo.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 *
 * 不用权限可以访问url    /api/v1/pub/
 * 要登录可以访问url    /api/v1/pri/
 */

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {


    @Bean
    LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    /**
     * Interceptor 拦截器配置
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //拦截全部
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/api/v1/pri/*/*/**")
                //不拦截哪些路径   斜杠一定要加
                .excludePathPatterns("/api/v1/pri/user/login","/api/v1/pri/user/register", "/test/**", "/validator/test/**");

        // 好的编程习惯，不覆盖父类中的逻辑
        WebMvcConfigurer.super.addInterceptors(registry);

    }

    /**
     * 跨域访问配置
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // registry.addMapping("/user/**").allowedOrigins("http://www.xigua366.cn").allowedMethods("POST", "GET");

        // 好的编程习惯，不覆盖父类中的逻辑
        WebMvcConfigurer.super.addCorsMappings(registry);
    }

    /**
     * 添加自定义格式化组件
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        // registry.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));

        // 好的编程习惯，不覆盖父类中的逻辑
        WebMvcConfigurer.super.addFormatters(registry);
    }

    /**
     * URI到视图的映射
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // registry.addViewController("/addUserUI").setViewName("/addUser.jsp");

        // 好的编程习惯，不覆盖父类中的逻辑
        WebMvcConfigurer.super.addViewControllers(registry);
    }
}