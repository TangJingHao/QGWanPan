package com.example.myapplication.util;

import android.util.Log;

import com.example.myapplication.DataBean.SearchResult;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Name：NetWorkUtil
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/7/31 14:39
 */
public class NetWorkUtil {

    final static String TAG = "NetWorkUtils";

    public static String sendRequestOkHttp(FormBody formBody,String urlType,String jwt,int uid) {
        String responseData = null;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(Constants.ServerURL+urlType)
                .addHeader("Authorization",jwt)
                .addHeader("uid",String.valueOf(uid))
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            responseData = response.body().string();
            Log.d(TAG,"sendRequestWithOkHttp: "+responseData);
        } catch (IOException e) {
            Log.d(TAG,"网络错误");
            e.printStackTrace();
        }
        return responseData;
    }


    public static SearchResult jsonExchange(String json){
        Gson gson = new Gson();
        Log.d(TAG,json);
        SearchResult searchResult = gson.fromJson(json,SearchResult.class);
        Log.d(TAG,searchResult.getMessage());
        return searchResult;
    }
//    public static List<Word> jsonExchangeList(String json){
//        List<Word> words = new ArrayList<>();
//        Gson gson = new Gson();
//        words = gson.fromJson(json,new TypeToken<List<Word>>(){}.getType());
//        return words;
//    }
}