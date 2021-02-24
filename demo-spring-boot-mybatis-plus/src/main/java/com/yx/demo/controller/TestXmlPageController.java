package com.yx.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yx.demo.model.entity.Video;
import com.yx.demo.service.VideoService;
import com.yx.demo.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试 XML 自定义分页
 *
 * 官方文档地址：https://mybatis.plus/guide/page.html
 *
 * @author yangxi
 * @version 1.0
 */
@RestController
@RequestMapping("/test/xmlpage")
public class TestXmlPageController {

    @Autowired
   private VideoService videoService;

    /**
     * 分页查询视频信息 xml sql方式
     * @param page
     * @param size
     * @return
     */
    @GetMapping("pageVideo")
    public JsonData pageVideo(int page, int size) {
        IPage<Video> videoPage = videoService.pageVideoXmlSql(page, size);
        return JsonData.buildSuccess(videoPage);
    }

}