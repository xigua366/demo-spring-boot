package com.yx.demo.service;

import com.yx.demo.model.entity.Video;
import com.yx.demo.model.entity.VideoBanner;

import java.util.List;

/**
 * @author yangxi
 * @version 1.0
 */
public interface VideoService {

    /**
     * 获取首页轮播图
     * @return
     */
    List<VideoBanner> listBanner();

    /**
     * 获取首页视频课程列表
     * @return
     */
    List<Video> listVideo();

    /**
     * 获取视频课程详情
     * @param videoId
     * @return
     */
    Video getVideoDetail(Integer videoId);

}