package com.example.demo.dao;

import com.example.demo.domain.Praise;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @ClassName PraiseDao
 * @Description 点赞类与数据库 的交互接口
 * @Auther ydc
 * @Date 2019/1/25 20:26
 * @Version 1.0
 **/
public interface PraiseDao extends JpaRepository<Praise,Integer> {
    List<Praise> findAllByTopicId(int id);
    void deleteByPraiseId(int id);
    Praise findByUserIdAndTopicId(int userId,int topicId);
    Praise findByTopicIdAndUserId(int TopicId,int userId);
    void deleteAllByTopicId(int topicId);
}
