package com.yx.demo.mapper;

import com.yx.demo.model.entity.Episode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EpisodeMapper {

    /**
     * 查询视频课程明细的第一集记录
     * @param videoId
     * @return
     */
    Episode findFirstEpisodeByVideoId(@Param("video_id") int videoId);

}