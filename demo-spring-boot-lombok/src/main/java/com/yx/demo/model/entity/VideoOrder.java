package com.yx.demo.model.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 视频课程订单对象
 */
@Builder
@Data
public class VideoOrder {

    private Integer id;


    private String outTradeNo;

    private Integer state;

    private Date createTime;

    private  Integer totalFee;

    private Integer videoId;

    private String videoTitle;

    private String videoImg;

    private Integer userId;
}
