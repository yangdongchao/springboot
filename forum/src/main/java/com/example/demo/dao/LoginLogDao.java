package com.example.demo.dao;

import com.example.demo.domain.LoginLog;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoginLogDao extends JpaRepository<LoginLog,Integer> {
    LoginLog findByUserId(int id);

    List<LoginLog> findAllByUserId(int id);
}
