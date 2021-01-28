package com.yx.demo.config;


/**
 * 缓存key管理类
 */
public class CacheKeyConstants {

    /**
     * 首页轮播图缓存key
     */
    public static final String INDEX_BANNER_LIST_KEY = "index:banner:list";


    /**
     * 首页视频列表缓存key
     */
    public static final String INDEX_VIDEL_LIST_KEY = "index:video:list";


    /**
     * 视频详情缓存key, %s是视频id
     */
    public static final String VIDEO_DETAIL_KEY = "video:detail:%s";


}
