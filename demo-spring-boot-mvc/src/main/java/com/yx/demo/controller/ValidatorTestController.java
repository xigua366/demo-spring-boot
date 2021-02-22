package com.yx.demo.controller;

import com.yx.demo.model.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author yangxi
 * @version 1.0
 */
@RestController
@RequestMapping("/validator/test")
public class ValidatorTestController {

    private static final Logger log = LoggerFactory.getLogger(ValidatorTestController.class);

    /**
     *
     * 请求示例参数：
     * {
     *     "id":1,
     *     "username":"ceshi232323",
     *     "password":"12333333",
     *     "email":"xx@qq.com",
     *     "phone":"13824543345",
     *     "idCard":"431122198009012518",
     *     "sex":0
     * }
     *
     * @param userVO
     * @return
     */
    @PostMapping(value = "/user/create", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public boolean createUser(@RequestBody @Validated UserVO userVO) {
        log.info("userVO:{}", userVO);
        return true;
    }

    @PostMapping(value = "/user/update", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    public boolean updateUser(@RequestBody @Validated UserVO userVO) {
        UserVO user = null;
        //user = userDao.selectById(userId);
        Assert.notNull(user, "用户不存在！");

        log.info("userVO:{}", userVO);
        return true;
    }

}