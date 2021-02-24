package com.yx.demo.controller;

import com.yx.demo.model.entity.Video;
import com.yx.demo.service.VideoService;
import com.yx.demo.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 测试乐观锁版本号
 *
 * @author yangxi
 * @version 1.0
 */
@RestController
@RequestMapping("/test/version")
public class TestVersionController {

    @Autowired
    private VideoService videoService;

    /**
     * 测试乐观锁版本号
     * @param id
     * @return
     */
    @PostMapping("/video/{id}")
    public JsonData updateVideo(@PathVariable("id") Integer id) {
        Video video = videoService.getVideo(id);
        video.setTitle(video.getTitle() + "x");
        // 强制增加一个版本，模拟报错 （真实执行后发现不会报错，只是更新的记录为0）
        video.setVersion(video.getVersion() + 1);

        // 如果version字段为null，则mybatis plus自动忽略乐观锁版本号的存在，以下是version为null时执行打印的sql
//        ==>  Preparing: UPDATE video SET title=?, summary=?, cover_img=?, price=?, point=? WHERE id=?
//        ==> Parameters: 视频标题xx(String), 概述(String), 封面图(String), 100(Integer), 9.1(Double), 51(Integer)
//        <==    Updates: 1
        // video.setVersion(null);
        int rows = videoService.updateVideoSelective(video);
        if(rows == 0) {
            return JsonData.buildError("更新失败，更新记录为0");
        }
        return JsonData.buildSuccess();
    }

    /**
     * 测试xml sql是否支持乐观锁版本号
     *
     * 测试结论：原生的xml中的sql是不会自动支持mybatis plus的乐观锁机制的，以下是执行结果打印的sql
     * ==>  Preparing: update video set title = ?, summary = ?, cover_img = ?, price = ?, point = ? where id = ?
     * ==> Parameters: 视频标题xxx(String), 概述(String), 封面图(String), 100(Integer), 9.1(Double), 51(Integer)
     * <==    Updates: 1
     *
     * @param id
     * @return
     */
    @PostMapping("/xmlsql/video/{id}")
    public JsonData xmlSqlupdateVideo(@PathVariable("id") Integer id) {
        Video video = videoService.getVideo(id);
        video.setTitle(video.getTitle() + "x");
        // 强制增加一个版本，模拟报错 （真实执行后发现不会报错，只是更新的记录为0）
//        video.setVersion(video.getVersion() + 1);
        int rows = videoService.updateVideoXmlSql(video);
        if(rows == 0) {
            return JsonData.buildError("更新失败，更新记录为0");
        }
        return JsonData.buildSuccess();
    }

    /**
     * 测试mybatis plus BaseMapper中的删除api是否支持乐观锁机制
     *
     * 测试结论：删除api是不支持mybatis plus的乐观锁机制的，以下是打印的sql
     * ==>  Preparing: DELETE FROM video WHERE id=?
     * ==> Parameters: 51(Integer)
     * <==    Updates: 1
     *
     * 那最终的结论就是，只有BaseMapper的update api才支持mybatis plus的乐观锁机制
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/video/{id}")
    public JsonData deleteVideo(@PathVariable("id") Integer id) {
        Video video = videoService.getVideo(id);
        video.setTitle(video.getTitle() + "x");
        // 强制增加一个版本，模拟报错 （真实执行后发现不会报错，只是更新的记录为0）
        video.setVersion(video.getVersion() + 1);
        int rows = videoService.deleteVideo(video);
        if(rows == 0) {
            return JsonData.buildError("删除失败，删除记录为0");
        }
        return JsonData.buildSuccess();
    }
}