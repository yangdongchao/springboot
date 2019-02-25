package com.example.demo.service.imp;

import com.example.demo.dao.PostDao;
import com.example.demo.dao.TopicDao;
import com.example.demo.domain.Post;
import com.example.demo.domain.Topic;
import com.example.demo.service.TopicService;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName TopicServiceImp
 * @Description TODO
 * @Auther ydc
 * @Date 2019/1/8 9:48
 * @Version 1.0
 **/
@Transactional
@Service
public class TopicServiceImp implements TopicService {

    private TopicDao topicDao;

    private PostDao postDao;

    /**
     *  删除话题，先删除话题下面的所有帖子，再删除话题
     * @param id
     */
    @Override
    public void deleteTopic(int id){
        List<Post> posts = postDao.findByTopicId(id);
        for(Post post: posts){
            postDao.deleteById(post.getPostId());
        }
        postDao.flush();
        topicDao.deleteById(id);
        topicDao.flush();
    }

    /**
     * 设置热门话题
     * @param hot
     * @param id
     */

    @Override
    public void resetTopicHot(int hot,int id) {
           topicDao.updateHotById(hot,id);
    }

    /**
     * 更换话题背景
     * @param pictureId
     * @param id
     */
    @Override
    public void resetTopicBackGroud(int pictureId, int id) {
        topicDao.updateBackGroundById(pictureId,id);
    }

    /**
     * 修改话题标题
     * @param string
     * @param id
     */
    @Override
    public void resetTopicTitle(String string, int id) {
        topicDao.updateTitleById(string,id);
    }

    @Override
    public void TopicSave(Topic topic) {
        topicDao.save(topic);
    }

    @Override
    public Topic getTopicByTopicId(int id) {
        return topicDao.findByTopicId(id);
    }


    /**
     * 更新title ,所属版块，content
     * @param boardId
     * @param descrition
     * @param content
     * @param title
     */
    @Override
    public void AmendTopic(int boardId, String descrition, String content, String title,int topicId) {
        topicDao.updateBoardIdByTopicId(boardId,topicId);
        topicDao.updateTitleById(title,topicId);
        topicDao.updateContentByTopicId(content,topicId);
        topicDao.updateDescriptionByTopicId(descrition,topicId);
        topicDao.flush();
    }

    @Override
    public Page<Topic> getTopicsByBoardId(int pageNum, int pageSize,int id) {
        Pageable pageable = PageRequest.of(pageNum,pageSize,Sort.by(Sort.Direction.DESC,"topicId"));
        return topicDao.findAllByBoardId(id,pageable);
    }

    @Override
    public List<Topic> getAllTopicByBoardId(int id) {
        return topicDao.findByBoardId(id);
    }

    @Override
    public List<Topic> getAllTopicByUserId(int id) {
        return topicDao.findAllByUserId(id);
    }


    @Override
    public Page<Topic> getAllTopic(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum,pageSize,Sort.by(Sort.Direction.DESC,"topicId"));
        return topicDao.findAll(pageable);
    }


    @Override
    public Page<Topic> getTopicBySearch(int pageNum, int PageSize, String key) {
        key = "%"+key+"%";
        Pageable pageable = PageRequest.of(pageNum,PageSize,Sort.by(Sort.Direction.DESC,"createTime"));
        return topicDao.findByTopicTitleLike(key,pageable);
    }

    @Override
    public void deleteTopicByTopicId(int topicId) {
        topicDao.deleteByTopicId(topicId);
        topicDao.flush();
    }

    @Override
    public Page<Topic> getAllPageTopicByUserId(int page,int size,int userId) {
        Pageable pageable = PageRequest.of(page,size,Sort.by(Sort.Direction.DESC,"createTime"));
        return topicDao.findAllByUserId(userId,pageable);
    }


    @Override
    public void updateTopicRepliesByTopicId(int cnt, int topicId) {
        topicDao.updateTopicRepliesByTopicId(cnt,topicId);
        topicDao.flush();
    }

    /**
     *
     * 修改topic的label
     * @param label
     * @param topicId
     */
    @Override
    public void updateTopicLabelByTopicId(String label, int topicId) {
        topicDao.updateTopicLabelByTopicId(label,topicId);
        topicDao.flush();
    }


    @Override
    public void updateTopicViewByTopicId(int voiw, int topicId) {
        topicDao.updateTopicReviewByTopicId(voiw,topicId);
    }

    @Autowired
    public void setTopicDao(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    @Autowired
    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }
}
