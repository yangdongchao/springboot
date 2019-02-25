package com.example.demo.service.imp;

import com.example.demo.dao.LoveDao;
import com.example.demo.domain.Love;
import com.example.demo.service.LoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @ClassName LoveServiceImp
 * @Description TODO
 * @Auther ydc
 * @Date 2019/2/5 18:31
 * @Version 1.0
 **/
@Service
@Transactional
public class LoveServiceImp implements LoveService {

    private LoveDao loveDao;

    @Autowired
    public void setLoveDao(LoveDao loveDao) {
        this.loveDao = loveDao;
    }

    @Override
    public void deleteById(int id) {
            loveDao.deleteById(id);
    }

    @Override
    public void save(Love love) {
        loveDao.save(love);
    }

    @Override
    public Page<Love> findLoveByUserId(int userId, int page, int pageSize) {
        PageRequest pageable = PageRequest.of(page,pageSize,Sort.by(Sort.Direction.DESC,"loveId"));
        return loveDao.findAllByUserId(userId,pageable);
    }

    @Override
    public Love getLoveByTopicId(int topicId) {
        return loveDao.findByTopicId(topicId);
    }

    @Override
    public void deleteAllByTopciId(int topicId) {
        loveDao.deleteAllByTopicId(topicId);
        loveDao.flush();
    }
}
