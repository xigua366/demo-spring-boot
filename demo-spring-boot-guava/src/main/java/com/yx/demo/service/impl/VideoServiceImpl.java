package com.yx.demo.service.impl;

import com.yx.demo.config.BaseCacheManager;
import com.yx.demo.config.CacheKeyConstants;
import com.yx.demo.dao.VideoDAO;
import com.yx.demo.model.entity.Video;
import com.yx.demo.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangxi
 * @version 1.0
 */
@Service
public class VideoServiceImpl implements VideoService {

    private static Logger log = LoggerFactory.getLogger(VideoServiceImpl.class);

    @Autowired
    private VideoDAO videoDAO;

    @Autowired
    private BaseCacheManager baseCacheManager;

    @Override
    public List<Video> listVideo() {
        try {
            Object cacheObj = baseCacheManager.getTenSecondsCache().get(CacheKeyConstants.INDEX_VIDEL_LIST, () -> videoDAO.list());

            if(cacheObj instanceof List) {
                return (List<Video>) cacheObj;
            }
        } catch (Exception e) {
            log.error("缓存视频列表错误", e);
        }
        return null;
    }
}