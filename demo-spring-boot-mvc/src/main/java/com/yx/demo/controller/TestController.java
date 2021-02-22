package com.yx.demo.controller;

import com.yx.demo.model.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author yangxi
 * @version 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * 测试直接返回User对象
     * @return
     */
    @GetMapping("/user")
    public User testReturnUser() {
        User user = new User();
        user.setId(1);
        user.setName("zhangsan");
        user.setPhone("13454565567");
        user.setCreateTime(new Date());
        user.setPwd("123456");
        user.setHeadImg("https://thirdwx.qlogo.cn/mmopen/vi_32/cer7IibrbEfP3cAJnpe96DDnb5Y82ShLuial2HuQpnUbak7k4G4tT6Wk7A5cmYiclI8iaJia5Aia8UZMWyEgVW3nvjkA/132");
        return user;
    }

    /**
     * 测试直接返回token令牌
     * @return
     */
    @GetMapping("/token")
    public String testReturnToken(HttpServletResponse response) {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String token = "yxeyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5eCIsImhlYWRfaW1nIjoiaHR0cHM6Ly94ZC12aWRlby1wYy1pbWcub3NzLWNuLWJlaWppbmcuYWxpeXVuY3MuY29tL3hkY2xhc3NfcHJvL2RlZmF1bHQvaGVhZF9pbWcvMTIuanBlZyIsImlkIjo4LCJuYW1lIjoieWFuZ3hpIiwiaWF0IjoxNjEzOTgzOTY0LCJleHAiOjE2MTM5ODQ1NjR9.5XqpH7oCi1gGRo5oE0bCqLDW4tgNDSsFWCVzqE1d_Bs";
        return token;
    }

}