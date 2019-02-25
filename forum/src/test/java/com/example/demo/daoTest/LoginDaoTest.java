package com.example.demo.daoTest;

import com.example.demo.dao.*;
import com.example.demo.domain.*;
import com.example.demo.service.LoginLogService;
import com.example.demo.service.imp.BoardServiceImp;
import com.example.demo.service.imp.LoginLogServiceImp;
import com.example.demo.service.imp.TopicServiceImp;
import com.example.demo.service.imp.UserServiceImp;
import org.assertj.core.error.ShouldBeAfterYear;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @ClassName LoginDaoTest
 * @Description TODO
 * @Auther ydc
 * @Date 2019/1/8 10:18
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginDaoTest {

    @Autowired
    private TopicServiceImp topicServiceImp;


    @Autowired
    private BoardServiceImp boardServiceImp;

    @Autowired
    private UserServiceImp userServiceImp;

    @Autowired
    private LoginLogServiceImp loginLogServiceImp;

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private LoveDao loveDao;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private  UserDao userDao;

    @Autowired
    private PraiseDao praiseDao;

    public void addLog(){
       // User user = userDao.findByUserId(2);
       // System.out.println(user);
        PageRequest pageable = new PageRequest(0,10,Sort.Direction.DESC,"userId");
        //Assert.assertNull(pageable);
        System.out.println(pageable);
        System.out.println("test");
       User user2 =new User("kdwww","123",12,"kk","we",1,1,"dff","222",new Date());
       // User user2 =new User("dwwwtw","123",12,"kk","we",1,1,"dff","222",new Date());
       //userDao.save(user2);
       // userDao.save(user2);
        //Page<User> page = userDao.findByUserNameLike("%dw%",pageable);
       // System.out.println(page.getTotalElements());
        //System.out.println(page.getTotalPages());
        //for(User user : page.getContent()){
          //  System.out.println(user.toString());
        //}
        //BackGround backGround = new BackGround("123","456");
        //backGroundDao.save(backGround);
        //Topic topic = new Topic(1,2,"yes",new Date(),new Date(),22,22,1,1);
       // Post post = new Post(1,"yes",new Date(),2,"he did it",2);
        //topicDao.save(topic);
      // postDao.save(post);
       // int id= user.getUserId();
    }

    @Test
    @Transactional
    public void updateTopic(){

        //System.out.println(praiseDao.queryUserId(82));
    }
}
