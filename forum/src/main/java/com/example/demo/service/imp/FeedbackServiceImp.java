package com.example.demo.service.imp;

import com.example.demo.dao.FeedbackDao;
import com.example.demo.domain.Feedback;
import com.example.demo.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName FeedbackServiceImp
 * @Description TODO
 * @Auther ydc
 * @Date 2019/2/10 16:28
 * @Version 1.0
 **/
@Service
@Transactional
public class FeedbackServiceImp implements FeedbackService {

    private FeedbackDao feedbackDao;

    @Autowired
    public void setFeedbackDao(FeedbackDao feedbackDao) {
        this.feedbackDao = feedbackDao;
    }


    @Override
    public void save(Feedback feedback) {
        feedbackDao.save(feedback);
    }
}
