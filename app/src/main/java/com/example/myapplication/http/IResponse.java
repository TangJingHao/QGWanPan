package com.example.myapplication.http;

/**
 * Created with Android studio
 *
 * @Author: EDGClearlove7
 * @Date: 2021/07/29/11:12
 * @Description:
 */
public interface IResponse {
    /**
     *  响应码
     * @return
     */
    int getCode();

    /**
     *  返回的数据
     * @return
     */
    String getData();
}
