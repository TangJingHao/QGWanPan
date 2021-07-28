package com.example.myapplication.basic;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created with Android studio
 *
 * @Author: EDGClearlove7
 * @Date: 2021/07/28/10:32
 * @Description:
 */
public class BaseCreator {
    private final static String BASE_URL="http://39.98.41.126:31109/";
    private static Retrofit retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    public static <T> T create(Class<T> retrofitClass){
        return retrofit.create(retrofitClass);
    }
}
