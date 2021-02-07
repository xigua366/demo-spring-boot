package com.yx.demo.service.impl;


import com.yx.demo.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author yangxi
 *
 **/

@Service
public class MailServiceImpl implements MailService {

    private static final Logger log = LoggerFactory.getLogger(MailServiceImpl.class);

    /**
     *  springboot 提供的一个发送邮件的简单抽象，直接注入即可
     */
    @Autowired
    private JavaMailSender mailSender;


    @Value("${spring.mail.from}")
    private String from;

    /**
     * 发送邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     */
    @Override
    public void sendMail(String to, String subject, String content) {

        //创建一个邮箱消息对象

        // 简单的文本邮件对象 FIXME 如何发送一个html格式内容的邮件呢？
        SimpleMailMessage message = new SimpleMailMessage();

        //配置邮箱发送人
        message.setFrom(from);

        //邮件的收件人
        message.setTo(to);

        //邮件的主题
        message.setSubject(subject);

        //邮件的内容
        message.setText(content);


        mailSender.send(message);

        log.info("邮件发送成功:{}",message.toString());

    }
}
