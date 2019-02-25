package com.example.demo.dao;

import com.example.demo.domain.Letter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LetterDao extends JpaRepository<Letter,Integer> {
    Page<Letter> findAllByReceiverId(int id, Pageable pageable);
}
