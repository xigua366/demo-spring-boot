package com.yx.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * spring boot 整合mvc示例工程
 * @author yangxi
 * @version 1.0
 */

// 原生方式整合servlet规范的Servlet、Filter、Listener需要添加这个注解
@ServletComponentScan
@SpringBootApplication
public class MvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvcApplication.class, args);
    }

}