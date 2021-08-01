package com.example.myapplication.DataBean;

import java.util.List;

public class FileBean {
    private String flag;
    private String message;
    private List<FileDataBean> data;

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

    public List<FileDataBean> getData() {
        return data;
    }

    public void setData(List<FileDataBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "FileBean{" +
                "flag='" + flag + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
