package com.yx.demo.service.impl;

import com.yx.demo.mapper.VideoBannerMapper;
import com.yx.demo.mapper.VideoMapper;
import com.yx.demo.model.entity.Video;
import com.yx.demo.model.entity.VideoBanner;
import com.yx.demo.model.request.DeleteVideoRequest;
import com.yx.demo.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;


@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private VideoBannerMapper videoBannerMapper;

    @Override
    public List<Video> listVideo() {
        return videoMapper.listVideo();
    }

    @Override
    public List<VideoBanner> listBanner() {
        return videoBannerMapper.listVideoBanner();
    }

    @Override
    public Video findDetailById(Integer videoId) {
        Assert.notNull(videoId, "videoId不能为空");
        return videoMapper.findDetailById(videoId);
    }

    @Override
    public List<Video> selectByPointAndTitleLike(Double point, String title) {
        Assert.notNull(title, "title不能为空");
        return videoMapper.selectByPointAndTitleLike(point, title);
    }

    @Override
    public int add(Video video) {
        return videoMapper.add(video);
    }

    @Override
    public int addBatch(List<Video> list) {
        return videoMapper.addBatch(list);
    }

    @Override
    public int updateVideo(Video video) {
        return videoMapper.updateVideo(video);
    }

    @Override
    public int updateVideoSelective(Video video) {
        return videoMapper.updateVideoSelective(video);
    }

    @Override
    public int deleteByCreateTimeAndPrice(DeleteVideoRequest deleteVideoRequest) {
        return videoMapper.deleteByCreateTimeAndPrice(deleteVideoRequest);
    }
}
