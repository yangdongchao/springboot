package com.example.demo.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName Love
 * @Description 我的收藏实体类
 * @Auther ydc
 * @Date 2019/2/5 18:12
 * @Version 1.0
 **/
@Entity
@Table(name = "love")
public class Love extends BaseDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "love_id")
    private int loveId;
    @Column(name = "topic_id")
    private int topicId;//收藏的话题id
    @Column(name = "user_id")
    private int userId;//收藏者id
    @Column(name = "creatTime")
    private Date creatTime;//收藏时间

    @Transient
    private Topic topic;

    @Transient
    private User user;

    public int getLoveId() {
        return loveId;
    }

    public int getTopicId() {
        return topicId;
    }

    public int getUserId() {
        return userId;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public Topic getTopic() {
        return topic;
    }

    public User getUser() {
        return user;
    }

    public void setLoveId(int loveId) {
        this.loveId = loveId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Love(int topicId, int userId, Date creatTime, Topic topic, User user) {
        this.topicId = topicId;
        this.userId = userId;
        this.creatTime = creatTime;
        this.topic = topic;
        this.user = user;
    }

    public Love(int topicId, int userId, Date creatTime) {
        this.topicId = topicId;
        this.userId = userId;
        this.creatTime = creatTime;
    }

    public Love() {
    }
}
