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
     *
     * token 7天有效期
     * yxeyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5eCIsImhlYWRfaW1nIjoiaHR0cHM6Ly94ZC12aWRlby1wYy1pbWcub3NzLWNuLWJlaWppbmcuYWxpeXVuY3MuY29tL3hkY2xhc3NfcHJvL2RlZmF1bHQvaGVhZF9pbWcvMTEuanBlZyIsImlkIjoxLCJuYW1lIjoieXgiLCJpYXQiOjE2MTQyMzYyMjksImV4cCI6MTYxNDg0MTAyOX0.MKsgzYGo9sx4Z8A4DmKNwPMJhwhREUnDUe6x9fR5Gfs
     *
     * @param loginRequest
     * @return
     */
    @PostMapping("login")
    public JsonData login(@RequestBody LoginRequest loginRequest){

        String token = userService.findByPhoneAndPwd(loginRequest.getPhone(), loginRequest.getPwd());

        return token == null ?JsonData.buildError("登录失败，账号密码错误"): JsonData.buildSuccess(token);

    }



}
