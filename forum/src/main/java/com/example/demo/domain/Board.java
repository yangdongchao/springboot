package com.example.demo.domain;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName Board
 * @Description 版块
 * @Auther ydc
 * @Date 2019/1/7 20:08
 * @Version 1.0
 **/
@Entity
@Table(name = "board")
public class Board extends BaseDomain implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private int boardId;

    @Column(name = "board_name")
    private String boardName;//版块名称

    @Column(name = "board_descript")
    private String boardDescription;//版块描述

    @Column(name = "topic_num")//该版块有的话题数量
    private int topicNum;

    public int getBoardId() {
        return boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public String getBoardDescription() {
        return boardDescription;
    }

    public int getTopicNum() {
        return topicNum;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public void setBoardDescription(String boardDescription) {
        this.boardDescription = boardDescription;
    }

    public void setTopicNum(int topicNum) {
        this.topicNum = topicNum;
    }

    public Board(String boardName, String boardDescription, int topicNum) {
        this.boardName = boardName;
        this.boardDescription = boardDescription;
        this.topicNum = topicNum;
    }

    public Board() {
    }
}
