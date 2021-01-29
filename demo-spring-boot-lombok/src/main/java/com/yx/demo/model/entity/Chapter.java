package com.yx.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * 视频课程 章对象
 */
@Data
@ToString(exclude = {"episodeList", "createTime"})
public class Chapter {


    private Integer id;

    @JsonProperty("video_id")
    private Integer videoId;

    private String title;

    private Integer ordered;

    @JsonProperty("create_time")
    private Date createTime;

    @JsonProperty("episode_list")
    private List<Episode> episodeList;
}
