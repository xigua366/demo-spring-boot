package com.yx.demo.model.request;

/**
 * @author yangxi
 * @version 1.0
 */
public class CheckCaptchaCodeRequest {

    /**
     * 图形验证码
     */
    private String checkCaptchaCode;

    public String getCheckCaptchaCode() {
        return checkCaptchaCode;
    }

    public void setCheckCaptchaCode(String checkCaptchaCode) {
        this.checkCaptchaCode = checkCaptchaCode;
    }
}