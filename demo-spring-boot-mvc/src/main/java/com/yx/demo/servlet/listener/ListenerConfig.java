package com.yx.demo.servlet.listener;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListenerConfig {

    @Bean
    public ServletListenerRegistrationBean<MyServletRequestListener> regServletListernerBean() {
        ServletListenerRegistrationBean<MyServletRequestListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
        servletListenerRegistrationBean.setListener(new MyServletRequestListener());
        return servletListenerRegistrationBean;
    }

}
