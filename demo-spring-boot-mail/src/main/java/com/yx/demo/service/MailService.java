package com.yx.demo.service;

/**
 * @author yangxi
 */
public interface MailService {

    /**
     * 发送邮件
     * @param to
     * @param subject
     * @param content
     */
    void sendMail(String to,String subject, String content);
}
