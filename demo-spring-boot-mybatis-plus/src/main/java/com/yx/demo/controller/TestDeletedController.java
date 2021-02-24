package com.yx.demo.controller;

import com.yx.demo.model.entity.Video;
import com.yx.demo.service.VideoService;
import com.yx.demo.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试逻辑删除
 * @author yangxi
 * @version 1.0
 */
@RestController
@RequestMapping("/test/deleted")
public class TestDeletedController {

    @Autowired
    private VideoService videoService;

    /**
     * 测试mybatis plus BaseMapper中的delete api的逻辑删除机制
     *
     * 测试结论：OK  执行打印的sql如下：
     * ==>  Preparing: UPDATE video SET deleted=1 WHERE id=? AND deleted=0
     * ==> Parameters: 50(Integer)
     * <==    Updates: 1
     *
     * 其它BaseMapper中的查询、更新等api生成的sql中 where条件中都会自动加上 deleted=0的条件，如下：
     * ==>  Preparing: SELECT id,title,summary,cover_img,price,create_time,point,version,deleted FROM video WHERE id=? AND deleted=0
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/video/{id}")
    public JsonData deleteVideo(@PathVariable("id") Integer id) {
        Video video = videoService.getVideo(id);

        // 删除视频记录
        int rows = videoService.deleteVideo(video);
        if(rows == 0) {
            return JsonData.buildError("删除失败，删除记录为0");
        }
        return JsonData.buildSuccess();
    }
}