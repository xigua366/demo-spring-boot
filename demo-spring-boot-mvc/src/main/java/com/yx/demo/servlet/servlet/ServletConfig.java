package com.yx.demo.servlet.servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean<MyTwoServlet> regMyTwoServlet() {
        MyTwoServlet myTwoServlet = new MyTwoServlet();
        ServletRegistrationBean<MyTwoServlet> myTwoServletServletRegistrationBean = new ServletRegistrationBean<>();
        myTwoServletServletRegistrationBean.setServlet(myTwoServlet);

        /**
         *  经过断点测试，无论怎么配置，这个自定义的Servlet的优先级，都会比DispatcherServlet优先执行
         */
//        myTwoServletServletRegistrationBean.setOrder(Integer.MIN_VALUE);
//        myTwoServletServletRegistrationBean.setOrder(Integer.MAX_VALUE);
        myTwoServletServletRegistrationBean.addUrlMappings("/api/myTwoServlet");
        myTwoServletServletRegistrationBean.setName("myTwoServlet");
        return myTwoServletServletRegistrationBean;
    }

}
