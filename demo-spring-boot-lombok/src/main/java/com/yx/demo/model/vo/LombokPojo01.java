package com.yx.demo.model.vo;

import lombok.*;

import java.util.Date;

/**
 * @author yangxi
 * @version 1.0
 */
@Setter
@Getter
@ToString(exclude = "createTime")
public class LombokPojo01 {

    @Getter(AccessLevel.NONE)
    private Long id;

    @Setter(AccessLevel.PRIVATE)
    private String username;

    private String password;

    @NonNull
    private String address;

    private static String phone;

    private Integer age;

    private final Date createTime = new Date();

}