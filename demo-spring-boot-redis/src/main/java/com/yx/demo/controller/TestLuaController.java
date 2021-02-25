package com.yx.demo.controller;

import com.yx.demo.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * 测试通过Lua脚本操作redis
 * @author yangxi
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/pub/test/lua")
public class TestLuaController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 测试使用lua脚本来释放锁
     * @param lockKey 分布式锁key
     * @param value
     * @return
     */
    @GetMapping("/deleteLock")
    public JsonData deleteLock(String lockKey, String value) {
        String script = "if redis.call(\"get\",KEYS[1]) == ARGV[1] then return redis.call(\"del\",KEYS[1]) else return 0 end";
        Long result = redisTemplate.execute(new DefaultRedisScript<>(script, Long.class), Arrays.asList(lockKey), value);
        if(result > 0) {
            return JsonData.buildSuccess(result);
        }
        return JsonData.buildError("释放锁失败");

    }

}