package com.yx.demo.controller;


import com.yx.demo.model.request.SendMailRequest;
import com.yx.demo.service.MailService;
import com.yx.demo.util.JsonData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangxi
 */
@RestController
@RequestMapping("mail/test")
public class MailTestController {

    @Autowired
    private MailService mailService;

    /**
     * 发送邮件测试
     * @param sendMailRequest
     * @return
     */
    @PostMapping("send-mail")
    public JsonData sendMail(@RequestBody SendMailRequest sendMailRequest) {
        String to = sendMailRequest.getTo();
        if(StringUtils.isEmpty(to)) {
            to = "xigua366@qq.com";
        }
        String subject = sendMailRequest.getSubject();
        if(StringUtils.isEmpty(subject)) {
            subject = "测试邮件";
        }
        String content = sendMailRequest.getContent();
        if(StringUtils.isEmpty(content)) {
            content = "<h1>hello world</h1>";
        }
        mailService.sendMail(to, subject, content);
        return JsonData.buildSuccess();
    }

}
