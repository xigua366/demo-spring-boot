package com.yx.demo.servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 自定义Filter有如下几种方式：
 * 1、原生的@WebFilter + @ServletComponentScan或者web.xml方式
 * 2、基于Spring的 FilterRegistrationBean 方式
 * 3、基于spring security过滤器链方式
 *
 * 这种增加filter的方式无法添加order排序
 */
@WebFilter(urlPatterns = "/*", filterName = "myFilter")
public class MyFilter implements Filter {

    @Override
    public void destroy() {
        System.out.println("myfilter destroy");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("自定义filter");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
