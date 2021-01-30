package com.yx.demo.service.impl;

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
        VideoOrder videoOrder = videoOrderMapper.findByUserIdAndVideoIdAndState(userId, videoId, 1);
        if(videoOrder != null) {
            log.warn("用户【{}】已购买了视频课程【{}】", userId, videoId);
            return 0;
        }

        // 新增一个视频课程订单
        Video video = videoMapper.findById(videoId);
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

        int num = videoOrderMapper.saveOrder(newVideoOrder);
        if(num > 0) {
            // 同步创建一条用户视频课程的播放进度的记录
            Episode episode = episodeMapper.findFirstEpisodeByVideoId(videoId);
            if(episode == null){
                throw new MyException(-1,"视频没有集信息，请运营人员检查");
            }
            PlayRecord playRecord = new PlayRecord();
            playRecord.setCreateTime(new Date());
            playRecord.setEpisodeId(episode.getId());
            playRecord.setCurrentNum(episode.getNum());
            playRecord.setUserId(userId);
            playRecord.setVideoId(videoId);
            playRecordMapper.saveRecord(playRecord);
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
        return videoOrderMapper.listOrderByUserId(userId);
    }
}