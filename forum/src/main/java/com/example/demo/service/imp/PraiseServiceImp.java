package com.example.demo.service.imp;

import com.example.demo.dao.PraiseDao;
import com.example.demo.dao.TopicDao;
import com.example.demo.domain.Praise;
import com.example.demo.domain.Topic;
import com.example.demo.service.PraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ClassName PraiseServiceImp
 * @Description TODO
 * @Auther ydc
 * @Date 2019/1/25 15:13
 * @Version 1.0
 **/
@Transactional
@Service
public class PraiseServiceImp implements PraiseService {

    private PraiseDao praiseDao;


    private TopicDao topicDao;
    @Autowired
    public void setPraiseDao(PraiseDao praiseDao) {
        this.praiseDao = praiseDao;
    }

    @Autowired
    public void setTopicDao(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    /**
     * 根据topicId和userId先查询到Praise对象，再根据praiseId来进行删除操作
     * @param topicId
     * @param userId
     */
    @Override
    public void deleteByTopicIdAndUserId(int topicId, int userId) {
        praiseDao.deleteByPraiseId(praiseDao.findByUserIdAndTopicId(topicId,userId).getPraiseId());
    }

    @Override
    public void save(Praise praise) {
        praiseDao.save(praise);
    }

    /**
     * 点赞操作
     * @param topicId
     * @param userId
     */
    @Override
    public void like(int topicId, int userId) {
        Praise praise = praiseDao.findByUserIdAndTopicId(userId,topicId);
        Topic topic = topicDao.findByTopicId(topicId);
        if(praise==null){
            praise = new Praise(topicId,userId,new Date());
            praiseDao.save(praise);
            topicDao.updatePraiseByTopicId(topic.getPraise()+1,topicId);//+1操作
        }
        else{
            praiseDao.deleteByPraiseId(praise.getPraiseId());
            topicDao.updatePraiseByTopicId(topic.getPraise()-1,topicId);//-1操作
        }
    }

    @Override
    public Praise getPraiseByTopicIdAndUserId(int topicId, int userId) {
        return praiseDao.findByTopicIdAndUserId(topicId,userId);
    }

    @Override
    public List<Praise> getAllPraiseByTopicId(int topicId) {
        return praiseDao.findAllByTopicId(topicId);
    }

    @Override
    public void deleteAllByTopicId(int topicId) {
        praiseDao.deleteAllByTopicId(topicId);
        praiseDao.flush();
        /**
         * 删除操作必须要有flush，不然不会立刻更新到数据库
         */
    }
}

