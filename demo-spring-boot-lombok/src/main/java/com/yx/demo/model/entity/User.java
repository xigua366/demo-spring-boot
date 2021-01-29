package com.yx.demo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;

/**
 * 用户实体类
 */
@Data
//@NoArgsConstructor
//@AllArgsConstructor
@RequiredArgsConstructor
public class User {

    private Integer id;

    private String name;

    private String pwd;

    private String headImg;

    private static String phone;

    private final Date createTime = new Date();
}
