package com.example.demo.service.imp;

import com.example.demo.dao.FollowerDao;
import com.example.demo.domain.Follower;
import com.example.demo.service.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName FollowerServiceImp
 * @Description TODO
 * @Auther ydc
 * @Date 2019/1/19 17:49
 * @Version 1.0
 **/
@Service
@Transactional
public class FollowerServiceImp implements FollowerService {

    private FollowerDao followerDao;

    @Autowired
    public void setFollowerDao(FollowerDao followerDao) {
        this.followerDao = followerDao;
    }

    /**
     * 查所有的粉丝
     * @param id
     * @return
     */
    @Override
    public List<Follower> getAllFansById(int id) {
        return followerDao.findAllByUserId(id);
    }

    @Override
    public void save(Follower follower) {
        followerDao.save(follower);
    }

    /**
     * 查所有我的关注
     * @param id
     * @return
     */
    @Override
    public List<Follower> getAllMyFollowById(int id) {
        return followerDao.findAllByFId(id);
    }

    @Override
    public void delete(int fid, int userId) {
        followerDao.deleteByFIdAndUserId(fid,userId);
    }

    @Override
    public Follower findByUserIdAndFid(int userId, int fid) {
        return followerDao.findByUserIdAndFId(userId,fid);
    }
}
