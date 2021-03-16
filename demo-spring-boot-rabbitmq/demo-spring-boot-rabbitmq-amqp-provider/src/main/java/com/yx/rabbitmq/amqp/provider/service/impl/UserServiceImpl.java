package com.yx.rabbitmq.amqp.provider.service.impl;

import com.yx.rabbitmq.amqp.provider.domain.request.UserRegisterRequest;
import com.yx.rabbitmq.amqp.provider.domain.vo.UserRegisterVO;
import com.yx.rabbitmq.amqp.provider.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author yangxi
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserRegisterVO register(UserRegisterRequest userRegisterRequest) {

        // 处理用户注册业务 TODO
        String phone = userRegisterRequest.getPhone();

        UserRegisterVO userRegisterVO = new UserRegisterVO();
        userRegisterVO.setPhone(phone);

        return userRegisterVO;
    }
}