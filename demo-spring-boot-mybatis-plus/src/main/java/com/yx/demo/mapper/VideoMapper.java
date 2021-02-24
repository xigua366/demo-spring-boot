package com.yx.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yx.demo.model.entity.Video;
import com.yx.demo.model.request.DeleteVideoRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VideoMapper extends BaseMapper<Video> {

    /**
     * 查询视频列表
     * @return
     */
    List<Video> listVideo();

    /**
     * 查询视频详情
     * @param videoId
     * @return
     */
    Video findDetailById(@Param("video_id") int videoId);

    /**
     * 简单查询视频信息
     * @param videoId
     * @return
     */
    Video findById(@Param("video_id") int videoId);

    /**
     * 根据评分和标题模糊查询
     * @param point
     * @param title
     * @return
     */
    List<Video> selectByPointAndTitleLike(@Param("point") double point, @Param("title") String title);

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
     * xml sql更新视频对象，测试乐观锁版本号
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
     * 分页查询视频列表
     * @param pageInfo 分页信息
     * @param price 查询条件: 视频价格
     * @return
     */
    IPage<Video> pageVideoXmlSql(Page<?> pageInfo, @Param("price") int price);

}
