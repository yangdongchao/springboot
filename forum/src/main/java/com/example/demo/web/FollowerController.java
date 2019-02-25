package com.example.demo.web;

import com.example.demo.domain.Follower;
import com.example.demo.domain.User;
import com.example.demo.domain.result.ExceptionMsg;
import com.example.demo.domain.result.ResponseData;
import com.example.demo.service.FollowerService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @ClassName FollowerController
 * @Description 粉丝/关注
 * @Auther ydc
 * @Date 2019/2/4 16:49
 * @Version 1.0
 **/
@RestController
public class FollowerController extends BaseController {
    private FollowerService followerService;

    private UserService userService;

    @Autowired
    public void setFollowerService(FollowerService followerService) {
        this.followerService = followerService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 粉丝加入
     * @param request
     * @param followerId
     * @return
     */
    @RequestMapping("follower/add")
    public ResponseData followerAdd(HttpServletRequest request,int followerId){
        try {
            User user = getSessionUser(request);
            Follower follower = new Follower(followerId, user.getUserId(), new Date());
            followerService.save(follower);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseData(ExceptionMsg.FAILED);
        }
        return new ResponseData(ExceptionMsg.SUCCESS);
    }

    /**
     * 取消关注
     * @param request
     * @param followerId
     * @return
     */
    @RequestMapping("follower/delete")
    public ResponseData followerDelete(HttpServletRequest request,int followerId){
        try{
            User user = getSessionUser(request);
            //Follower follower = new Follower(followerId, user.getUserId(), new Date());
            if(followerService.findByUserIdAndFid(followerId,user.getUserId())==null){
                return new ResponseData(ExceptionMsg.FAILED);
            }
            followerService.delete(user.getUserId(),followerId);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseData(ExceptionMsg.FAILED);
        }
        return new ResponseData(ExceptionMsg.SUCCESS);
    }
}
