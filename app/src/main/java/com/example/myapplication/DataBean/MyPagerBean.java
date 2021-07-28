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
    private MyPagerBeanData data;
    private String message;

    public MyPagerBean() {
    }

    public MyPagerBean(Boolean flag, MyPagerBeanData data, String message) {
        this.flag = flag;
        this.data = data;
        this.message = message;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public MyPagerBeanData getData() {
        return data;
    }

    public void setData(MyPagerBeanData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
