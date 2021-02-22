package com.yx.demo.servlet.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<MyTwoFilter> regMyTwoFilter() {
        MyTwoFilter myTwoFilter = new MyTwoFilter();
        FilterRegistrationBean<MyTwoFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(myTwoFilter);//注册自定义过滤器
        filterFilterRegistrationBean.setName("myTwoFilter");//过滤器名称
        filterFilterRegistrationBean.addUrlPatterns("/*");//过滤所有路径
        filterFilterRegistrationBean.setOrder(1);//优先级，最顶级
        return filterFilterRegistrationBean;
    }


    /**
     * 跨域配置
     * @return
     */
    @Bean
    public FilterRegistrationBean<CorsFilter> regCorsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("*"));
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
//        corsConfiguration.setExposedHeaders(Arrays.asList("token"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        CorsFilter corsFilter = new CorsFilter(urlBasedCorsConfigurationSource);
        FilterRegistrationBean<CorsFilter> corsFilterFilterRegistrationBean = new FilterRegistrationBean<>();
        corsFilterFilterRegistrationBean.setFilter(corsFilter);
        return corsFilterFilterRegistrationBean;
    }

}
