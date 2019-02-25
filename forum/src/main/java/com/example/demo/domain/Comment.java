package com.example.demo.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Comment
 * @Description 评论，作为post的接收类，和post功能一样，只不过post面向数据库，comment面向网页
 * @Auther ydc
 * @Date 2019/1/31 8:55
 * @Version 1.0
 **/
public class Comment extends BaseDomain implements Serializable {
    private int id;
    private int topicId;
    private String userName;
    private String userPhoto;
    private String content;
    private Date createTime;
    private int postId;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTopicId() {
        return topicId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public String getContent() {
        return content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public int getPostId() {
        return postId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setPostId(int postId) {
        this.postId = postId;

    }

    public Comment(int topicId, String userName, String userPhoto, String content, Date createTime, int postId, int userId) {
        this.topicId = topicId;
        this.userName = userName;
        this.userPhoto = userPhoto;
        this.content = content;
        this.createTime = createTime;
        this.postId = postId;
        this.userId = userId;
    }

    public Comment() {
    }
}
