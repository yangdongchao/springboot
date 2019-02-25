package com.example.demo.domain;

import com.example.demo.domain.enums.LetterType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Letter
 * @Description 私信类
 * @Auther ydc
 * @Date 2019/2/4 10:30
 * @Version 1.0
 **/
@Entity
@Table(name = "letter")
public class Letter extends BaseDomain implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "letter_id")
    private int letterId;

    @Column(name = "sender_id")
    private int senderId;//发送者

    @Column(name = "receiver_id")
    private int receiverId;//接收者

    @Column(name = "content")
    private String content;//文本内容

    @Column(name = "create_time")
    private Date createTime;//发送时间

    @Enumerated(EnumType.STRING)
    @Column(name = "letter_type")
    private LetterType type;//信的类型，私信还是回复

    @Transient
    private User sender;

    @Transient
    private String sendType;

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public int getLetterId() {
        return letterId;
    }




    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public String getContent() {
        return content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public LetterType getType() {
        return type;
    }

    public void setLetterId(int letterId) {
        this.letterId = letterId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setType(LetterType type) {
        this.type = type;
    }

    public Letter(int senderId, int receiverId, String content, Date createTime, LetterType type) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.createTime = createTime;
        this.type = type;
    }

    public Letter() {
    }
}
