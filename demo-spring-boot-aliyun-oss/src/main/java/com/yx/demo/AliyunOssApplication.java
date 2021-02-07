package com.yx.demo;

import com.yx.demo.config.OSSConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author yangxi
 * @version 1.0
 */
@EnableConfigurationProperties(OSSConfig.class)
@SpringBootApplication
public class AliyunOssApplication {

    public static void main(String[] args) {
        SpringApplication.run(AliyunOssApplication.class, args);
    }
}