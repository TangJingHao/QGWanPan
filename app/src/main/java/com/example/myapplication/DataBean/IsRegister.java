package com.example.myapplication.DataBean;

/**
 * Created with Android studio
 *
 * @Author: EDGClearlove7
 * @Date: 2021/07/28/10:01
 * @Description: 判断注册信息的类（对应参数写好）
 */
public class IsRegister {
    private Boolean flag;
    private String message;
    private Object data;


    public IsRegister() {
    }

    public IsRegister(Boolean flag, String message, Object data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
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
}
