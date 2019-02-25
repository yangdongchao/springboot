package com.example.demo.web;

import com.example.demo.domain.Comment;
import com.example.demo.domain.Post;
import com.example.demo.domain.Topic;
import com.example.demo.domain.User;
import com.example.demo.domain.result.ExceptionMsg;
import com.example.demo.domain.result.ResponseData;
import com.example.demo.service.TopicService;
import com.example.demo.service.imp.BoardServiceImp;
import com.example.demo.service.imp.PostServiceImp;
import com.example.demo.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName PostController
 * @Description TODO
 * @Auther ydc
 * @Date 2019/1/31 8:28
 * @Version 1.0
 **/
@RestController
public class PostController  extends BaseController{
    private PostServiceImp postServiceImp;
    private UserServiceImp userServiceImp;
    private BoardServiceImp boardServiceImp;
    private TopicService topicService;

    @Autowired
    public void setPostServiceImp(PostServiceImp postServiceImp) {
        this.postServiceImp = postServiceImp;
    }

    @Autowired
    public void setUserServiceImp(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @Autowired
    public void setBoardServiceImp(BoardServiceImp boardServiceImp) {
        this.boardServiceImp = boardServiceImp;
    }

    @Autowired
    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    @RequestMapping("post/add")
    public ResponseData postAdd(HttpServletRequest request,int topicId,String text){
        //System.out.println(post.getPostText());
        System.out.println(text);
        try {
            /**
             * 先保存评论记录，后更新topic的topicReplies
             */
            User user = getSessionUser(request);
            Post post1 = new Post(1, "0000", new Date(), topicId, text, user.getUserId(), 1, user.getUserName());
            postServiceImp.save(post1);
            Topic topic =topicService.getTopicByTopicId(topicId);
            topicService.updateTopicRepliesByTopicId(topic.getTopicReplies()+1,topicId);
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping("post/list/{topicId}")
    public List<Comment> getListPost(@PathVariable("topicId") int topicId){
            List<Post> posts = postServiceImp.findPostByTopicId(topicId);
            return changePost(posts);
    }

    @RequestMapping("post/delete/{postId}/{topicId}")
    public ResponseData deletePost(@PathVariable("postId") int postId,@PathVariable("topicId") int topicId){
        /**
         * 先删除评论，在更新topic
         */
        postServiceImp.deletePostById(postId);
        Topic topic =topicService.getTopicByTopicId(topicId);
        topicService.updateTopicRepliesByTopicId(topic.getTopicReplies()-1,topicId);
        return new ResponseData(ExceptionMsg.SUCCESS);
    }

    private List<Comment> changePost(List<Post> posts){
        List<Comment> comments=new ArrayList<>();
        for(Post post:posts){
            User user = userServiceImp.getUserById(post.getUserId());
            Comment comment =new Comment(post.getTopicId(),post.getUserName(),user.getPhoto(),post.getPostText(),post.getCreateTime(),post.getPostId(),user.getUserId());
            comment.setId(post.getPostId());
            comments.add(comment);
        }
        return comments;
    }
}
