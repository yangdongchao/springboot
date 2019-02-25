package com.example.demo.dao;

import com.example.demo.domain.Follower;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowerDao  extends JpaRepository<Follower,Integer> {

    List<Follower> findAllByUserId(int id);

    List<Follower> findAllByFId(int id);

    void deleteByFIdAndUserId(int fid,int userId);

    Follower findByUserIdAndFId(int userId,int fid);
}
