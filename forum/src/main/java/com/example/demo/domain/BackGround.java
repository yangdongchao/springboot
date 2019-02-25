package com.example.demo.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName BackGround
 * @Description 图片实体类，目前还没有用的
 * @Auther ydc
 * @Date 2019/1/8 11:01
 * @Version 1.0
 **/
@Entity
@Table(name = "background")
public class BackGround extends BaseDomain implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int backgroundId;

    @Column(name = "background_title")
    private String backgroundTitle;

    @Column(name = "picture")
    private String picture;

    public int getBackgroundId() {
        return backgroundId;
    }

    public String getBackgroundTitle() {
        return backgroundTitle;
    }

    public String getPicture() {
        return picture;
    }

    public void setBackgroundId(int backgroundId) {
        this.backgroundId = backgroundId;
    }

    public void setBackgroundTitle(String backgroundTitle) {
        this.backgroundTitle = backgroundTitle;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public BackGround(String backgroundTitle, String picture) {
        this.backgroundTitle = backgroundTitle;
        this.picture = picture;
    }

    public BackGround() {
    }
}
