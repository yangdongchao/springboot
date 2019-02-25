package com.example.demo.domain;

import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Feedback
 * @Description 用户反馈实体类
 * @Auther ydc
 * @Date 2019/2/10 13:38
 * @Version 1.0
 **/
@Entity
@Table(name = "feedback")
public class Feedback extends BaseDomain implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private int feedback_id;

    @Column(name = "user_id")
    private int user_id;//反馈用户

    @Column(name = "user_name")
    private String user_name;//用户姓名

    @Column(name = "content")
    private String content;//内容

    @Column(name = "user_email")
    private String user_email;//用户email

    @Column(name = "create_time")
    private Date createTime;//反馈时间


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getFeedback_id() {
        return feedback_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getContent() {
        return content;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setFeedback_id(int feedback_id) {
        this.feedback_id = feedback_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public Feedback(int user_id, String user_name, String content, String user_email) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.content = content;
        this.user_email = user_email;
    }

    public Feedback() {
    }
}


