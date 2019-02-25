package com.example.demo.web;

import com.example.demo.domain.*;
import com.example.demo.domain.result.ExceptionMsg;
import com.example.demo.domain.result.Response;
import com.example.demo.domain.result.ResponseData;
import com.example.demo.service.LoveService;
import com.example.demo.service.imp.*;
import com.example.demo.tf.TensorflowModel;
import groovy.transform.Undefined;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName TopicController
 * @Description TODO
 * @Auther ydc
 * @Date 2019/1/25 14:39
 * @Version 1.0
 **/
@RestController
public class TopicController extends BaseController {
    private TopicServiceImp topicServiceImp;
    private UserServiceImp userServiceImp;

    private BoardServiceImp boardServiceImp;
    private PraiseServiceImp praiseServiceImp;

    private PostServiceImp postServiceImp;

    private LoveService loveService;

    private TensorflowModel tensorflowModel;

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

    @Autowired
    public void setLoveService(LoveService loveService) {
        this.loveService = loveService;
    }

    @Autowired
    public void setTensorflowModel(TensorflowModel tensorflowModel) {
        this.tensorflowModel = tensorflowModel;
    }

    @RequestMapping("topic/like/{topicId}")
    //映射点赞
    public ResponseData changePraise(HttpServletRequest request, @PathVariable("topicId") int topicId){
        System.out.println(111);
        try {
            User user = getSessionUser(request);
            praiseServiceImp.like(topicId,user.getUserId());//完成修改
        }
        catch (Exception e){
            System.err.println("有错误");
        }
        return new ResponseData(ExceptionMsg.SUCCESS);
    }

