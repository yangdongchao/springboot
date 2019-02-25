package com.example.demo.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Topic
 * @Description TODO
 * @Auther ydc
 * @Date 2019/1/7 20:12
 * @Version 1.0
 **/
@Entity
@Table(name = "topic")
public class Topic extends BaseDomain implements Serializable {
    public static final int hot_TOPIC = 1; //热门主题

    public static final int NOT_hot_TOPIC = 0; //普通主题

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private int topicId; //帖子id

    @Column(name = "board_id")
    private  int boardId; //所属版块id

    @Column(name = "user_id")
    private int userId;//发表topic的用户id

    @Column(name = "topic_title")
    private String topicTitle; //帖子标题

    @Column(name = "create_time")
    private Date createTime;  //创建时间

    @Column(name = "last_post")
    private Date lastPost; //最后回复时间

    @Column(name = "topic_views")
    private  int topicViews; //浏览次数

    @Column(name = "topic_replies")
    private  int topicReplies; // 回复数量

    @Column(name = "hot")
    private  int hot; //是否是热门帖子

    @Column(name = "background_id")
    private  int backGroundId;  //背景图片id


    @Column(name = "type")
    private String type;

    @Transient
    private String url;//阅读该topic的地址，这里没有存入数据库，因为访问时需要topicID


    @Column(name = "logo_url")
    private String logoUrl;//话题的logo图片，目前没有开发支持用户上传喜欢背景的功能，采用默认图片的方式

    @Column(name = "description")
    private String decription;//该话题的简介

    @Column(name = "praise")
    private int praise;//该话题获得的赞

    @Column(name = "content")
    private String content;//该话题的正文

    @Column(name = "label")
    private String label;//利用文本分类算法得到的文章类型

    @Transient
    private User user;//数据库里面已经有userId了，想要获得user是很简单的，由于在网页中往往需要userName，所以将user设为临时变量，可以方便获得userName

    public User getUser() {
        return user;
    }

    @Transient
    private Board board;//因为需要boardName

    @Transient
    private int isPraise; //判断当前用户是否点赞了该话题

    public void setIsPraise(int isPraise) {
        this.isPraise = isPraise;
    }

    public int getIsPraise() {
        return isPraise;
    }

    public static int getHot_TOPIC() {
        return hot_TOPIC;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public int getTopicId() {
        return topicId;
    }

    public int getBoardId() {
        return boardId;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getDecription() {
        return decription;
    }

    public int getPraise() {
        return praise;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public void setPraise(int praise) {
        this.praise = praise;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getLastPost() {
        return lastPost;
    }

    public int getTopicViews() {
        return topicViews;
    }

    public int getTopicReplies() {
        return topicReplies;
    }

    public int getHot() {
        return hot;
    }

    public int getBackGroundId() {
        return backGroundId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public void setBoardId(int boardId) {
        boardId = boardId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setLastPost(Date lastPost) {
        this.lastPost = lastPost;
    }

    public void setTopicViews(int topicViews) {
        this.topicViews = topicViews;
    }

    public void setTopicReplies(int topicReplies) {
        this.topicReplies = topicReplies;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public void setBackGroundId(int backGroundId) {
        this.backGroundId = backGroundId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Topic(int boardId, int userId, String topicTitle, Date createTime, Date lastPost, int topicViews, int topicReplies, int hot, int backGroundId, String type, String url, String logoUrl, String decription, int praise, String content) {
        this.boardId = boardId;
        this.userId = userId;
        this.topicTitle = topicTitle;
        this.createTime = createTime;
        this.lastPost = lastPost;
        this.topicViews = topicViews;
        this.topicReplies = topicReplies;
        this.hot = hot;
        this.backGroundId = backGroundId;
        this.type = type;
        this.url = url;
        this.logoUrl = logoUrl;
        this.decription = decription;
        this.praise = praise;
        this.content = content;
    }

    public Topic() {
    }
}
