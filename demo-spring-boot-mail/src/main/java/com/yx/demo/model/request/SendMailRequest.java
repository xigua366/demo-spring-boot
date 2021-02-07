package com.yx.demo.model.request;

/**
 * @author yangxi
 * @version 1.0
 */
public class SendMailRequest {

    /**
     * 目标邮箱
     */
    private String to;

    /**
     * 邮件标题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String content;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}