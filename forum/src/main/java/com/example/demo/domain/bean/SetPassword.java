package com.example.demo.domain.bean;

/**
 * @ClassName SetPassword
 * @Description 接收前端参数的bean
 * @Auther ydc
 * @Date 2019/1/11 18:41
 * @Version 1.0
 **/
public class SetPassword {
    private String oldPassword;
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public SetPassword(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public SetPassword() {
        super();
    }
}
