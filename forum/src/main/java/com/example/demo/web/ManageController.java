package com.example.demo.web;

import com.example.demo.domain.Post;
import com.example.demo.domain.Praise;
import com.example.demo.domain.Topic;
import com.example.demo.domain.User;
import com.example.demo.domain.result.ExceptionMsg;
import com.example.demo.domain.result.ResponseData;
import com.example.demo.service.BoardService;
import com.example.demo.service.PraiseService;
import com.example.demo.service.UserService;
import com.example.demo.service.imp.PostServiceImp;
import com.example.demo.service.imp.TopicServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.tensorflow.op.core.Mod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ManageController
 * @Description TODO
 * @Auther ydc
 * @Date 2019/2/15 22:13
 * @Version 1.0
 **/
@RestController
public class ManageController  extends BaseController{

    private UserService userService;

    private TopicServiceImp topicServiceImp;

    private PraiseService praiseService;

    private PostServiceImp postServiceImp;

    private BoardService boardService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setTopicServiceImp(TopicServiceImp topicServiceImp) {
        this.topicServiceImp = topicServiceImp;
    }

    @Autowired
    public void setPraiseService(PraiseService praiseService) {
        this.praiseService = praiseService;
    }

    @Autowired
    public void setPostServiceImp(PostServiceImp postServiceImp) {
        this.postServiceImp = postServiceImp;
    }

    @Autowired
    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    @RequestMapping("userManage")
    public ModelAndView userManage(HttpServletRequest request, @RequestParam(value = "page", defaultValue = "0") Integer page,
                                   @RequestParam(value = "size", defaultValue = "25") Integer size){

        if(page==0) page+=1;
        ModelAndView mv = new ModelAndView();
        mv.setViewName("manage/user_manage");
        Page<User> userPage = userService.getUserPage(page,size);
        List<User> users = new ArrayList<>();
        mv.addObject("cuser",getSessionUser(request));
        for(User user: userPage){
            if(user.getUser_type()==0){
                user.setTypeName("封禁用户");
            }
            else if(user.getUser_type()==1){
                user.setTypeName("普通用户");
            }
            else if(user.getUser_type()==2){
                user.setTypeName("普通管理员");
            }
            else{
                user.setTypeName("超级管理员");
            }
            users.add(user);
        }
        mv.addObject("users",users);
        return mv;
    }


