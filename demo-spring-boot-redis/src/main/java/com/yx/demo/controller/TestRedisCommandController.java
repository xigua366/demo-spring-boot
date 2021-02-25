package com.yx.demo.controller;

import com.yx.demo.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author yangxi
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/pub/test")
public class TestRedisCommandController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 测试setex命令
     * @param key
     * @param value
     * @return
     */
    @GetMapping("/setex")
    public JsonData testSetex(String key, String value) {
        // setIfPresent 方法要求key必须是已经存在的时候才会操作成功，跟redis原生的setex命令不太一样，setex命令无论key是否存在都可以创建工程
        // setex的执行规则是如果key不存在则新建key存入redis，如果key已经存在，则强行更新原有key的value值，也就是进行覆盖
        Boolean flag = stringRedisTemplate.opsForValue().setIfPresent(key, value);
        return JsonData.buildSuccess(flag);
    }

    /**
     * 测试setnx命令
     * @param key
     * @param value
     * @return
     */
    @GetMapping("/setnx")
    public JsonData testSetnx(String key, String value) {
        // setIfAbsent等效于redis原生的setnx命令，而且比setnx功能更强大，因为还可以原子性的设置过期时间
//        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(key, value);
        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(key, value, 100, TimeUnit.SECONDS);
        return JsonData.buildSuccess(flag);
    }

}