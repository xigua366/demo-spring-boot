package com.yx.rabbitmq.amqp.provider.controller;

import com.yx.rabbitmq.amqp.provider.domain.request.UserRegisterRequest;
import com.yx.rabbitmq.amqp.provider.domain.vo.UserRegisterVO;
import com.yx.rabbitmq.amqp.provider.mq.UserMQSender;
import com.yx.rabbitmq.amqp.provider.service.UserService;
import com.yx.rabbitmq.amqp.provider.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理Controller组件
 * @author yangxi
 * @version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMQSender userMQSender;

    /**
     * POST http://localhost:8090/user/register
     * {
     *     "phone":"13826431149",
     *     "pwd":"123456",
     *     "name":"李四"
     * }
     *
     * 用户注册
     * @return
     */
    @PostMapping("/register")
    public JsonData register(@RequestBody UserRegisterRequest userRegisterRequest) throws Exception {
        UserRegisterVO userRegisterVO = userService.register(userRegisterRequest);

        // 发送mq
        userMQSender.sendUserMsg(userRegisterRequest.getPhone());

        return JsonData.buildSuccess(userRegisterVO);
    }

}