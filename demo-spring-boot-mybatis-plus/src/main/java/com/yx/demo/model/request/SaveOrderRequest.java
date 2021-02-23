package com.yx.demo.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author yangxi
 * @version 1.0
 */
public class SaveOrderRequest {

    @JsonProperty("video_id")
    private Integer videoId;

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }
}