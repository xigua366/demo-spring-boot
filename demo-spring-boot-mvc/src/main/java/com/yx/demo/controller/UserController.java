package com.yx.demo.controller;

import com.yx.demo.model.request.LoginRequest;
import com.yx.demo.model.request.RegisterRequest;
import com.yx.demo.service.UserService;
import com.yx.demo.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/pri/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册接口
     * @param registerRequest
     * @return
     */
    @PostMapping("register")
    public JsonData register(@RequestBody RegisterRequest registerRequest) {

        int rows = userService.save(registerRequest);

        return rows == 1 ? JsonData.buildSuccess("注册成功"): JsonData.buildError("注册失败，请重试");

    }


    /**
     * 登录接口
     * @param loginRequest
     * @return
     */
    @PostMapping("login")
    public JsonData login(@RequestBody LoginRequest loginRequest){

        String token = userService.findByPhoneAndPwd(loginRequest.getPhone(), loginRequest.getPwd());

        return token == null ?JsonData.buildError("登录失败，账号密码错误"): JsonData.buildSuccess(token);

    }



}
