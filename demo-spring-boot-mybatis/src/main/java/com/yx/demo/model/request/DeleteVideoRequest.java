package com.yx.demo.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author yangxi
 * @version 1.0
 */
public class DeleteVideoRequest {

    /**
     * @JsonFormat 默认是标准时区的时间， 北京时间 东八区 timezone=”GMT+8”
     *     作用：后台的时间 格式化 发送到前台
     *
     * @DateTimeFormat 接受前台的时间格式 传到后台的格式
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @JsonProperty("create_time")
    private Date createTime;

    private Integer price;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "DeleteVideoRequest{" +
                "createTime=" + createTime +
                ", price=" + price +
                '}';
    }
}