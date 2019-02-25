package com.example.demo.service.imp;

import com.example.demo.dao.PostDao;
import com.example.demo.domain.Post;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName PostServiceImp
 * @Description TODO
 * @Auther ydc
 * @Date 2019/1/8 10:43
 * @Version 1.0
 **/
@Transactional
@Service
public class PostServiceImp  implements  PostService{
    private PostDao postDao;


    /**
     * 更新帖子内容和标题
     * @param newText
     * @param id
     */
    @Override
    public void resetPostText(String newText, int id) {

        postDao.flush();
    }

    @Override
    public void resetPostTitle(String newTitle, int id) {
        //postDao.updateTitleByPostId(newTitle,id);
        postDao.flush();
    }

    @Override
    public List<Post> findPostByTopicId(int topicId) {
        return postDao.findByTopicId(topicId);
    }

    /**
     * 根据id删除帖子
     * @param id
     */
    @Override
    public void deletePostById(int id) {
        postDao.deleteById(id);
        postDao.flush();
    }

    @Override
    public void save(Post post) {
        postDao.save(post);
    }

    /**
     * 根据topicId  查询一个话题下的所有帖子，并且按分页返回.
     * @param pageNum
     * @param pageSize
     * @param topicId
     * @return
     */

    @Override
    public Page<Post> getPostPage(int pageNum, int pageSize, int topicId) {
        PageRequest pageable = new PageRequest(pageNum-1,pageSize,Sort.Direction.ASC,"createTime");
        return postDao.findAllByTopicId(topicId,pageable);
    }

    @Override
    public void deleteAllByTopicId(int topicId) {
        postDao.deleteAllByTopicId(topicId);
        postDao.flush();
    }

    @Autowired
    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

}
