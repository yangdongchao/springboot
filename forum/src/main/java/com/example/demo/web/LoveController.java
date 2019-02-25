package com.example.demo.web;

import com.example.demo.domain.Love;
import com.example.demo.domain.Praise;
import com.example.demo.domain.Topic;
import com.example.demo.domain.User;
import com.example.demo.domain.result.ExceptionMsg;
import com.example.demo.domain.result.ResponseData;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.rmi.MarshalledObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName LoveController
 * @Description 我的收藏
 * @Auther ydc
 * @Date 2019/2/5 18:35
 * @Version 1.0
 **/
@RestController
public class LoveController extends BaseController {

    private TopicService topicService;
    private LoveService loveService;
    private UserService userService;
    private BoardService boardService;
    private PraiseService praiseService;

    @Autowired
    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    @Autowired
    public void setLoveService(LoveService loveService) {
        this.loveService = loveService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    @Autowired
    public void setPraiseService(PraiseService praiseService) {
        this.praiseService = praiseService;
    }

    @RequestMapping("userLove")
    public ModelAndView getMyLove(HttpServletRequest request,
                                  @RequestParam(value = "page", defaultValue = "0") Integer page,
                                  @RequestParam(value = "size", defaultValue = "15") Integer size){
        ModelAndView mv = new ModelAndView();
        User user = getSessionUser(request);
        if(user==null){
            mv.setViewName("error");
            return mv;
        }
        Page<Love> loves = loveService.findLoveByUserId(user.getUserId(),page,size);
        List<Topic> loves1 = new ArrayList<>();
        System.out.println(loves.getSize());
        for(Love love:loves){
            //love.setTopic(topicService.getTopicByTopicId(love.getTopicId()));
            Topic topic =topicService.getTopicByTopicId(love.getTopicId());
            topic.setUser(userService.getUserById(topic.getUserId()));
            topic.setBoard(boardService.findBoardById(topic.getBoardId()));
            topic.setUrl("/userReadDetail/"+topic.getTopicId());
            setIsPraise(topic,user);
            loves1.add(topic);
            System.out.println(topic);
        }
        mv.addObject("collects",loves1);
        mv.setViewName("user/love");
        mv.addObject("user",user);
        return mv;
    }


    @RequestMapping("userLove/more")
    public List<Topic> getLoveMore(HttpServletRequest request,
                                   @RequestParam(value = "page", defaultValue = "0") Integer page,
                                   @RequestParam(value = "size", defaultValue = "15") Integer size){
        User user = getSessionUser(request);
        if(user==null){
            return null;
        }
        Page<Love> loves = loveService.findLoveByUserId(user.getUserId(),page,size);
        List<Topic> loves1 = new ArrayList<>();
        for(Love love:loves){
            Topic topic =topicService.getTopicByTopicId(love.getTopicId());
            topic.setUser(userService.getUserById(topic.getUserId()));
            topic.setBoard(boardService.findBoardById(topic.getBoardId()));
            topic.setUrl("/userReadDetail/"+topic.getTopicId());
            setIsPraise(topic,user);
            loves1.add(topic);
        }
        return loves1;
    }

    @RequestMapping("love/detail/{topicId}")
    public ResponseData addLove(HttpServletRequest request,@PathVariable("topicId") int topicId){
        try {
            if (loveService.getLoveByTopicId(topicId) != null) {
                return new ResponseData(ExceptionMsg.Love);
            }
            User user = getSessionUser(request);
            Love love = new Love(topicId, user.getUserId(), new Date());
            loveService.save(love);
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        catch (Exception e){
            e.printStackTrace();
            return  new ResponseData(ExceptionMsg.FAILED);
        }
    }

    /**
     * 调用设置是否已经点赞的函数
     * @param topic
     * @param user
     */
    private void setIsPraise(Topic topic,User user){
        List<Praise> praises = praiseService.getAllPraiseByTopicId(topic.getTopicId());
        for(Praise praise:praises){
            if(praise.getUserId()==user.getUserId()){
                topic.setIsPraise(1);
                break;
            }
        }
        topic.setIsPraise(0);
    }
}
