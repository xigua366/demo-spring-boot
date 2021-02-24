package com.yx.demo.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yx.demo.model.entity.Video;
import com.yx.demo.model.entity.VideoBanner;
import com.yx.demo.model.request.DeleteVideoRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoService {

    /**
     * 根据ID获取单条视频记录
     * @param videoId
     * @return
     */
    Video getVideo(Integer videoId);

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
     * xml中写sql更新测试乐观锁版本号
     * @param video
     * @return
     */
    int updateVideoXmlSql(Video video);


    /**
     * 根据时间和价格删除
     * @param deleteVideoRequest
     * @return
     */
    int deleteByCreateTimeAndPrice(DeleteVideoRequest deleteVideoRequest);

    /**
     * 删除视频记录
     * @param video
     * @return
     */
    int deleteVideo(Video video);

    /**
     * 测试mybatis plus 自定义的xml sql实行分页查询
     * @param page
     * @param size
     * @return
     */
    IPage<Video> pageVideoXmlSql(int page, int size);
}
