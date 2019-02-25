package com.example.demo.service;

import com.example.demo.domain.Letter;
import org.springframework.data.domain.Page;

public interface LetterService  {
    void save(Letter letter);

    Page<Letter> getLettersByReceiverId(int page,int pageSize,int receiverId);
}
