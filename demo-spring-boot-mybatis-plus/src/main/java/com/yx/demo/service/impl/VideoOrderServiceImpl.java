package com.yx.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yx.demo.exception.MyException;
import com.yx.demo.mapper.EpisodeMapper;
import com.yx.demo.mapper.PlayRecordMapper;
import com.yx.demo.mapper.VideoMapper;
import com.yx.demo.mapper.VideoOrderMapper;
import com.yx.demo.model.entity.Episode;
import com.yx.demo.model.entity.PlayRecord;
import com.yx.demo.model.entity.Video;
import com.yx.demo.model.entity.VideoOrder;
import com.yx.demo.service.VideoOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author yangxi
 * @version 1.0
 */
@Service
public class VideoOrderServiceImpl implements VideoOrderService {

    private static Logger log = LoggerFactory.getLogger(VideoOrderServiceImpl.class);

    @Autowired
    private VideoOrderMapper videoOrderMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private EpisodeMapper episodeMapper;

    @Autowired
    private PlayRecordMapper playRecordMapper;

    @Override
    @Transactional
    public int saveOrder(Integer userId, Integer videoId) {
        // 判断是否重复购买某个视频课程
//        VideoOrder videoOrder = videoOrderMapper.findByUserIdAndVideoIdAndState(userId, videoId, 1);
        VideoOrder videoOrder = videoOrderMapper.selectOne(new QueryWrapper<VideoOrder>().eq("user_id", userId).eq("video_id", videoId).eq("state", 1));
        if(videoOrder != null) {
            log.warn("用户【{}】已购买了视频课程【{}】", userId, videoId);
            return 0;
        }

        // 新增一个视频课程订单
//        Video video = videoMapper.findById(videoId);
        Video video = videoMapper.selectById(videoId);
        if(video == null) {
            log.warn("视频课程【{}】不存在", videoId);
            return 0;
        }

        VideoOrder newVideoOrder = new VideoOrder();
        newVideoOrder.setCreateTime(new Date());
        newVideoOrder.setOutTradeNo(UUID.randomUUID().toString());
        newVideoOrder.setState(1);
        newVideoOrder.setTotalFee(video.getPrice());
        newVideoOrder.setUserId(userId);

        newVideoOrder.setVideoId(videoId);
        newVideoOrder.setVideoImg(video.getCoverImg());
        newVideoOrder.setVideoTitle(video.getTitle());

//        int num = videoOrderMapper.saveOrder(newVideoOrder);
        int num = videoOrderMapper.insert(newVideoOrder);
        if(num > 0) {
            // 同步创建一条用户视频课程的播放进度的记录
//            Episode episode = episodeMapper.findFirstEpisodeByVideoId(videoId);
            Episode episode = episodeMapper.selectOne(new QueryWrapper<Episode>().eq("video_id", videoId).eq("num", 1));
            if(episode == null){
                throw new MyException(-1,"视频没有集信息，请运营人员检查");
            }
            PlayRecord playRecord = new PlayRecord();
            playRecord.setCreateTime(new Date());
            playRecord.setEpisodeId(episode.getId());
            playRecord.setCurrentNum(episode.getNum());
            playRecord.setUserId(userId);
            playRecord.setVideoId(videoId);
//            playRecordMapper.saveRecord(playRecord);
            playRecordMapper.insert(playRecord);
        }
        return num;
    }

    /**
     * 查询用户订单记录
     * @param userId
     * @return
     */
    @Override
    public List<VideoOrder> listOrderByUserId(Integer userId) {
//        return videoOrderMapper.listOrderByUserId(userId);
        return videoOrderMapper.selectList(new QueryWrapper<VideoOrder>().eq("user_id", userId));
    }

    /**
     * 分页查询全部订单
     * @param page 第几页
     * @param size 每天显示记录数
     * @return
     */
    @Override
    public IPage<VideoOrder> pageOrderAll(int page, int size) {
//        return videoOrderMapper.listOrderAll();
        Page<VideoOrder> pageParam = new Page<>(page, size);
        // 如果没有where条件，构建一个空的QueryWrapper即可
        IPage<VideoOrder> videoOrderPage = videoOrderMapper.selectPage(pageParam, new QueryWrapper<>());
        return videoOrderPage;
    }
}