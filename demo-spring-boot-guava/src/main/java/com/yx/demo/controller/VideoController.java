package com.yx.demo.controller;

import com.yx.demo.model.entity.Video;
import com.yx.demo.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yangxi
 * @version 1.0
 */
@RestController
@RequestMapping("api/v1/pub/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    /**
     * localhost:8080/api/v1/pub/video/list_video
     * 查询视频列表
     * @return
     */
    @GetMapping("list_video")
    public List<Video> listVideo() {
        return videoService.listVideo();
    }

}