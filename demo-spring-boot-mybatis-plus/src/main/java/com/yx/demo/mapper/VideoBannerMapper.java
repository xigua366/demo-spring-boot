package com.yx.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yx.demo.model.entity.VideoBanner;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VideoBannerMapper extends BaseMapper<VideoBanner> {

    /**
     * 首页轮播图列表
     * @return
     */
    List<VideoBanner> listVideoBanner();
}
