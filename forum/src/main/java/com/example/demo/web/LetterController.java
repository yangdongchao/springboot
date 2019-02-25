package com.example.demo.web;

import com.example.demo.domain.Letter;
import com.example.demo.domain.User;
import com.example.demo.domain.enums.LetterType;
import com.example.demo.domain.result.ExceptionMsg;
import com.example.demo.domain.result.Response;
import com.example.demo.domain.result.ResponseData;
import com.example.demo.service.LetterService;
import com.example.demo.service.UserService;
import com.example.demo.service.imp.LetterServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName LetterController
 * @Description 私信
 * @Auther ydc
 * @Date 2019/2/4 13:55
 * @Version 1.0
 **/
@RestController
public class LetterController extends BaseController {

    private LetterService letterService;

    private UserService userService;

    @Autowired
    public void setLetterService(LetterService letterService) {
        this.letterService = letterService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    /**
     * 发送私信
     * @param request
     * @param letter
     * @return
     */
    @RequestMapping("letter/sendLetter")
    public ResponseData sendLetter(HttpServletRequest request,Letter letter){
        try {
            System.out.println(letter.getReceiverId());
            User user =getSessionUser(request);
            letter.setSenderId(user.getUserId());
            letter.setCreateTime(new Date());
            //需要判断是直接私信还是回复
            if ("original".equals(letter.getSendType())) {
                letter.setType(LetterType.ORIGINAL);
            } else {
                letter.setType(LetterType.REPLY);
            }
            System.out.println(letter);
            letterService.save(letter);
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        catch (Exception e){
            e.printStackTrace();
            return  new ResponseData(ExceptionMsg.FAILED);
        }
    }

    /**
     * 分页显示我的私信界面
     * @param request
     * @param page
     * @param size
     * @return
     */

    @RequestMapping("letter/me")
    public ModelAndView letterMe(HttpServletRequest request,@RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "size", defaultValue = "50") Integer size){
        User user = getSessionUser(request);
        Page<Letter> page1 = letterService.getLettersByReceiverId(page,size,user.getUserId());
        List<Letter> letterList = new ArrayList<>();
        for(Letter letter:page1){
            letter.setSender(userService.getUserById(letter.getSenderId()));
            if(letter.getType()==LetterType.ORIGINAL){
                letter.setSendType("ORIGINAL");
            }
            else{
                letter.setSendType("REPLY");
            }
            letterList.add(letter);
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("letterList",letterList);
        mv.setViewName("notice/letterme");
        return mv;
    }
}
