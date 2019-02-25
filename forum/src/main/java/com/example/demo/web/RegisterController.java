package com.example.demo.web;

import com.example.demo.domain.User;
import com.example.demo.domain.result.ExceptionMsg;
import com.example.demo.domain.result.ResponseData;
import com.example.demo.service.BoardService;
import com.example.demo.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Action;
import java.util.Date;

/**
 * @ClassName RegisterController
 * @Description TODO
 * @Auther ydc
 * @Date 2019/1/11 15:30
 * @Version 1.0
 **/
@RestController
public class RegisterController  extends  BaseController{
    private UserServiceImp userServiceImp;
    private BoardService boardService;
    @Autowired
    public void setUserServiceImp(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @Autowired
    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    @RequestMapping("register")
    public ResponseData register(HttpServletRequest request, User user){
        User dbUser = userServiceImp.getUserByName(user.getUserName());

        if(dbUser!=null){
           return new ResponseData(ExceptionMsg.UserNameUsed);
        }
        else if(userServiceImp.getUserByEmail(user.getEmail())!=null){
            return new ResponseData(ExceptionMsg.EmailNotRegister);
        }
        else{
            user.setLastIp(request.getRemoteAddr());
            user.setLastVisit(new Date());
            user.setCredits(5);
            user.setUser_type(1);
            user.setLocked(User.user_unlocked);
            user.setPhoto("/img/code.jpg");//先设定一个默认的头像
            //user.setGrade(" ");
            userServiceImp.saveUser(user);
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
    }

    @RequestMapping("registerPage")
    public ModelAndView getRegisterPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("register");
        mv.addObject("boards",boardService.findAllBoard());
        return mv;
    }

    @RequestMapping("goLogin")
    public ResponseData goLogin(){
       return new ResponseData(ExceptionMsg.SUCCESS,"/login");
    }

}
