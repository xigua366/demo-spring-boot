package com.yx.demo.dao.impl;

import com.yx.demo.dao.VideoDAO;
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

        System.out.println("模拟从数据库查询数据。size:" + list.size());

        return list;
    }
}