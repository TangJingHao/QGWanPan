package com.example.myapplication.http.impl;

import com.example.myapplication.http.IResponse;

/**
 * Created with Android studio
 *
 * @Author: EDGClearlove7
 * @Date: 2021/07/29/11:22
 * @Description:
 */
public class ResponseImpl implements IResponse {
    public static final int STATE_UNKNOWN_ERROR = 100001;
    public static int STATE_OK = 200;
    private int code;
    private String data;

    @Override
    public String getData() {
        return data;
    }
    @Override
    public int getCode () {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public void setData(String data) {
        this.data = data;
    }

}
