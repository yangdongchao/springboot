package com.example.demo.web;

import com.example.demo.domain.Praise;
import com.example.demo.domain.Topic;
import com.example.demo.domain.User;
import com.example.demo.service.PraiseService;
import com.example.demo.service.imp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName lookController
 * @Description TODO
 * @Auther ydc
 * @Date 2019/2/1 9:41
 * @Version 1.0
 **/
@RestController
public class lookController extends BaseController {

    private TopicServiceImp topicServiceImp;
    private UserServiceImp userServiceImp;

    private BoardServiceImp boardServiceImp;
    private PraiseServiceImp praiseServiceImp;

    private PostServiceImp postServiceImp;

    @Autowired
    public void setTopicServiceImp(TopicServiceImp topicServiceImp) {
        this.topicServiceImp = topicServiceImp;
    }

    @Autowired
    public void setUserServiceImp(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @Autowired
    public void setPraiseServiceImp(PraiseServiceImp praiseServiceImp) {
        this.praiseServiceImp = praiseServiceImp;
    }


    @Autowired
    public void setBoardServiceImp(BoardServiceImp boardServiceImp) {
        this.boardServiceImp = boardServiceImp;
    }

    @Autowired
    public void setPostServiceImp(PostServiceImp postServiceImp) {
        this.postServiceImp = postServiceImp;
    }


    private PraiseService praiseService;

    @Autowired
    public void setPraiseService(PraiseService praiseService) {
        this.praiseService = praiseService;
    }

    @RequestMapping("look/{id}")
//这里的id加上了{}，表示这是一个占位符，前端可以传不同的数值进来
    public ModelAndView justLook(HttpServletRequest request, @PathVariable("id") int id,
                                 @RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "size", defaultValue = "15") Integer size) {
        //观察可以发现，我用@PathVariable("id") int id实现了接收传来的id值。@RequestParam(value = "page", defaultValue = "0")是关于分页的，
        // 关于分页的这里不讲，会有专门的专题来讲解。
        User user = getSessionUser(request);
        page += 1;
        int userId;
        if (user == null) {
            userId = 0;
        } else {
            userId = user.getUserId();
        }
        Page<Topic> topics = topicServiceImp.getTopicsByBoardId(page, size, id);
        List<Topic> topics1 = new ArrayList<>();
        for (Topic topic : topics) {
            topic.setUser(userServiceImp.getUserById(topic.getUserId()));
            topic.setUrl("/userReadDetail/"+topic.getTopicId());
            setIsPraise(topic,user);
            topics1.add(topic);
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("look");//设置返回的页面是look.html
        mv.addObject("collects", topics1);//将页面需要的参数传给前端页面
        mv.addObject("userId", userId);
        mv.addObject("type", "look");
        mv.addObject("functio", userServiceImp);
        mv.addObject("size", topics1.size());
        mv.addObject("what", id);
        return mv;
    }
    @RequestMapping("topic/lookAround/{id}")
    public List<Topic> lookMore(HttpServletRequest request, @RequestParam(value = "page", defaultValue = "0") Integer page,
                                @RequestParam(value = "size", defaultValue = "15") Integer size,@PathVariable("id") int id){
        page++;
        User user = getSessionUser(request);
        Page<Topic> topics = topicServiceImp.getTopicsByBoardId(page,size,id);
        List<Topic> topics1 = new ArrayList<>();
        for(Topic topic:topics){
            topic.setUser(userServiceImp.getUserById(topic.getUserId()));
            topic.setUrl("/userReadDetail/"+topic.getTopicId());
            setIsPraise(topic,user);
            topics1.add(topic);
        }
        return  topics1;
    }
    @RequestMapping("topic/lookAllMore")
    public List<Topic> lookAllMore(HttpServletRequest request, @RequestParam(value = "page", defaultValue = "0") Integer page,
                                   @RequestParam(value = "size", defaultValue = "15") Integer size){
        page++;
        User user =getSessionUser(request);
        Page<Topic> topics = topicServiceImp.getAllTopic(page,size);
        List<Topic> topics1 = new ArrayList<>();
        for(Topic topic:topics){
            topic.setUser(userServiceImp.getUserById(topic.getUserId()));
            topic.setUrl("/userReadDetail/"+topic.getTopicId());
            setIsPraise(topic,user);
            topics1.add(topic);
        }
        return  topics1;
    }

    @RequestMapping("lookAll")
    public ModelAndView lookAll(HttpServletRequest request,@RequestParam(value = "page", defaultValue = "0") Integer page,
                                @RequestParam(value = "size", defaultValue = "15") Integer size){

        User user = getSessionUser(request);
        page+=1;
        int userId;
        if(user==null)
        {
            userId=0;
        }
        else{
            userId=user.getUserId();
        }
        Page<Topic> topics = topicServiceImp.getAllTopic(page,size);
        List<Topic> topics1 = new ArrayList<>();
        for(Topic topic:topics){
            topic.setUser(userServiceImp.getUserById(topic.getUserId()));
            topic.setUrl("/userReadDetail/"+topic.getTopicId());
            setIsPraise(topic,user);
            topics1.add(topic);
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("look2");
        mv.addObject("collects",topics1);
        mv.addObject("userId",userId);
        mv.addObject("type","look");
        mv.addObject("functio",userServiceImp);
        mv.addObject("size",topics1.size());
        return  mv;
    }


    /**
     * 复杂度极大，需要优化
     * @param topic
     * @param user
     */
    private void setIsPraise(Topic topic,User user){
        if(user==null){
            topic.setIsPraise(0);
            return;
        }
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


