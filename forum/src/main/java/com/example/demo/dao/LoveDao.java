package com.example.demo.dao;

import com.example.demo.domain.Love;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoveDao extends JpaRepository<Love,Integer> {

    @Override
    Page<Love> findAll(Pageable pageable);

    Page<Love> findAllByUserId(int userId,Pageable pageable);

    Love findByTopicId(int id);

    void deleteAllByTopicId(int topicId);
}
