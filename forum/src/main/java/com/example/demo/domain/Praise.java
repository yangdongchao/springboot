package com.example.demo.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Praise
 * @Description TODO
 * @Auther ydc
 * @Date 2019/1/25 14:40
 * @Version 1.0
 **/
@Entity
@Table(name = "praise")
/**
 * 点赞
 */
public class Praise extends BaseDomain implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "praise_id")
    private int praiseId;


    @Column(name = "topic_id")
    private int topicId;//被点赞的话题

    @Column(name = "user_id")
    private int userId;//点赞人id

    @Column(name = "praise_time")
    private Date praiseTime;//点赞时间

    public int getPraiseId() {
        return praiseId;
    }

    public int getTopicId() {
        return topicId;
    }

    public int getUserId() {
        return userId;
    }

    public Date getPraiseTime() {
        return praiseTime;
    }

    public void setPraiseId(int praiseId) {
        this.praiseId = praiseId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPraiseTime(Date praiseTime) {
        this.praiseTime = praiseTime;
    }

    public Praise() {
    }

    public Praise(int topicId, int userId, Date praiseTime) {
        this.topicId = topicId;
        this.userId = userId;
        this.praiseTime = praiseTime;
    }
}
