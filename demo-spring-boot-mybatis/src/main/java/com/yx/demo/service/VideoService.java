package com.yx.demo.service;


import com.yx.demo.model.entity.Video;
import com.yx.demo.model.entity.VideoBanner;
import com.yx.demo.model.request.DeleteVideoRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface VideoService {

    List<Video> listVideo();

    List<VideoBanner> listBanner();

    Video findDetailById(Integer videoId);

    List<Video> selectByPointAndTitleLike(Double point, @Param("title") String title);

    /**
     * 新增一条视频记录
     * @param video
     * @return
     */
    int add(Video video);


    /**
     * 批量插入
     * @param list
     * @return
     */
    int addBatch(List<Video> list);


    /**
     * 更新视频
     * @param video
     * @return
     */
    int updateVideo(Video video);


    /**
     * 动态选择更新
     * @param video
     * @return
     */
    int updateVideoSelective(Video video);


    /**
     * 根据时间和价格删除
     * @param deleteVideoRequest
     * @return
     */
    int deleteByCreateTimeAndPrice(DeleteVideoRequest deleteVideoRequest);
}
