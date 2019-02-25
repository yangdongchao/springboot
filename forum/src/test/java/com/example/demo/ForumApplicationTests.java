package com.example.demo;

import com.example.demo.dao.PraiseDao;
import com.example.demo.dao.TopicDao;
import com.example.demo.domain.Topic;
import com.example.demo.domain.User;
import com.example.demo.service.imp.BoardServiceImp;
import com.example.demo.service.imp.LoginLogServiceImp;
import com.example.demo.service.imp.TopicServiceImp;
import com.example.demo.service.imp.UserServiceImp;
import com.example.demo.tf.TensorflowModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ForumApplicationTests {

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
    private PraiseDao praiseDao;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private TensorflowModel tensorflowModel;


    @Test
    @Transactional
    public void contextLoads() {
        System.out.println(userServiceImp.getUserPage(1,15));
//        System.out.println(1);
//        List<User> page =userServiceImp.getUsersByUserName("ydc");
//        //System.out.println(page.getSize());
//        for(User user:page){
//            System.out.println(user);
//        }
//        try {
//            File path = new File(ResourceUtils.getURL("classpath:").getPath());
//            File upload = new File(path.getAbsolutePath(),"static/userImage/");
//            if(!upload.exists()) upload.mkdirs();
//            System.out.println("upload url:"+upload.getAbsolutePath());
//            System.out.println(ClassUtils.getDefaultClassLoader().getResource("").getPath());
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        Page<Topic> topicPage = topicServiceImp.getTopicBySearch(1,20,"2");
////        for(Topic topic:topicPage){
////            System.out.println(topic);
////        }
       // System.out.println(praiseDao.findByTopicId(82));
        //topicServiceImp.deleteTopicByTopicId(18);
        //System.out.println(tensorflowModel.cnn("体育面对威斯布鲁克的雷霆，独行侠却不会害怕。本赛季首次交锋，独行侠就以111-96取得胜利场投上一场打太阳，威斯布鲁克几乎三双，拿下了40分、12个篮板和8次助攻。今天他却状态低迷，不断“打铁”。雷霆还得靠乔治。他们在首节中段打出一波10-0，以18-9取得优势，乔治在这波攻击中独得5分。巴恩斯和东契奇相继还以颜色，独行侠也打出一波9-0，双方战成21-21。两队开始拉锯战，首节过后，雷霆以28-27领先1分。雷霆替补发挥出色，第四节开始后，他们打出一波17-4，在本节将过半时，帕特森空中接力扣篮，他们以90-88反超。双方派上主力后，雷霆近3分钟一分未得，而东契奇命中三分，独行侠连得5分，又成为领先的一方。乔治三罚三中，然后又投中三分，雷霆以96-95再度超出。双方进入拼刺刀阶段，在最后1秒才分出高下。比赛还有24.8秒时，史密斯杀了个“回马枪”，突然上篮得手，独行侠连得5分，以104-103反超。独行侠关键时刻成功防守，乔治急停跳投不中，时间只剩下1.6秒。乔丹两罚一中后，独行侠以105-103领先。全场手感冰凉的威斯布鲁克执行最后一攻，未能命中，雷霆功亏一篑。"));
}

}

