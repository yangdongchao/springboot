package com.example.demo.service;

import com.example.demo.domain.Praise;
import org.hibernate.validator.constraints.EAN;

import java.util.List;

public interface PraiseService {
    void save(Praise praise);
    void deleteByTopicIdAndUserId(int topicId,int userId);
    void like(int topicId,int userId );
    Praise getPraiseByTopicIdAndUserId(int topicId,int userId);
    List<Praise> getAllPraiseByTopicId(int topicId);

    void deleteAllByTopicId(int topicId);
}
