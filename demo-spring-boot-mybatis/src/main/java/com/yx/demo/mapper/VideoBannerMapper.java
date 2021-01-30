package com.yx.demo.mapper;

import com.yx.demo.model.entity.VideoBanner;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VideoBannerMapper {

    /**
     * 首页轮播图列表
     * @return
     */
    List<VideoBanner> listVideoBanner();
}
