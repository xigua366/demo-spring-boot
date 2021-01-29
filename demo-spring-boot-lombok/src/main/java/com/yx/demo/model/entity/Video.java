package com.yx.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 视频对象实体类
 */
@Data
@EqualsAndHashCode
public class Video {


    private Integer id;

    private String title;


    private String summary;

    @JsonProperty("cover_img")
    private String coverImg;

    private Integer price;

    @JsonProperty("create_time")
    private Date createTime;

    private Double point;

    @JsonProperty("chapter_list")
    private List<Chapter> chapterList;

}