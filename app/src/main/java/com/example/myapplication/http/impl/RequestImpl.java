package com.example.myapplication.http.impl;

import com.example.myapplication.http.IRequest;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with Android studio
 *
 * @Author: EDGClearlove7
 * @Date: 2021/07/29/11:18
 * @Description:
 */
public class RequestImpl implements IRequest {
    private String method = POST;
    private String url;
    private Map<String,String> header;
    private Map<String,Object> body;

    public RequestImpl(String url) {
        /**
         *  初始化公共参数和头部信息
         */
        this.url = url;
        header = new HashMap();
        body = new HashMap<>();
    }
    @Override
    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public void setHeader(String key, String value) {
        header.put(key,value);
    }

    @Override
    public void setBody(String key, Object value) {
        body.put(key,value);
    }

    @Override
    public String getUrl() {
        if (GET.equals(method)) {
            // 组装post请求参数
            for (String key : body.keySet()) {
                url = url.replace("${"+key+"}",body.get(key).toString());
            }
        }
        return url;
    }

    @Override
    public Map<String, String> getHeader() {
        return header;
    }

    @Override
    public String getBody() {
        // 组装post请求参数
        if (body != null) {
            return new Gson().toJson(this.body, HashMap.class);
        } else {
            return "{}";
        }
    }

}
