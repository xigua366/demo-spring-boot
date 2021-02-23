package com.yx.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.yx.demo.mapper.VideoBannerMapper;
import com.yx.demo.mapper.VideoMapper;
import com.yx.demo.model.entity.Video;
import com.yx.demo.model.entity.VideoBanner;
import com.yx.demo.model.request.DeleteVideoRequest;
import com.yx.demo.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;


@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private VideoBannerMapper videoBannerMapper;

    @Override
    public List<Video> listVideo() {
//        return videoMapper.listVideo();
        return videoMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public List<VideoBanner> listBanner() {
//        return videoBannerMapper.listVideoBanner();
        return videoBannerMapper.selectList(new QueryWrapper<VideoBanner>().orderByAsc("weight"));
    }

    @Override
    public Video findDetailById(Integer videoId) {
        Assert.notNull(videoId, "videoId不能为空");
        return videoMapper.findDetailById(videoId);
    }

    @Override
    public List<Video> selectByPointAndTitleLike(Double point, String title) {
        Assert.notNull(title, "title不能为空");
//        return videoMapper.selectByPointAndTitleLike(point, title);
        return videoMapper.selectList(new QueryWrapper<Video>().eq("point", point).like("title", title));
    }

    @Override
    public int add(Video video) {
//        return videoMapper.add(video);
        video.setCreateTime(new Date());
        return videoMapper.insert(video);
    }

    @Override
    public int addBatch(List<Video> list) {
        for(Video video : list) {
            video.setCreateTime(new Date());
        }
        // TODO BaseMapper中没有提供批量插入记录的方法，要实现批量插入的效果还是要在xml中写SQL
        return videoMapper.addBatch(list);
    }

    @Override
    public int updateVideo(Video video) {
//        return videoMapper.updateVideo(video);
        return videoMapper.updateById(video);
    }

    @Override
    public int updateVideoSelective(Video video) {
//        return videoMapper.updateVideoSelective(video);
        return videoMapper.updateById(video);
    }

    @Override
    public int deleteByCreateTimeAndPrice(DeleteVideoRequest deleteVideoRequest) {
//        return videoMapper.deleteByCreateTimeAndPrice(deleteVideoRequest);
        return videoMapper.delete(new UpdateWrapper<Video>().gt("create_time", deleteVideoRequest.getCreateTime()).ge("price", deleteVideoRequest.getPrice()));
    }

}
