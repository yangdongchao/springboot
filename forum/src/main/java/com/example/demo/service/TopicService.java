package com.example.demo.service;

import com.example.demo.domain.Topic;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TopicService {
    void deleteTopic(int id);//删除话题
    void resetTopicHot(int hot,int id);//修改话题


    void TopicSave(Topic topic);

    void resetTopicTitle(String string,int id);

    void resetTopicBackGroud(int pictureId,int id);

    List<Topic> getAllTopicByUserId(int id);

    List<Topic> getAllTopicByBoardId(int id);

    Topic getTopicByTopicId(int id);

    Page<Topic> getTopicsByBoardId(int pageNum, int pageSize,int id);

    void AmendTopic(int boardId,String descrition,String content,String title,int topicId);

    Page<Topic> getAllTopic(int pageNum,int pageSize);

    Page<Topic> getTopicBySearch(int pageNum,int PageSize,String key);

    void deleteTopicByTopicId(int topicId);

    Page<Topic> getAllPageTopicByUserId(int page,int size,int userId);

    void updateTopicRepliesByTopicId(int cnt,int topicId);

    void updateTopicLabelByTopicId(String label,int topicId);

    void updateTopicViewByTopicId(int voiw,int topicId);

}
