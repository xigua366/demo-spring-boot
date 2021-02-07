package com.yx.demo.controller;

import com.google.code.kaptcha.Producer;
import com.yx.demo.model.request.CheckCaptchaCodeRequest;
import com.yx.demo.util.CommonUtil;
import com.yx.demo.util.JsonData;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author yangxi
 * @version 1.0
 */
@RestController
@RequestMapping("api/v1/notity")
public class NotifyController {

    private static final Logger log = LoggerFactory.getLogger(NotifyController.class);

    @Autowired
    private Producer captchaProducer;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 图形验证码有效期10分钟
     */
    private static final long CAPTCHA_CODE_EXPIRED = 60 * 1000 * 10;

    /**
     * 获取图形验证码
     * @param request
     * @param response
     */
    @GetMapping("captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response){

        String captchaText = captchaProducer.createText();
        log.info("图形验证码:{}", captchaText);

        //存储
        String cacheKey = getCaptchaKey(request);
        redisTemplate.opsForValue().set(cacheKey, captchaText, CAPTCHA_CODE_EXPIRED, TimeUnit.MILLISECONDS);

        BufferedImage bufferedImage = captchaProducer.createImage(captchaText);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            ImageIO.write(bufferedImage,"jpg",outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            log.error("获取图形验证码异常:", e);
        }

    }

    /**
     * 提交表单验证图形验证码
     * @param checkCaptchaCodeRequest
     * @return
     */
    @PostMapping("captcha")
    public JsonData checkCaptchaCode(@RequestBody CheckCaptchaCodeRequest checkCaptchaCodeRequest, HttpServletRequest request) {
        String captchaCode = checkCaptchaCodeRequest.getCheckCaptchaCode();
        Assert.notNull(captchaCode, "图形验证码不能为空");
        String cacheKey = getCaptchaKey(request);

        String cacheCaptchaCode = redisTemplate.opsForValue().get(cacheKey);

        if(StringUtils.isNotEmpty(cacheCaptchaCode) && cacheCaptchaCode.equals(captchaCode)) {
            // 图形验证码匹配
            redisTemplate.delete(cacheKey);
            return JsonData.buildSuccess();
        }

        return JsonData.buildError("图形验证码错误");

    }

    /**
     * 获取缓存的key
     * @param request
     * @return
     */
    private String getCaptchaKey(HttpServletRequest request){

        String ip = CommonUtil.getIpAddr(request);
        String userAgent = request.getHeader("User-Agent");

        String deviceId = CommonUtil.md5(ip+userAgent);
        String key = "user-service:captcha:" + deviceId;

        log.info("ip={}",ip);
        log.info("userAgent={}",userAgent);
        log.info("key={}",key);

        return key;

    }
}