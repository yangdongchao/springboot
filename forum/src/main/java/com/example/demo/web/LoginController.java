package com.example.demo.web;

import com.example.demo.cons.Constantion;
import com.example.demo.domain.User;
import com.example.demo.domain.result.ExceptionMsg;
import com.example.demo.domain.result.ResponseData;
import com.example.demo.service.MailService;
import com.example.demo.service.imp.UserServiceImp;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.sql.Timestamp;
/**
 * @ClassName LoginController
 * @Description TODO
 * @Auther ydc
 * @Date 2019/1/10 21:19
 * @Version 1.0
 **/
@RestController
public class LoginController extends BaseController {

    private UserServiceImp userServiceImp;

    private MailService mailService;

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    @Autowired
    public void setUserServiceImp(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @RequestMapping("login")
    public ModelAndView goLogin(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping("loginPage")
//RequestMapping是一种映射，刚刚我们前端提交的地方是"/loginPage"，也就是说通过@RequestMapping,前端的信息将来到这个函数里面。
    public ResponseData login(HttpServletRequest request, User user) {
        //直接在这里讲一下接收前端传来的数据的规则，因为前端我们定义了userName和password来存储信息，所以我们后端只要定义相同的变量名称就可以实现接收了。
        // 而我们的user实体类刚好有userName和password，所以直接放一个user对象即可（千万注意，命名一定要和前端完全一致），如果你不想用user去接收，
        // 那你就直接用(String userName,String password)去接收。
        try {
            User dbUser = userServiceImp.getUserByName(user.getUserName());
            //先查找有没有这个用户
            System.out.println(dbUser);
            if (dbUser == null) {
                System.out.println("1");
                //没有这个用户，返回错误信息
                return new ResponseData(ExceptionMsg.LoginNameNotExists);
            } else if (!dbUser.getPassword().equals(user.getPassword())) {
                //密码不对
                System.out.println("2");
                return new ResponseData(ExceptionMsg.LoginNameOrPassWordError);
            } else if (dbUser.getLocked() == User.user_locked) {
                //这个用户被管理员锁定了，暂时不允许登录
                System.out.println("'3");
                return new ResponseData(ExceptionMsg.Locked);
            } else {
                //成功,保存登录信息到数据库
                System.out.println("'4");
                dbUser.setLastIp(request.getRemoteAddr());
                dbUser.setLastVisit(new Date());
                //dbUser.setUser_type(1);
                userServiceImp.loginSuccess(dbUser);
                setSessionUser(request, dbUser);
                String toUrl = (String)
                        request.getSession().getAttribute(Constantion.LOGIN_TO_URL);//记忆化历史浏览记录,这个主意是解决用户之前在浏览一个页面，后来再次登录的时候，可以让用户直接回到之前定的页面
                request.getSession().removeAttribute(Constantion.LOGIN_TO_URL);
                if (toUrl == null) {
                    toUrl = "/index";
                    //如果没有记录，则跳转到index页面
                }
                return new ResponseData(ExceptionMsg.SUCCESS, toUrl);
            }
        } catch (Exception e) {
            System.out.println("操作失败");
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }


    @RequestMapping("loginDo")
    public ModelAndView log() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        mv.addObject("index", "hi00000");
        return mv;
    }

    @RequestMapping("logout")
    public ModelAndView logout(HttpServletRequest request) {
        request.getSession().removeAttribute(Constantion.USER_CONTEXT);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }


    @RequestMapping("forgotPassword")
    public ModelAndView forgetPassWord() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/forgetPassword");
        return mv;
    }

    @RequestMapping("user/sendForgotPasswordEmail")
    public ResponseData forgetEmail(String email) {
        //接收到前端发来的邮箱地址
        try {
            User registerUser = userServiceImp.getUserByEmail(email);
            if (registerUser == null) {
                //先检测这个邮箱在不在数据库里面
                return new ResponseData(ExceptionMsg.EmailNotRegister);
            }
            Random random = new Random();
            StringBuffer secretKey = new StringBuffer();
            for (int i = 0; i < 4; i++) {
                //利用随机数生成4位验证码
                int tmp = random.nextInt(9);
                secretKey.append(tmp);
            }
            String ans = secretKey.toString();
            registerUser.setValidcode(ans);
            //将验证码存到数据库里，为后面的验证做准备
            userServiceImp.updateUserValidCode(ans,registerUser.getUserId());
            //发送邮件
            mailService.sendMail("SHUE密码找回服务", ans, email);
            return new ResponseData(ExceptionMsg.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping("user/sendWord")
    public ResponseData sendWord(String email, String word) {
        System.out.println(email);
        System.out.println(word);
        try {
            //System.out.println(email);
            //System.out.println(word);
            User user = userServiceImp.getUserByEmail(email);
            System.out.println(user.getValidcode());
            if (user.getValidcode().equals(word)) {
                return new ResponseData(ExceptionMsg.SUCCESS);
            } else {
                return new ResponseData(ExceptionMsg.FAILED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }

    @RequestMapping("user/success/{email}")
    public ModelAndView userSuccess(@PathVariable("email") String email){
        ModelAndView mv = new ModelAndView();
        User user = userServiceImp.getUserByEmail(email);
        mv.addObject("userId",user.getUserId());
        mv.setViewName("user/newPassword");
        return mv;
    }

    @RequestMapping("user/setNewPassword")
    public ResponseData userSetNewPsd(String newpwd,int id){
        try{
            userServiceImp.resetPassword(newpwd,id);
            return new ResponseData(ExceptionMsg.SUCCESS);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseData(ExceptionMsg.FAILED);
        }
    }
}
