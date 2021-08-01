package com.example.myapplication.Model;

import android.util.Log;

import com.example.myapplication.DataBean.IsRegister;
import com.example.myapplication.Presenter.RegisterPresenter;
import com.example.myapplication.basic.BaseCreator;
import com.example.myapplication.basic.BaseModel;
import com.example.myapplication.contract.IPost;
import com.example.myapplication.contract.IRegister;
import com.example.myapplication.util.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created with Android studio
 *
 * @Author: EDGClearlove7
 * @Date: 2021/07/27/12:45
 * @Description:
 */
public class RegisterModel extends BaseModel<RegisterPresenter, IRegister.M> {
    /**
     * 根据传入的P层与P层绑定
     *
     * @param mPresenter
     */
    public RegisterModel(RegisterPresenter mPresenter) {
        super(mPresenter);
    }

    @Override
    public IRegister.M getContract() {
       return new IRegister.M() {
           @Override
           public void requestRegister(String username, String password, String nickname, String userEmail, String checkCode) throws Exception {
               RequestBody requestBody=new FormBody.Builder().add("username",username).
                       add("password",password).add("nickname",nickname).add("userEmail",userEmail)
                       .add("checkCode",checkCode)
                       .build();
               Request request=new Request.Builder().url("http://39.98.41.126:31109/user/register?").post(requestBody).build();
               OkHttpClient okHttpClient=new OkHttpClient();
               okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
                   @Override
                   public void onFailure(okhttp3.Call call, IOException e) {

                   }

                   @Override
                   public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                       String responseData = response.body().string();
                       Log.d("===================",responseData);
                       try {
                           JSONObject jsonObject=new JSONObject(responseData);
                           Boolean flag=jsonObject.getBoolean("flag");
                           String message=jsonObject.getString("message");
                           if(flag){
                               mPresenter.getContract().responseRegister(username,password,Constants.REGISTER_SUCCESS_CODE);
                           }else{
                               if(message.equals("注册失败，服务器网络异常")){
                                   mPresenter.getContract().responseRegister(username,password,Constants.REGISTER_ERROR_NETWORK);
                               }else if(message.equals("已存在该用户名，注册失败")){
                                   mPresenter.getContract().responseRegister(username,password,Constants.REGISTER_ERROR_USERNAME);
                               }
                           }
                       } catch (JSONException e) {
                           e.printStackTrace();
                       } catch (Exception e) {
                           e.printStackTrace();
                       }

                   }
               });

//               IPost post= BaseCreator.create(IPost.class);
//               Log.d("=========","daozheli");
//               Log.d("===================",userEmail);
//               post.registerData(username,password,nickname, userEmail, checkCode).enqueue(new Callback<IsRegister>() {
//                   @Override
//                   public void onResponse(Call<IsRegister> call, Response<IsRegister> response) {
//                       Boolean flag=response.body().getFlag();
//                       if(flag){
//                           try {
//                               mPresenter.getContract().responseRegister(username,password,Constants.REGISTER_SUCCESS_CODE);
//                           } catch (Exception e) {
//                               e.printStackTrace();
//                           }
//                       }else{
//                           String Msg=response.body().getMessage();
//                           if(Msg.equals("注册失败，服务器网络异常")){
//                               try {
//                                   mPresenter.getContract().responseRegister(username,password,Constants.REGISTER_ERROR_NETWORK);
//                               } catch (Exception e) {
//                                   e.printStackTrace();
//                               }
//                           }else if(Msg.equals("已存在该用户名，注册失败")){
//                               try {
//                                   mPresenter.getContract().responseRegister(username,password,Constants.REGISTER_ERROR_USERNAME);
//                               } catch (Exception e) {
//                                   e.printStackTrace();
//                               }
//                           }
//                       }
//                   }
//
//                   @Override
//                   public void onFailure(Call<IsRegister> call, Throwable t) {
//                       try {
//                           mPresenter.getContract().responseRegister(username,password,Constants.NETWORK_ERROR);
//                           Log.d("===============","baocuole");
//                       } catch (Exception e) {
//                           e.printStackTrace();
//                       }
//                   }
//               });
           }

           @Override
           public void requestCheckCode(String userEmail) throws Exception {
               Log.d("==================",userEmail);
               RequestBody requestBody=new FormBody.Builder().add("userEmail",userEmail).build();
               Request request=new Request.Builder().url("http://39.98.41.126:31109/user/sendCheckCode?").post(requestBody).build();
               OkHttpClient okHttpClient=new OkHttpClient();
               okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
                   @Override
                   public void onFailure(okhttp3.Call call, IOException e) {
                       e.printStackTrace();
                   }

                   @Override
                   public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                       String responseData = response.body().string();
                       try {
                           JSONObject jsonObject=new JSONObject(responseData);
                           Boolean flag=jsonObject.getBoolean("flag");
                           if(flag){
                               mPresenter.getContract().responseRegisterCodeResult(Constants.SUCCESS_REGISTER_CODE);
                           }else{
                               mPresenter.getContract().responseRegisterCodeResult(Constants.ERROR_REGISTER_CODE);
                           }
                       } catch (JSONException e) {
                           e.printStackTrace();
                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                   }
               });
           }
       };
    }
}
