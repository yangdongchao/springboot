package com.example.demo.service;

import com.example.demo.dao.LoginLogDao;
import com.example.demo.dao.UserDao;
import com.example.demo.domain.LoginLog;
import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @ClassName LoginLogService
 * @Description TODO
 * @Auther ydc
 * @Date 2019/1/8 9:52
 * @Version 1.0
 **/

public interface LoginLogService  {
   void loginSave(LoginLog loginLog);
   LoginLog loginFindByUserId(int id);//查看最新登录记录
   List<LoginLog> loginLogFindAllByUserId(int id);//查找全部登录日志
}
