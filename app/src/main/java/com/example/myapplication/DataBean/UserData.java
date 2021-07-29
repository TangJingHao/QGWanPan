package com.example.myapplication.DataBean;

/**
 * Created with Android studio
 *
 * @Author: EDGClearlove7
 * @Date: 2021/07/29/0:20
 * @Description:
 */
public class UserData {
    private String jwt;
    private UserInnerData user;

    public UserData() {
    }

    public UserData(String jwt, UserInnerData user) {
        this.jwt = jwt;
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public UserInnerData getUser() {
        return user;
    }

    public void setUser(UserInnerData user) {
        this.user = user;
    }
}
