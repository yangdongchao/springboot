package com.example.demo.service.imp;

import com.example.demo.dao.LoginLogDao;
import com.example.demo.dao.UserDao;
import com.example.demo.domain.LoginLog;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @ClassName UserService
 * @Description TODO
 * @Auther ydc
 * @Date 2019/1/7 20:27
 * @Version 1.0
 **/
@Transactional
@Service
public class UserServiceImp implements UserService {
    private UserDao userDao;


    private LoginLogDao loginLogDao;

    /**
     * 重置密码
     * @param id
     * @param newPassword
     */
    @Override
    public void resetPassword(String newPassword,int id) {
        userDao.updatePasswordById(newPassword,id);
    }

    @Override
    public User getUserById(int id) {
        return userDao.findByUserId(id);
    }

    @Override
    public User getUserByName(String name) {
        return userDao.findByUserName(name);
    }

    @Override
    public List<User> getUsersByName(String name) {
        name = "%"+name+"%";  //通过设置占位符实现模糊查询
        return userDao.findByUserNameLike(name);
    }


    /**
     * 根据用户id从小到大进行分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page<User> getUserPage(int pageNum, int pageSize) {
        PageRequest pageable = new PageRequest(pageNum-1,pageSize,Sort.Direction.ASC,"userId");
        return userDao.findAll(pageable);
    }

    /**
     * 按名字进行模糊查询，并分页
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page<User> getUserPageByName(String name, int pageNum, int pageSize) {
        name = "%"+name+"%";
        PageRequest pageable = PageRequest.of(pageNum,pageSize,Sort.by(Sort.Direction.DESC,"userId"));
        return userDao.findByUserNameLike(name,pageable);
    }

    /**
     * 重置用户年级，学院等
     * @param academy
     * @param id
     */

    @Override
    public void resetUserAcademy(String academy, int id) {
        userDao.updateUserGradeById(academy,id);
        userDao.flush();
    }

    @Override
    public void resetUserGrade(String grade, int id) {
        userDao.updateUserGradeById(grade,id);
        userDao.flush();
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    /**
     * 修改用户类型
     * @param type
     * @param id
     */
    @Override
    public void resetUserType(int type, int id) {

        userDao.updateUserTypeById(type,id);
        userDao.flush();
    }

    /**
     * 更新头像地址
     * @param photo
     * @param id
     */
    @Override
    public void updateUserPhoto(String photo, int id) {
        userDao.updateUserPhoto(photo,id);
    }

    /**
     * 重置用户锁定状态
     * @param sta
     * @param id
     */
    @Override
    public void restUserLock(int sta, int id) {
        userDao.updateUserLockedById(sta,id);
        userDao.flush();
    }


    @Override
    public void updateCredits(int credit, int id) {
        userDao.updateUserCreditById(credit,id);
        userDao.flush();
    }

    @Override
    public void updateUserSignature(String signature, int id) {
        userDao.updateUserSignature(signature,id);
        userDao.flush();
    }

    /**
     * 更新用户名
     * @param name
     * @param id
     */
    @Override
    public void updateUserName(String name, int id) {
        userDao.updateUserName(name,id);
        userDao.flush();
    }

    @Override
    public void loginSuccess(User user) {
        LoginLog loginLog = new LoginLog(user.getUserId(),user.getLastIp(),new Date());
        System.out.println(loginLog);
        loginLogDao.save(loginLog);//先保存登录日志
        updateCredits(user.getCredits()+5,user.getUserId());
    }

    @Override
    public void updateUserValidCode(String validCode, int userId) {
        userDao.updateUserValidCode(validCode,userId);
    }


    /**
     * 更新用户背景图像地址
     * @param bg
     * @param userId
     */
    @Override
    public void updateUserBg(String bg, int userId) {
        userDao.updateUserBg(bg,userId);
    }

    /**
     * 存储user
     * @param user
     */
    @Override
    public void saveUser(User user) {
        userDao.save(user);
    }

    @Override
    public List<User> getUsersByUserName(String name) {
        name="%"+name+"%";
        return userDao.findByUserNameLike(name);
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setLoginLogDao(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }
}