    @RequestMapping("userTopicSave/{userId}")
    //写文章后保存，由于使用了tensorflow导致速度有的忙
    public ResponseData userTopicSave(HttpServletRequest request,@PathVariable("userId")int userId,String html,String title,String content,String boardId){
       // System.out.println(tensorflowModel.cnn("体育面对威斯布鲁克的雷霆，独行侠却不会害怕。本赛季首次交锋，独行侠就以111-96取得胜利场投上一场打太阳，威斯布鲁克几乎三双，拿下了40分、12个篮板和8次助攻。今天他却状态低迷，不断“打铁”。雷霆还得靠乔治。他们在首节中段打出一波10-0，以18-9取得优势，乔治在这波攻击中独得5分。巴恩斯和东契奇相继还以颜色，独行侠也打出一波9-0，双方战成21-21。两队开始拉锯战，首节过后，雷霆以28-27领先1分。雷霆替补发挥出色，第四节开始后，他们打出一波17-4，在本节将过半时，帕特森空中接力扣篮，他们以90-88反超。双方派上主力后，雷霆近3分钟一分未得，而东契奇命中三分，独行侠连得5分，又成为领先的一方。乔治三罚三中，然后又投中三分，雷霆以96-95再度超出。双方进入拼刺刀阶段，在最后1秒才分出高下。比赛还有24.8秒时，史密斯杀了个“回马枪”，突然上篮得手，独行侠连得5分，以104-103反超。独行侠关键时刻成功防守，乔治急停跳投不中，时间只剩下1.6秒。乔丹两罚一中后，独行侠以105-103领先。全场手感冰凉的威斯布鲁克执行最后一攻，未能命中，雷霆功亏一篑。"));
        try {
            int bd = Integer.parseInt(boardId);
            Topic topic = new Topic(bd, userId, title, new Date(), new Date(), 1, 0, 0, 1, "PRIVATE", "/userRead/", "/img/favicon.png", html, 0, content);
            System.out.println(html);
            System.out.println(content);
            topic.setLabel(tensorflowModel.cnn(html));
            topicServiceImp.TopicSave(topic);
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping("userWritePage")
    //映射到写文文章页面
    public ModelAndView userWriting(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/write");
        return mv;
    }

    @RequestMapping("userRead/{topicId}")
    //阅读文章
    public ModelAndView userReading(@PathVariable("topicId")int topicId){
        Topic topic = topicServiceImp.getTopicByTopicId(topicId);
        ModelAndView mv = new ModelAndView();
        mv.addObject("topic",topic);
        mv.addObject("user",userServiceImp.getUserById(topic.getUserId()));
        mv.setViewName("user/read");
        return mv;
    }

    @RequestMapping("userReadDetail/{topicId}")
    //阅读文章
    public ModelAndView userReadDetail(@PathVariable("topicId")int topicId){
        Topic topic = topicServiceImp.getTopicByTopicId(topicId);
        ModelAndView mv = new ModelAndView();
        List<Post> posts = postServiceImp.findPostByTopicId(topicId);
        mv.addObject("topic",topic);
        mv.addObject("posts",posts);
       // System.out.println(posts);
        mv.addObject("board",boardServiceImp.findBoardById(topic.getBoardId()));
        mv.addObject("user",userServiceImp.getUserById(topic.getUserId()));
        mv.setViewName("user/readDetail");
        return mv;
    }

    @RequestMapping("userAmend/{topicId}")
    //再次修改编辑文章
    public ModelAndView userAmend(@PathVariable("topicId")int topicId){
        Topic topic = topicServiceImp.getTopicByTopicId(topicId);
        List<Board> boards= boardServiceImp.findAllBoard();
        ModelAndView mv = new ModelAndView();
        mv.addObject("topic",topic);
        mv.addObject("boards",boards);
        mv.setViewName("user/amend");
        return  mv;
    }

    @RequestMapping("userTopicAmend/{topicId}")
    public ResponseData userTopicAmend(@PathVariable("topicId")int topicId,String html,String content,String title,String boardId){
        try {
            int bd = Integer.parseInt(boardId);
            topicServiceImp.AmendTopic(bd,html,content,title,topicId);
            return  new ResponseData(ExceptionMsg.SUCCESS);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }
    @RequestMapping("search/{key}")
    //根据关键字查询
    public ModelAndView search(@PathVariable("key") String key,HttpServletRequest request,@RequestParam(value = "page", defaultValue = "0") Integer page,
                               @RequestParam(value = "size", defaultValue = "25") Integer size){
        Page<Topic> pages = topicServiceImp.getTopicBySearch(page,size,key);
        User user = getSessionUser(request);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/search");
        mv.addObject("user",user);
        List<Topic> topics2 = new ArrayList<>();
        for(Topic topic:pages) {
            topic.setUser(userServiceImp.getUserById(topic.getUserId()));
            topic.setBoard(boardServiceImp.findBoardById(topic.getBoardId()));
            topic.setUrl("/userReadDetail/"+topic.getTopicId());
            topics2.add(topic);
        }
        mv.addObject("topics",topics2);
        return  mv;
    }

    @RequestMapping("topic/delete/{topicId}")
    //删除Topic
    public ResponseData topicDelete(@PathVariable("topicId")int topicId){
        try{

            /**
             * 必须先删除相关引用topic的元素
             */
            int de=topicId;
            praiseServiceImp.deleteAllByTopicId(topicId);
            loveService.deleteAllByTopciId(topicId);
            //praiseServiceImp.deleteAllByTopicId(topicId);
            postServiceImp.deleteAllByTopicId(topicId);
            topicServiceImp.deleteTopicByTopicId(de);
            System.out.println(de);
            //topicServiceImp.deleteTopicByTopicId(topicId);
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping("myArticle")
    //我的文章
    public ModelAndView userArticle(HttpServletRequest request,@RequestParam(value = "page", defaultValue = "0") Integer page,
                                    @RequestParam(value = "size", defaultValue = "15") Integer size){
        User user = getSessionUser(request);
        ModelAndView mv = new ModelAndView();
        if(user==null){
            mv.setViewName("error");
            return mv;
        }
       // ModelAndView mv = new ModelAndView();
        mv.setViewName("user/myArticle");
        mv.addObject("user",user);
        Page<Topic> topics1 = topicServiceImp.getAllPageTopicByUserId(page,size,user.getUserId());
        List<Topic> topics2 = new ArrayList<>();
        for(Topic topic:topics1) {
            topic.setUser(userServiceImp.getUserById(topic.getUserId()));
            topic.setBoard(boardServiceImp.findBoardById(topic.getBoardId()));
            topic.setUrl("/userReadDetail/"+topic.getTopicId());
            setIsPraise(topic,user);
            topics2.add(topic);
        }
        mv.addObject("topics",topics2);
        return  mv;
    }

    @RequestMapping("myArticle/more")
    //分页加载我的文章，每页15条数据
    public List<Topic> loadMoreArticle(HttpServletRequest request, @RequestParam(value = "page", defaultValue = "0") Integer page,
                                @RequestParam(value = "size", defaultValue = "15") Integer size){
        page++;
        System.out.println(page);
        User user = getSessionUser(request);
        if(user==null){
            return null;
        }
        Page<Topic> topics = topicServiceImp.getAllPageTopicByUserId(page,size,user.getUserId());
        List<Topic> topics1 = new ArrayList<>();
        for(Topic topic:topics){
            topic.setUser(userServiceImp.getUserById(topic.getUserId()));
            topic.setBoard(boardServiceImp.findBoardById(topic.getBoardId()));
            topic.setUrl("/userReadDetail/"+topic.getTopicId());
            setIsPraise(topic,user);
            topics1.add(topic);
        }
        return  topics1;
    }
    private void setIsPraise(Topic topic,User user){
        List<Praise> praises = praiseServiceImp.getAllPraiseByTopicId(topic.getTopicId());
        for(Praise praise:praises){
            if(praise.getUserId()==user.getUserId()){
                topic.setIsPraise(1);
                break;
            }
        }
        topic.setIsPraise(0);
    }

    @RequestMapping("lookRecord/save/{topicId}")
    //文章浏览次数更新
    public ResponseData lookRecord(@PathVariable("topicId") int topicId){
        try{
            Topic topic = topicServiceImp.getTopicByTopicId(topicId);
            topicServiceImp.updateTopicViewByTopicId(topic.getTopicViews()+1,topicId);
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }
}
