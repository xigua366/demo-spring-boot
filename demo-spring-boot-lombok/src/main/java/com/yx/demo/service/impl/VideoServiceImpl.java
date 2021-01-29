package com.yx.demo.service.impl;

import com.yx.demo.dao.VideoBannerDAO;
import com.yx.demo.dao.VideoDAO;
import com.yx.demo.model.entity.Video;
import com.yx.demo.model.entity.VideoBanner;
import com.yx.demo.service.VideoService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangxi
 * @version 1.0
 */
@Log
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoDAO videoDAO;

    @Autowired
    private VideoBannerDAO videoBannerDAO;

    @Override
    public List<VideoBanner> listBanner() {
        return videoBannerDAO.list();
    }

    @Override
    public List<Video> listVideo() {
        return videoDAO.list();
    }

    @Override
    public Video getVideoDetail(Integer videoId) {
        return videoDAO.findVideoDetailById(videoId);
    }
}