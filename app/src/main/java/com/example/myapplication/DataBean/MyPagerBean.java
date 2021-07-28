package com.example.myapplication.DataBean;

/**
 * Created with Android studio
 *
 * @Author: EDGClearlove7
 * @Date: 2021/07/29/0:19
 * @Description:
 */
public class MyPagerBean {
    private Boolean flag;
    private String message;
    private MyPagerBeanData data;

    public MyPagerBean(Boolean flag, String message, MyPagerBeanData data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }

    public MyPagerBean() {
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

    public MyPagerBeanData getData() {
        return data;
    }

    public void setData(MyPagerBeanData data) {
        this.data = data;
    }
}
