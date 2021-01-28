package com.yx.demo.dao.impl;

import com.yx.demo.dao.VideoDAO;
import com.yx.demo.model.entity.Chapter;
import com.yx.demo.model.entity.Episode;
import com.yx.demo.model.entity.Video;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author yangxi
 * @version 1.0
 */
@Component
public class VideoDAOImpl implements VideoDAO {

    @Override
    public List<Video> list() {

        List<Video> list = new ArrayList<>();

        // 模拟一些视频对象数据
        Video video1 = new Video();
        video1.setId(30);
        video1.setTitle("互联网架构之JAVA虚拟机JVM零基础到高级实战");
        video1.setSummary("https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/video/2019_backend/jvm_detail.jpeg");
        video1.setPrice(3980);
        video1.setPoint(9.1);
        video1.setCoverImg("https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/video/2019_backend/jvm.jpeg");
        video1.setCreateTime(new Date());
        video1.setChapterList(null);

        Video video2 = new Video();
        video2.setId(30);
        video2.setTitle("权限框架Shiro+SpringBoot2.x零基础到高级实战");
        video2.setSummary("https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/video/2019_backend/shiro_detail.jpeg");
        video2.setPrice(2980);
        video2.setPoint(8.9);
        video2.setCoverImg("https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/video/2019_backend/shiro.jpeg");
        video2.setCreateTime(new Date());
        video2.setChapterList(null);

        Video video3 = new Video();
        video3.setId(32);
        video3.setTitle("新版Maven3.5+Nexus私服搭建全套核心技术");
        video3.setSummary("https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/video/2019_backend/maven_detail.jpeg");
        video3.setPrice(1980);
        video3.setPoint(8.9);
        video3.setCoverImg("https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/video/2019_backend/maven.png");
        video3.setCreateTime(new Date());
        video3.setChapterList(null);

        list.add(video1);
        list.add(video2);
        list.add(video3);

        System.out.println("模拟从数据库查询首页视频课程列表数据。size:" + list.size());

        return list;
    }

    @Override
    public Video findVideoDetailById(Integer videoId) {

//        "id": 40,
//                "title": "全新微信小程序零基础到项目实战",
//                "summary": "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/video/2019_frontend/%E5%B0%8F%E7%A8%8B%E5%BA%8F/wx_app_detail.png",
//                "price": 5980,
//                "point": 9.1,
//                "cover_img": "https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/video/2019_frontend/%E5%B0%8F%E7%A8%8B%E5%BA%8F/wxapp.png",
//                "create_time": "2019-08-19T03:14:00.000+00:00",
//                "chapter_list": [
//        {
//            "id": 370,
//                "videoId": null,
//                "title": "走进微信⼩小程序的世界",
//                "ordered": 1,
//                "createTime": "2019-09-06T03:39:59.000+00:00",
//                "episodeList": [
//            {
//                "id": 11000,
//                    "title": "微信小程序课程介绍",
//                    "num": 1,
//                    "ordered": 1,
//                    "free": 0,
//                    "play_url": "xdclass.net/aaa.mp4",
//                    "chapter_id": null,
//                    "video_id": null,
//                    "create_time": null
//            }
//                ]
//        }

        Video video = new Video();
        video.setId(videoId);
        video.setTitle("全新微信小程序零基础到项目实战");
        video.setSummary("https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/video/2019_frontend/%E5%B0%8F%E7%A8%8B%E5%BA%8F/wx_app_detail.png");
        video.setPrice(5980);
        video.setPoint(9.1);
        video.setCoverImg("https://xd-video-pc-img.oss-cn-beijing.aliyuncs.com/xdclass_pro/video/2019_frontend/%E5%B0%8F%E7%A8%8B%E5%BA%8F/wxapp.png");
        video.setCreateTime(new Date());

        List<Chapter> chapterList = new ArrayList<>();
        Chapter chapter1 = new Chapter();
        chapter1.setId(370);
        chapter1.setVideoId(videoId);
        chapter1.setTitle("走进微信⼩小程序的世界");
        chapter1.setOrdered(1);
        chapter1.setCreateTime(new Date());

        List<Episode> episodeList = new ArrayList<>();
        Episode episode1 = new Episode();
        episode1.setId(11000);
        episode1.setTitle("微信小程序课程介绍");
        episode1.setNum(1);
        episode1.setOrdered(1);
        episode1.setFree(0);
        episode1.setPlayUrl("xdclass.net/aaa.mp4");
        episode1.setChapterId(chapter1.getId());
        episode1.setVideoId(videoId);
        episode1.setCreateTime(new Date());
        episodeList.add(episode1);
        chapter1.setEpisodeList(episodeList);

        chapterList.add(chapter1);

        video.setChapterList(chapterList);

        System.out.println("模拟从数据库查询视频课程详情。videoId:" + videoId);

        return video;
    }
}