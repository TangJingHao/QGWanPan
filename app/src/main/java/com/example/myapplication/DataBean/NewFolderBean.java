package com.example.myapplication.DataBean;

/**
 * @Description:
 * @author: Hx
 * @date: 2021年08月02日 1:11
 */
public class NewFolderBean {
    private String flag;
    private String message;
    private String data;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "NewFile{" +
                "flag='" + flag + '\'' +
                ", message='" + message + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
