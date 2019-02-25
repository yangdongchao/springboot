package com.example.demo.service;

import com.example.demo.domain.Follower;

import java.util.List;

public interface FollowerService {
    List<Follower> getAllFansById(int id);

    List<Follower> getAllMyFollowById(int id);

    void save(Follower follower);

    void delete(int fid,int userId);

    Follower findByUserIdAndFid(int userId,int fid);

    //void deleteAllFans(int id);
}
