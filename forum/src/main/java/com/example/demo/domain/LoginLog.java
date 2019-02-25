package com.example.demo.domain;


import org.springframework.context.annotation.ComponentScan;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName 登录日志，记录用户的登录信息
 * @Description TODO
 * @Auther ydc
 * @Date 2019/1/7 20:09
 * @Version 1.0
 **/
@Entity
@Table(name = "login_log")
public class LoginLog extends BaseDomain implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "login_log_id")
    private int loginLogId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "ip")
    private String ip;

    @Column(name = "login_time")
    private Date loginDate;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public int getLoginLogId() {
        return loginLogId;
    }

    public void setLoginLogId(int loginLogId) {
        this.loginLogId = loginLogId;
    }


    public LoginLog(int userId, String ip, Date loginDate) {
        this.userId=userId;
        this.ip = ip;
        this.loginDate = loginDate;
    }

    public LoginLog() {
    }
}
