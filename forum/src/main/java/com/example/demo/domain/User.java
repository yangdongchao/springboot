package com.example.demo.domain;



import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName User
 * @Description TODO
 * @Auther ydc
 * @Date 2019/1/7 19:44
 * @Version 1.0
 **/
@Entity
@Table(name = "user") //指定对应的数据库表
public class User extends BaseDomain implements Serializable {

    public static final int user_locked = 1; //用户被锁定
    public static  final int user_unlocked = 0; // 用户未被锁定
    public  static  final int user_normal=0;//普通用户
    public static  final int user_Admin = 1; //版块管理员
    public static  final  int user_super_admin=2; //超级管理员

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主键生成策略是自增型
    @Column(name = "user_id")  //对应数据库的主键名
    private int userId;

    @Column(name = "user_name")
    private String userName;//用户姓名

    @Column(name="password")
    private String password;//用户密码

    @Column(name = "credit")
    private int credits;//用户积分

    @Column(name = "grade")
    private  String grade;//用户年级

    @Column(name = "academy")
    private  String academy;//所在学院

    @Column(name = "user_type")
    private  int user_type;//用户类型

    @Column(name = "locked")
    private  int locked;//用户是否被锁定（如果被锁定，表示管理员禁止该用户登录）

    @Column(name = "photo")//用户个人头像
    private String photo;

    @Column(name = "last_ip") //用户所在ip地址
    private String lastIp;

    @Column(name = "last_visit")//最后访问网站的时间
    private Date lastVisit;

    @Column(name = "signature")//个性签名
    private String signature;

    @Column(name = "backPicture")//背景图片
    private String backPicture;

    @Column(name = "email") //用户邮箱
    private String email;

    @Column(name = "validcode")//验证邮箱时的验证码，只有在找回密码阶段才使用
    private String validcode;

    @Transient
    private String typeName;


    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinTable(name = "board_manager",joinColumns = {@JoinColumn(name = "user_id")},inverseJoinColumns = {@JoinColumn(name = "board_id")})
    private Set<Board> manBoards =new HashSet<Board>();

    public Set<Board> getManBoards() {
        return manBoards;
    }


    public String getValidcode() {
        return validcode;
    }

    public void setValidcode(String validcode) {
        this.validcode = validcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getBackPicture() {
        return backPicture;
    }

    public void setBackPicture(String backPicture) {
        this.backPicture = backPicture;
    }

    public void setManBoards(Set<Board> manBoards) {
        this.manBoards = manBoards;
    }

    public String getPhoto() {
        return photo;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getCredits() {
        return credits;
    }

    public String getGrade() {
        return grade;
    }

    public String getAcademy() {
        return academy;
    }

    public int getUser_type() {
        return user_type;
    }

    public int getLocked() {
        return locked;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public Date getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public User(String userName, String password, int credits, String grade, String academy, int user_type, int locked, String photo, String lastIp, Date lastVisit) {

        this.userName = userName;
        this.password = password;
        this.credits = credits;
        this.grade = grade;
        this.academy = academy;
        this.user_type = user_type;
        this.locked = locked;
        this.photo = photo;
        this.lastIp = lastIp;
        this.lastVisit = lastVisit;
    }

    public User() {
    }
}
