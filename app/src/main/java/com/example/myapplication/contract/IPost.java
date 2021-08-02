package com.example.myapplication.contract;

import com.example.myapplication.DataBean.FileBean;
import com.example.myapplication.DataBean.IsDeleteHistory;
import com.example.myapplication.DataBean.IsRegister;
import com.example.myapplication.DataBean.MyPagerBean;
import com.example.myapplication.DataBean.NewFolderBean;
import com.example.myapplication.DataBean.SearchHistoryBean;
import com.example.myapplication.DataBean.SearchResult;
import com.example.myapplication.DataBean.UserDataBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created with Android studio
 *
 * @Author: EDGClearlove7
 * @Date: 2021/07/27/14:32
 * @Description: 讲所有的Post逻辑写在这里
 */
public interface IPost {
    @FormUrlEncoded
    @POST("user/register")
    Call<IsRegister> registerData(@Field("username")String username, @Field("password")String password,@Field("nickname")String nickname,
                                  @Field("userEmail")String userEmail,@Field("checkCode")String checkCode);

    @FormUrlEncoded
    @POST("user/login")
    Call<UserDataBean> loginData(@Field("username")String username, @Field("password")String password);

    @FormUrlEncoded
    @POST("user/userInfo")
    Call<MyPagerBean> userLoginData(@Header("Authorization")String jwt,@Header("userId")int id,@Field("userid")int ID);

    @FormUrlEncoded
    @POST("file/findDocsByName")
    Call<SearchResult> findDocs(@Header("Authorization")@Field("docname")String docname, @Field("uid")int uid);

    @FormUrlEncoded
    @POST("search/history")
    Call<SearchHistoryBean> searchHistory(@Header("Authorization")@Field("num")int num, @Field("uid")int uid);

    @FormUrlEncoded
    @POST("search/deleteAll")
    Call<IsDeleteHistory> deleteAll(@Header("Authorization")@Field("uid")int uid);

    @FormUrlEncoded
    @POST("folder/enterYun")
    Call<FileBean> getFileData(@Header("Authorization")String jwt, @Header("userid")int userID,@Field("userid")int userid);

    @FormUrlEncoded
    @POST
    Call<NewFolderBean> newFile(@Header("Authorization")String jwt, @Header("userid")int userId ,
                                @Field("userid") int userid, @Field("folderName")String folderName, @Field("fid")int fid);

}
