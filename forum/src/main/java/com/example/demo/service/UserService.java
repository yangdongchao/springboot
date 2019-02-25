package com.example.demo.service;

import com.example.demo.domain.User;
import org.springframework.data.domain.Page;

import javax.xml.soap.SAAJResult;
import java.util.List;

/**
 * @ClassName UserService
 * @Description TODO
 * @Auther ydc
 * @Date 2019/1/8 16:00
 * @Version 1.0
 **/
public interface UserService {
    List<User> getUsersByName(String name);//模糊查询接口
    User getUserByName(String name);//获得用户
    User getUserById(int id);//通过id获得用户

    User getUserByEmail(String email);//通过email查找用户
    Page<User> getUserPage(int pageNum,int pageSize);//根据页码和每页大小获取页面

    Page<User> getUserPageByName(String name,int pageNum,int pageSize);//模糊查询，查询后进行分页

    void resetPassword(String newPassword,int id);//重置密码
    void resetUserType(int type,int id);//重置用户类型
    void resetUserGrade(String grade,int id);//重置年级
    void resetUserAcademy(String academy,int id);//重置学院
    void restUserLock(int sta,int id);//重置用户锁定状态

    void updateUserSignature(String signature,int id);//修改个人简介

    void updateUserPhoto(String photo,int id);

    void updateCredits(int credit,int id);//更新积分

    void updateUserName(String name,int id);//更新用户名

    void loginSuccess(User user);//登录验证

    void saveUser(User user);

    void updateUserValidCode(String validCode,int userId);

    void updateUserBg(String bg,int userId);

    List<User> getUsersByUserName(String name);

    //void resetPhoto();//重置头像




}
