package com.example.demo.web;
import com.example.demo.domain.User;
import com.example.demo.domain.result.ExceptionMsg;
import com.example.demo.domain.result.ResponseData;
import com.example.demo.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.utils.FileUtil;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName UploadController
 * @Description TODO
 * @Auther ydc
 * @Date 2019/1/12 19:08
 * @Version 1.0
 **/
@RestController
public class UploadController extends BaseController{
    private UserServiceImp userServiceImp;

    @Autowired
    public void setUserServiceImp(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }


    //处理文件上传
    @RequestMapping("/uploadHeadPortrait")
    public ResponseData uploadHeadPortrait(HttpServletRequest request,String dataUrl){
        try {
            System.out.println("C:\\Program Files\\apache-tomcat-9.0.13\\apache-tomcat-9.0.13\\webapps\\springboot\\WEB-INF\\classes\\static\\userImage");
            String filePath ="C:\\Program Files\\apache-tomcat-9.0.13\\apache-tomcat-9.0.13\\webapps\\springboot\\WEB-INF\\classes\\static\\userImage\\";
            //String filePath = "C:\\Users\\Administrator\\Documents\\GitHub\\forum\\forum\\src\\main\\resources\\static\\userImage\\";
            String fileName = UUID.randomUUID().toString() + ".png";//指定为png格式
            String image = dataUrl;
            String savePath = "userImage/"+fileName;
            String header = "data:image";
            String[] imageArr = image.split(",");
            if (imageArr[0].contains(header)) {
                image = imageArr[1];
                Base64.Decoder decoder = Base64.getDecoder();
                byte[] decodedBytes = decoder.decode(image);
                FileUtil.uploadFile(decodedBytes, filePath, fileName);
                System.out.println("保存结束");
                User user = getSessionUser(request);
                if(user==null){
                    return new ResponseData(ExceptionMsg.FAILED);
                }
                userServiceImp.updateUserPhoto(savePath,user.getUserId());//存储头像路径
                user.setPhoto(savePath);
                System.out.println(user);
                setSessionUser(request,user);
            }
            System.out.println("头像地址"+savePath);
            System.out.println("上传结束");
            return new ResponseData(ExceptionMsg.SUCCESS,savePath);
        }
        catch (Exception e){
            System.out.println("错误");
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping("/uploadBackground")
    public ResponseData uploadBackground(HttpServletRequest request,String dataUrl){
        try {
            System.out.println("11");
            String filePath ="C:\\Program Files\\apache-tomcat-9.0.13\\apache-tomcat-9.0.13\\webapps\\springboot\\WEB-INF\\classes\\static\\userBg\\";
            //String filePath = "C:\\Users\\Administrator\\Documents\\GitHub\\forum\\forum\\src\\main\\resources\\static\\userBg\\";
            String fileName = UUID.randomUUID().toString() + ".png";//指定为png格式
            String image = dataUrl;
            String savePath = "userBg/"+fileName;
            String header = "data:image";
            String[] imageArr = image.split(",");
            if (imageArr[0].contains(header)) {
                image = imageArr[1];
                Base64.Decoder decoder = Base64.getDecoder();
                byte[] decodedBytes = decoder.decode(image);
                FileUtil.uploadFile(decodedBytes, filePath, fileName);
                System.out.println("保存结束");
                User user = getSessionUser(request);
                if(user==null){
                    return new ResponseData(ExceptionMsg.FAILED);
                }
                userServiceImp.updateUserBg(savePath,user.getUserId());//存储头像路径
                user.setBackPicture(savePath);
                System.out.println(user);

                setSessionUser(request,user);
            }
            System.out.println("头像地址"+savePath);
            System.out.println("上传结束");
            return new ResponseData(ExceptionMsg.SUCCESS,savePath);
        }
        catch (Exception e){
            System.out.println("错误");
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping("uploadBackgroundPage")
    public ModelAndView uploadBackgroundPage(){
        ModelAndView mv =new ModelAndView();
        mv.setViewName("user/updateBackGround");
        return mv;
    }

}
