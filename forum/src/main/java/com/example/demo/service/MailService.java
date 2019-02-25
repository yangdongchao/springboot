package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


/**
 * @ClassName MailService
 * @Description TODO
 * @Auther ydc
 * @Date 2019/2/10 8:43
 * @Version 1.0
 **/
@Service
public class MailService {
    private JavaMailSender mailSender; //框架自带的

    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Value("${spring.mail.username}")  //发送人的邮箱  比如155156641XX@163.com
    private String from;

    @Async  //意思是异步调用这个方法
    public void sendMail(String title, String url, String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from); // 发送人的邮箱
        message.setSubject(title); //标题
        message.setTo(email); //发给谁  对方邮箱
        message.setText(url); //内容
        mailSender.send(message); //发送
    }

}
