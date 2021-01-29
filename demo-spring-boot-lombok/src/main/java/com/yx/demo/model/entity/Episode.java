package com.yx.demo.model.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 视频课程 集对象
 */
@Data
@ToString(of = {"id", "title", "num"})
public class Episode {

    private Integer id;

    private String title;

    private Integer num;

    private Integer ordered;


    private String playUrl;


    private Integer chapterId;


    private Integer free;

    private Integer videoId;

    private Date createTime;
}
