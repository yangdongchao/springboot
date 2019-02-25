package com.example.demo.web;

import com.example.demo.domain.User;
import com.example.demo.domain.bean.SetPassword;
import com.example.demo.domain.result.ExceptionMsg;
import com.example.demo.domain.result.ResponseData;
import com.example.demo.service.imp.UserServiceImp;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static jdk.nashorn.internal.objects.NativeError.printStackTrace;

/**
 * @ClassName UserSetController
 * @Description 用户信息设置
 * @Auther ydc
 * @Date 2019/1/11 18:19
 * @Version 1.0
 **/
@RestController
public class UserSetController  extends BaseController{
    private UserServiceImp userServiceImp;

    @Autowired
    public void setUserServiceImp(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @RequestMapping("userSetPassword")
    public ResponseData userInformationSet(HttpServletRequest request, SetPassword setPassword){
        User user = getSessionUser(request);
        try {
            if (user.getPassword().equals(setPassword.getOldPassword())) {
                user.setPassword(setPassword.getNewPassword());
                userServiceImp.resetPassword(setPassword.getNewPassword(), user.getUserId());
                //mv.addObject("success","修改密码成功");
                return new ResponseData(ExceptionMsg.SUCCESS);
            } else {
                return new ResponseData(ExceptionMsg.PassWordError);
            }
        }
        catch (Exception e){
            printStackTrace(e);
            return new ResponseData(ExceptionMsg.FAILED);
        }

    }

    @RequestMapping("userSetSignature")
    public ResponseData userSignatureSet(HttpServletRequest request,String introduction){
        User user = getSessionUser(request);
        try{
           // System.out.println(111);
            user.setSignature(introduction);
            userServiceImp.updateUserSignature(introduction,user.getUserId());
        }
        catch (Exception e){
            printStackTrace(e);
            return new ResponseData(ExceptionMsg.FAILED);
        }
        return new ResponseData(ExceptionMsg.SUCCESS);
    }

    @RequestMapping("updateUserName")
    public ResponseData userNameSet(HttpServletRequest request,String userName){
        User user = getSessionUser(request);
        try{
            user.setUserName(userName);
            userServiceImp.updateUserName(userName,user.getUserId());
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        catch (Exception e){
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping("uploadHeadPortraitPage")
    public ModelAndView  updatePhotoPage(){
        System.out.println("page");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/user/updatePhoto");
        return mv;
    }


}
