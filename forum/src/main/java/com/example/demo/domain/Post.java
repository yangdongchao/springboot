package com.example.demo.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Post
 * @Description TODO
 * @Auther ydc
 * @Date 2019/1/7 20:10
 * @Version 1.0
 **/
@Entity
@Table(name = "post")
public class Post extends BaseDomain implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private  int postId; //回复贴的id

    @Column(name = "board_id")
    private  int boardId; //所在版块

    @Column(name = "post_title")
    private String postTitle; //标题

    @Column(name = "create_time")
    private Date createTime; //创建时间

    @Column(name = "topic_id")
    private  int topicId;

    @Column(name = "post_text")
    private String postText; //内容

    @Column(name = "user_id")
    private int userId;

    @Column(name = "post_type")
    private int postType;

    @Column(name = "userName")
    private String userName;









    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public int getPostType() {
        return postType;
    }

    public void setPostType(int postType) {
        this.postType = postType;
    }

    public int getPostId() {
        return postId;
    }

    public int getBoardId() {
        return boardId;
    }


    public String getPostTitle() {
        return postTitle;
    }

    public Date getCreateTime() {
        return createTime;
    }



    public String getPostText() {
        return postText;
    }



    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }



    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public void setPostText(String postText) {
        postText = postText;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Post(int boardId, String postTitle, Date createTime, int topicId, String postText, int userId, int postType, String userName) {
        this.boardId = boardId;
        this.postTitle = postTitle;
        this.createTime = createTime;
        this.topicId = topicId;
        this.postText = postText;
        this.userId = userId;
        this.postType = postType;
        this.userName = userName;
    }

    public Post() {
    }
}
