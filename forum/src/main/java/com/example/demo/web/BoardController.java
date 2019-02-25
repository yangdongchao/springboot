package com.example.demo.web;

import com.example.demo.dao.TopicDao;
import com.example.demo.domain.Board;
import com.example.demo.domain.Topic;
import com.example.demo.service.imp.BoardServiceImp;
import com.example.demo.service.imp.TopicServiceImp;
import org.hibernate.dialect.pagination.TopLimitHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @ClassName BoardController
 * @Description TODO
 * @Auther ydc
 * @Date 2019/1/12 18:51
 * @Version 1.0
 **/
@RestController
public class BoardController extends BaseController {
    private BoardServiceImp boardServiceImp;

    private TopicServiceImp topicServiceImp;


    private TopicDao topicDao;

    @Autowired
    public void setTopicDao(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    @Autowired
    public void setTopicServiceImp(TopicServiceImp topicServiceImp) {
        this.topicServiceImp = topicServiceImp;
    }

    @Autowired
    public void setBoardServiceImp(BoardServiceImp boardServiceImp) {
        this.boardServiceImp = boardServiceImp;
    }


    /**
     * 映射index页面，同时将board信息传给index页面
     * @return
     */
    @RequestMapping("index")
    public ModelAndView index(){
            ModelAndView mv  = new ModelAndView();
            mv.setViewName("index");
            Page<Board> boards = boardServiceImp.getBoardPage(1,10);
            mv.addObject("boards",boards);
            return  mv;
    }

    /**
     * 根据版块名字获得里面的所有帖子
     * @param name
     * @return
     */
    @RequestMapping("getTopic/{name}")
    public List<Topic> getTopicByBoardName(@Param("name") String name){
        Board board = boardServiceImp.getBoardByName(name);
        return topicDao.findByBoardId(board.getBoardId());
    }

}
