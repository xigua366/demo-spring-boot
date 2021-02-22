package com.yx.demo.service;

import com.yx.demo.model.request.RegisterRequest;

public interface UserService {

    /**
     * 新增用户
     * @param registerRequest
     * @return
     */
    int save(RegisterRequest registerRequest);

    /**
     * 登录接口
     * @param phone 手机号码
     * @param pwd 密码
     * @return token 令牌
     */
    String findByPhoneAndPwd(String phone, String pwd);
}
