package com.example.demo.service;

import com.example.demo.domain.Love;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoveService  {
    void save(Love love);
    void deleteById(int id);

    Page<Love> findLoveByUserId(int userId,int page,int pageSize);

    Love getLoveByTopicId(int topicId);

    void deleteAllByTopciId(int topicId);
}