    /**
     * 分页显示用户
     * @param request
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("userManage/lookAllMore")
    public List<User> userManageLookAll(HttpServletRequest request, @RequestParam(value = "page", defaultValue = "0") Integer page,
                                   @RequestParam(value = "size", defaultValue = "25") Integer size){

        //page+=1;
        Page<User> userPage = userService.getUserPage(page,size);
        List<User> users = new ArrayList<>();
        for(User user: userPage){
            if(user.getUser_type()==0){
                user.setTypeName("封禁用户");
            }
            else if(user.getUser_type()==1){
                user.setTypeName("普通用户");
            }
            else if(user.getUser_type()==2){
                user.setTypeName("普通管理员");
            }
            else{
                user.setTypeName("超级管理员");
            }
            users.add(user);
        }
        return users;
    }

    /*
  管理员的一些接口
  by xiuwenli
   */
    @RequestMapping("administrator")
    public ModelAndView manageAll(HttpServletRequest request,
                                  @RequestParam(value = "page", defaultValue = "0") Integer page,
                                  @RequestParam(value = "size", defaultValue = "15") Integer size){
        User user = getSessionUser(request);
        System.out.println(page);
        //page+=1;
//        if(page==0){
//            page+=1;
//        }
        int userId;
        if(user==null) {
            userId=0;
        }
        else{
            userId=user.getUserId();
        }
        Page<Topic> topics = topicServiceImp.getAllTopic(page,size);
        List<Topic> topics1 = new ArrayList<>();
        for(Topic topic:topics){
            topic.setUser(userService.getUserById(topic.getUserId()));
            topic.setUrl("/userReadDetail/"+topic.getTopicId());
            setIsPraise(topic,user);
            topics1.add(topic);
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("manage/topic_manage");
        mv.addObject("collects",topics1);
        mv.addObject("userId",userId);
        mv.addObject("type","look");
        mv.addObject("functio",userService);
        mv.addObject("size",topics1.size());
        return  mv;
    }

    @RequestMapping("topicManage/search/{name}")
    public ModelAndView topicManageSeach(@PathVariable("name") String name,@RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "25") Integer size){

        ModelAndView mv = new ModelAndView();
        Page<Topic> pages = topicServiceImp.getTopicBySearch(page,size,name);
        mv.setViewName("manage/topic_manageSearch");
        List<Topic> topics2 = new ArrayList<>();
        for(Topic topic:pages) {
            topic.setUser(userService.getUserById(topic.getUserId()));
            topic.setUrl("/userReadDetail/"+topic.getTopicId());
            topics2.add(topic);
        }
        mv.addObject("collects",topics2);
        return  mv;
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

    /*
    管理员的一些接口
     */
    @RequestMapping("deleteComment/{topicId}")
    public ModelAndView manageDetail(@PathVariable("topicId")int topicId){
        Topic topic = topicServiceImp.getTopicByTopicId(topicId);
        ModelAndView mv = new ModelAndView();
        List<Post> posts = postServiceImp.findPostByTopicId(topicId);
        mv.setViewName("manage/deleteComment");
        mv.addObject("topic",topic);
        mv.addObject("posts",posts);
        mv.addObject("board",boardService.findBoardById(topic.getBoardId()));
       mv.addObject("user",userService.getUserById(topic.getUserId()));
        return mv;
    }
    @RequestMapping("userManege/lock/{userId}")
    public ResponseData userlock(@PathVariable("userId") int userId){
           try{
               userService.resetUserType(0,userId);
               userService.restUserLock(User.user_locked,userId);
               return  new ResponseData(ExceptionMsg.SUCCESS);
           }
           catch (Exception e){
               e.printStackTrace();
               return new ResponseData(ExceptionMsg.FAILED);
           }
    }
    @RequestMapping("userManege/unlock/{userId}")
    public ResponseData userUnlock(@PathVariable("userId") int userId){
        try{
            userService.resetUserType(1,userId);
            userService.restUserLock(User.user_unlocked,userId);
            return  new ResponseData(ExceptionMsg.SUCCESS);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping("userManege/manager/{userId}")
    public ResponseData userManeger(HttpServletRequest request, @PathVariable("userId") int userId){
        User user = getSessionUser(request);
        if(user.getUser_type()!=3){
            return new ResponseData(ExceptionMsg.FAILED);
        }
        try{
            userService.resetUserType(2,userId);
            //userService.restUserLock(User.user_unlocked,userId);
            return  new ResponseData(ExceptionMsg.SUCCESS);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping("userManege/unmanager/{userId}")
    public ResponseData userUnManeger(HttpServletRequest request, @PathVariable("userId") int userId){
        User user = getSessionUser(request);
        if(user.getUser_type()!=3){
            return new ResponseData(ExceptionMsg.FAILED);
        }
        try{
            userService.resetUserType(1,userId);
            //userService.restUserLock(User.user_unlocked,userId);
            return  new ResponseData(ExceptionMsg.SUCCESS);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }


    @RequestMapping("userManage/search/{key}")
    public ModelAndView userManageSeach(@PathVariable("key") String name,@RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "25") Integer size){

        if(page==0){
            page+=1;
        }
        ModelAndView mv = new ModelAndView();
        List<User> userPage = userService.getUsersByUserName(name);
        System.out.println(userPage);
        List<User> users = new ArrayList<>();
        for(User user: userPage){
            if(user.getUser_type()==0){
                user.setTypeName("封禁用户");
            }
            else if(user.getUser_type()==1){
                user.setTypeName("普通用户");
            }
            else if(user.getUser_type()==2){
                user.setTypeName("普通管理员");
            }
            else{
                user.setTypeName("超级管理员");
            }
            users.add(user);
        }
        mv.setViewName("manage/user_manageSearch");
        mv.addObject("users",users);
        return  mv;
    }

}
