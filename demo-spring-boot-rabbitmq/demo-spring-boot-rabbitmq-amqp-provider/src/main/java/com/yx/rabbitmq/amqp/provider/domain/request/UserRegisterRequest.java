package com.yx.rabbitmq.amqp.provider.domain.request;

import lombok.Data;

/**
 * @author yangxi
 * @version 1.0
 */
@Data
public class UserRegisterRequest {

    /**
     * 注册手机号码
     */
    private String phone;

    /**
     * 注册密码
     */
    private String pwd;

    /**
     * 用户姓名
     */
    private String name;
}