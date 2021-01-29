package com.yx.demo.dao.impl;

import com.yx.demo.dao.VideoBannerDAO;
import com.yx.demo.model.entity.VideoBanner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yangxi
 * @version 1.0
 */
@Component
public class VideoBannerDAOImpl implements VideoBannerDAO {

    @Override
    public List<VideoBanner> list() {

        // 模拟从数据库查询数据

        VideoBanner videoBanner1 = new VideoBanner();
        videoBanner1.setId(1);
        videoBanner1.setUrl("https://m.xdclass.net/#/coursedetail?video_id=49");
        videoBanner1.setImg("https://file.xdclass.net/video/2020/%E9%9D%A2%E8%AF%95%E4%B8%93%E9%A2%98/%E9%9D%A2%E8%AF%95%E4%B8%93%E9%A2%98%E7%AC%AC%E4%B8%80%E5%AD%A3banner.png");
        videoBanner1.setCreateTime(new Date());
        videoBanner1.setWeight(1);

        VideoBanner videoBanner2 = new VideoBanner();
        videoBanner2.setId(2);
        videoBanner2.setUrl("https://m.xdclass.net/#/member");
        videoBanner2.setImg("https://file.xdclass.net/video/%E5%AE%98%E7%BD%91%E8%BD%AE%E6%92%AD%E5%9B%BE/%E8%BD%AE%E6%92%AD%E5%9B%BE-VIP.png");
        videoBanner2.setCreateTime(new Date());
        videoBanner2.setWeight(2);

        VideoBanner videoBanner3 = new VideoBanner();
        videoBanner3.setId(3);
        videoBanner3.setUrl("https://m.xdclass.net/#/coursedetail?video_id=48");
        videoBanner3.setImg("https://file.xdclass.net/video/2020/c%E8%AF%AD%E8%A8%80/WechatIMG5.png");
        videoBanner3.setCreateTime(new Date());
        videoBanner3.setWeight(3);


        VideoBanner videoBanner4 = new VideoBanner();
        videoBanner4.setId(4);
        videoBanner4.setUrl("https://m.xdclass.net/#/coursedetail?video_id=47");
        videoBanner4.setImg("https://file.xdclass.net/video/2020/node/node_banner.png");
        videoBanner4.setCreateTime(new Date());
        videoBanner4.setWeight(4);

        List<VideoBanner> list = new ArrayList<>();
        list.add(videoBanner1);
        list.add(videoBanner2);
        list.add(videoBanner3);
        list.add(videoBanner4);

        System.out.println("模拟从数据库查询首页轮播图列表数据。size:" + list.size());

        return list;
    }
}