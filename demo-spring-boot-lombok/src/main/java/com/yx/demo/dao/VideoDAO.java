package com.yx.demo.dao;

import com.yx.demo.model.entity.Video;

import java.util.List;

/**
 * @author yangxi
 * @version 1.0
 */
public interface VideoDAO {

    List<Video> list();

    Video findVideoDetailById(Integer videoId);
}