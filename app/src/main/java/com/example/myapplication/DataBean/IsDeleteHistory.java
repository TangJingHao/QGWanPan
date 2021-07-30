package com.example.myapplication.DataBean;

/**
 * @Name：My Application
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/7/28 22:58
 */
public class IsDeleteHistory {
    private Boolean flag;
    private String message;

    public IsDeleteHistory() {
    }

    public IsDeleteHistory(Boolean flag, String message) {
        this.flag = flag;
        this.message = message;
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
}
