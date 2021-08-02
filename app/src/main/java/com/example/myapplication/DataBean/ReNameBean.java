package com.example.myapplication.DataBean;

/**
 * @Description:
 * @author: Hx
 * @date: 2021年08月02日 17:16
 */
public class ReNameBean {
    private boolean flag;
    private String message;
    private String data;

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
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
        return "ReNameBean{" +
                "flag=" + flag +
                ", message='" + message + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
