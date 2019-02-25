package com.example.demo.service.imp;

import com.example.demo.dao.LoginLogDao;
import com.example.demo.dao.UserDao;
import com.example.demo.domain.LoginLog;
import com.example.demo.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName LoginLogServiceImp
 * @Description TODO
 * @Auther ydc
 * @Date 2019/1/10 11:40
 * @Version 1.0
 **/
@Service
@Transactional
public class LoginLogServiceImp  implements LoginLogService {
    private LoginLogDao loginLogDao;
    private UserDao userDao;

    @Override
    public void loginSave(LoginLog loginLog) {
        loginLogDao.save(loginLog);
    }

    @Override
    public LoginLog loginFindByUserId(int id){
        return loginLogDao.findByUserId(id);
    }

    @Override
    public List<LoginLog> loginLogFindAllByUserId(int id) {
        return loginLogDao.findAllByUserId(id);
    }

    @Autowired
    public void setLoginLogDao(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
