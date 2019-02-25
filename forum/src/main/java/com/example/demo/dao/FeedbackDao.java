package com.example.demo.dao;

import com.example.demo.domain.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackDao extends JpaRepository<Feedback,Integer> {
}
