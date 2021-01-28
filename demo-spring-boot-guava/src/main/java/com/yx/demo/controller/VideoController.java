package com.yx.demo.controller;

import com.yx.demo.model.entity.Video;
import com.yx.demo.model.entity.VideoBanner;
import com.yx.demo.service.VideoService;
import com.yx.demo.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
     * localhost:8080/api/v1/pub/video/list_banner
     * 查询首页轮播图列表
     * @return
     */
    @GetMapping("list_banner")
    public JsonData listBanner() {
        List<VideoBanner> list = videoService.listBanner();
        return JsonData.buildSuccess(list);
    }

    /**
     * localhost:8080/api/v1/pub/video/list_video
     * 查询视频列表
     * @return
     */
    @GetMapping("list_video")
    public JsonData listVideo() {
        List<Video> list = videoService.listVideo();
        return JsonData.buildSuccess(list);
    }

    /**
     * localhost:8080/api/v1/pub/video/list_video
     * 查询视频列表
     * @return
     */
    @GetMapping("video_detail")
    public JsonData videoDetail(@RequestParam("video_id") Integer videoId) {
        Video video = videoService.getVideoDetail(videoId);
        return JsonData.buildSuccess(video);
    }

}