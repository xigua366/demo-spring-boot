package com.yx.demo.controller;

import com.yx.demo.model.entity.Video;
import com.yx.demo.model.entity.VideoBanner;
import com.yx.demo.model.request.DeleteVideoRequest;
import com.yx.demo.service.VideoService;
import com.yx.demo.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/pub/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    /**
     * 轮播图列表
     * @return
     */
    @GetMapping("list_banner")
    public JsonData indexBanner() {

        List<VideoBanner> bannerList = videoService.listBanner();
        return JsonData.buildSuccess(bannerList);
    }


    /**
     * 视频列表
     * @return
     */
    @RequestMapping("list_video")
    public JsonData listVideo() {

        List<Video> videoList = videoService.listVideo();
        return JsonData.buildSuccess(videoList);
    }


    /**
     * 查询视频详情，包含章，集信息
     * @param videoId
     * @return
     */
    @GetMapping("find_detail_by_id")
    public JsonData findDetailById(@RequestParam(value = "video_id")Integer videoId){


        Video video = videoService.findDetailById(videoId);

        return JsonData.buildSuccess(video);

    }

    /**
     * 多条件模糊查询视频信息
     * @param point
     * @param title
     * @return
     */
    @GetMapping("query_video")
    public JsonData query_video(@RequestParam(value = "point")Double point, @RequestParam("title") String title) {
        List<Video> videoList = videoService.selectByPointAndTitleLike(point, title);
        return JsonData.buildSuccess(videoList);
    }

    /**
     * 新增一条视频记录
     * @param video
     * @return
     */
    @PostMapping("add")
    public JsonData add(@RequestBody Video video) {
        int rows = videoService.add(video);
        return JsonData.buildSuccess();
    }


    /**
     * 批量插入
     * @param list
     * @return
     */
    @PostMapping("addBatch")
    public JsonData addBatch(@RequestBody List<Video> list) {
        int rows = videoService.addBatch(list);
        return JsonData.buildSuccess();
    }

    /**
     * 更新视频
     * @param video
     * @return
     */
    @PutMapping("updateVideo")
    public JsonData updateVideo(@RequestBody Video video) {
        int rows = videoService.updateVideo(video);
        return JsonData.buildSuccess();
    }

    /**
     * 动态选择更新
     * @param video
     * @return
     */
    @PutMapping("updateVideoSelective")
    public JsonData updateVideoSelective(@RequestBody Video video) {
        int rows = videoService.updateVideoSelective(video);
        return JsonData.buildSuccess();
    }


    /**
     * 根据时间和价格删除
     * @param deleteVideoRequest
     * @return
     */
    @DeleteMapping("deleteByCreateTimeAndPrice")
    public JsonData deleteByCreateTimeAndPrice(@RequestBody DeleteVideoRequest deleteVideoRequest) {
        int rows = videoService.deleteByCreateTimeAndPrice(deleteVideoRequest);
        return JsonData.buildSuccess();
    }

}
