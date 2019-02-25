package com.example.demo.domain;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Follower
 * @Description TODO
 * @Auther ydc
 * @Date 2019/1/19 17:15
 * @Version 1.0
 **/
@Entity
@Table(name = "follower")
public class Follower extends BaseDomain implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;//被关注的id

    @Column(name = "follow_id")
    private  int fId;//粉丝id

    @Column(name = "create_time")
    private Date createTime;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getfId() {
        return fId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setfId(int fId) {
        this.fId = fId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Follower() {
    }

    public Follower(int userId, int fId, Date createTime) {
        this.userId = userId;
        this.fId = fId;
        this.createTime = createTime;
    }
}
