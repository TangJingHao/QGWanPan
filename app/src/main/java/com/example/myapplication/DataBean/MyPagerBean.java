package com.example.myapplication.DataBean;

/**
 * @Name：  MyPagerBean
 * @Description： 存储个人信息的类 包括头像 组群 个人信息等 具体再添加
 * @Author： Suzy.Mo
 * @Date： 2021/7/28 1:09
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

