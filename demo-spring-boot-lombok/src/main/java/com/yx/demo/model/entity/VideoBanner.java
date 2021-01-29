package com.yx.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

/**
 * 首页轮播图对象
 *
 */
@Data
@RequiredArgsConstructor
public class VideoBanner {

    private Integer id;

    private String url;

    private String img;

    @JsonProperty("create_time")
    private Date createTime = new Date();

    private Integer weight;
}
