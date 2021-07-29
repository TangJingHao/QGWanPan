package com.example.myapplication.DataBean;

/**
 * Created with Android studio
 *
 * @Author: EDGClearlove7
 * @Date: 2021/07/29/0:20
 * @Description:
 */
public class User {
    private Boolean flag;//登录状态
    private UserData data;//用户的数据
    private String message;//登录的信息

    public User() {
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }

    public User(Boolean flag, String message, UserData data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }
}
