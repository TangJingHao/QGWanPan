package com.example.myapplication.DataBean;

/**
 * Created with Android studio
 *
 * @Author: EDGClearlove7
 * @Date: 2021/07/29/0:20
 * @Description:
 */
public class MyPagerBeanData {
    private int id;
    private String username;
    private String password;
    private String nickname;
    private String image;
    private double space;

    public MyPagerBeanData() {
    }

    public MyPagerBeanData(int id, String username, String password, String nickname, String image, double space) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.image = image;
        this.space = space;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getSpace() {
        return space;
    }

    public void setSpace(double space) {
        this.space = space;
    }
}
