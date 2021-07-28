package com.example.myapplication.Model;

/**
 * Created with Android studio
 *
 * @Author: EDGClearlove7
 * @Date: 2021/07/27/15:06
 * @Description:
 */
public class User {
   private Boolean flag;//登录状态
   private String message;//登录的信息
   private UserData data;//用户的数据

    public User() {
    }

    public User(Boolean flag, String message, UserData data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
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
}

class UserData{
    private int id;
    private String username;
    private String password;
    private String nickname;
    private String image;//用户头像

    public UserData() {
    }

    public UserData(int id, String username, String password, String nickname, String image) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.image = image;
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
}
