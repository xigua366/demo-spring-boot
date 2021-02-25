package com.yx.demo.service;

import com.yx.demo.model.request.RegisterRequest;

public interface UserService {

    /**
     * 新增用户
     * @param registerRequest
     * @return
     */
    int save(RegisterRequest registerRequest);


    String findByPhoneAndPwd(String phone, String pwd);
}
