package com.yx.rabbitmq.amqp.provider.service;

import com.yx.rabbitmq.amqp.provider.domain.request.UserRegisterRequest;
import com.yx.rabbitmq.amqp.provider.domain.vo.UserRegisterVO;

/**
 * @author yangxi
 * @version 1.0
 */
public interface UserService {

    /**
     * 用户注册
     * @param userRegisterRequest
     * @return
     */
    UserRegisterVO register(UserRegisterRequest userRegisterRequest);

}