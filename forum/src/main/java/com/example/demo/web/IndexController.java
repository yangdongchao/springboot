package com.example.demo.web;

import com.example.demo.domain.*;
import com.example.demo.domain.result.ExceptionMsg;
import com.example.demo.domain.result.ResponseData;
import com.example.demo.service.FeedbackService;
import com.example.demo.service.FollowerService;
import com.example.demo.service.MailService;
import com.example.demo.service.PraiseService;
import com.example.demo.service.imp.BoardServiceImp;
import com.example.demo.service.imp.FollowerServiceImp;
import com.example.demo.service.imp.TopicServiceImp;
import com.example.demo.service.imp.UserServiceImp;
import jdk.internal.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName IndexController
 * @Description TODO
 * @Auther ydc
 * @Date 2019/1/15 10:50
 * @Version 1.0
 **/

@RestController
public class IndexController extends BaseController {


    private FollowerServiceImp followerServiceImp;

    private TopicServiceImp topicServiceImp;

    private UserServiceImp userServiceImp;

    private BoardServiceImp boardServiceImp;
    private PraiseService praiseService;

    private FeedbackService feedbackService;

    private MailService mailService;

    @Autowired
    public void setFollowerServiceImp(FollowerServiceImp followerServiceImp) {
        this.followerServiceImp = followerServiceImp;
    }

    @Autowired
    public void setUserServiceImp(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @Autowired
    public void setTopicServiceImp(TopicServiceImp topicServiceImp) {
        this.topicServiceImp = topicServiceImp;
    }

    @Autowired
    public void setBoardServiceImp(BoardServiceImp boardServiceImp) {
        this.boardServiceImp = boardServiceImp;
    }

    @Autowired
    public void setPraiseService(PraiseService praiseService) {
        this.praiseService = praiseService;
    }

    @Autowired
    public void setFeedbackService(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    /**
     * 映射到home页面
     * @return
     */
    @GetMapping("home")
    public ModelAndView uploadStatus() {
        System.out.println(1);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home");
        return mv;
    }

    /**
     * 个人主页，根据userId来加载不同的数据，fid目前没有用处
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("userIndex/{userId}/{fid}")
    public ModelAndView userIndex(HttpServletRequest request, @PathVariable("userId") int id) {
        User login = getSessionUser(request);
        User user = userServiceImp.getUserById(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("userIndex");
        mv.addObject("currentUser", user);
        List<Follower> fan = followerServiceImp.getAllFansById(user.getUserId());
        List<User> fans = new ArrayList<>();
        //加载粉丝
        for (Follower f : fan) {
            fans.add(userServiceImp.getUserById(f.getfId()));
        }
        List<Follower> followers = followerServiceImp.getAllMyFollowById(user.getUserId());
        ArrayList<User> follower = new ArrayList<>();
        //加载我关注的人
        for (Follower f : followers) {
            follower.add(userServiceImp.getUserById(f.getUserId()));
        }
        if (login.getUserId() != user.getUserId()) {
            mv.addObject("yes", followerServiceImp.findByUserIdAndFid(user.getUserId(), login.getUserId()) == null ? 1 : 0);
        } else {
            mv.addObject("yes", 3);
        }

        //加载发表的文章数量
        mv.addObject("topicNum", topicServiceImp.getAllTopicByUserId(user.getUserId()) == null ? 0 : topicServiceImp.getAllTopicByUserId(user.getUserId()).size());
        mv.addObject("fans", fans);
        mv.addObject("follower", follower);
        //通过mv传送给userIndex页面
        return mv;
    }

    /**
     * 映射到浏览话题页面，可以查看所有话题，分页显示
     * @param request
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("userContent")
    public ModelAndView userContent(HttpServletRequest request, @RequestParam(value = "page", defaultValue = "0") Integer page,
                                    @RequestParam(value = "size", defaultValue = "15") Integer size) {
        User user = getSessionUser(request);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/userContent");
        mv.addObject("user", user);
        Page<Topic> topics1 = topicServiceImp.getAllTopic(page, size);
        List<Topic> topics2 = new ArrayList<>();
        for (Topic topic : topics1) {
            topic.setUser(userServiceImp.getUserById(topic.getUserId()));
            topic.setBoard(boardServiceImp.findBoardById(topic.getBoardId()));
            topic.setUrl("/userReadDetail/" + topic.getTopicId());
            setIsPraise(topic, user);
            topics2.add(topic);
        }
        mv.addObject("topics", topics2);
        return mv;
    }

    @RequestMapping("userContent/more")
    public List<Topic> loadMore(HttpServletRequest request, @RequestParam(value = "page", defaultValue = "0") Integer page,
                                @RequestParam(value = "size", defaultValue = "15") Integer size) {
        page++;
        System.out.println(page);
        User user = getSessionUser(request);
        Page<Topic> topics = topicServiceImp.getAllTopic(page, size);
        List<Topic> topics1 = new ArrayList<>();
        for (Topic topic : topics) {
            topic.setUser(userServiceImp.getUserById(topic.getUserId()));
            topic.setBoard(boardServiceImp.findBoardById(topic.getBoardId()));
            topic.setUrl("/userReadDetail/" + topic.getTopicId());
            setIsPraise(topic, user);
            topics1.add(topic);
        }
        return topics1;
    }

    /**
     * 映射到Home的初始页面
     * @return
     */
    @RequestMapping("userHome")
    public ModelAndView userHome() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("firstPage");
        mv.addObject("time", new Date());
        return mv;
    }

    private void setIsPraise(Topic topic, User user) {
        List<Praise> praises = praiseService.getAllPraiseByTopicId(topic.getTopicId());
        for (Praise praise : praises) {
            if (praise.getUserId() == user.getUserId()) {
                topic.setIsPraise(1);
                break;
            }
        }
        topic.setIsPraise(0);
    }

    /**
     * 用户反馈
     * @param request
     * @return
     */
    @RequestMapping("feedback")
    public ModelAndView feedBack(HttpServletRequest request) {
        User user = getSessionUser(request);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.setViewName("user/feedback");
        return mv;
    }

    /**
     * 存储用户反馈信息，并已邮件的方式发送给超级管理员的邮箱
     * @param feedback
     * @return
     */
    @RequestMapping("feedback/save")
    public ResponseData feedBackSave(Feedback feedback) {
        try {
            User user = userServiceImp.getUserByName(feedback.getUser_name());
            feedback.setUser_id(user.getUserId());
            feedback.setCreateTime(new Date());
            feedbackService.save(feedback);
            mailService.sendMail("有新的反馈，请注意查收",feedback.getContent(),"15087581161@163.com");
            return new ResponseData(ExceptionMsg.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }
}
