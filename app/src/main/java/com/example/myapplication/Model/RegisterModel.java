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
               IPost post= BaseCreator.create(IPost.class);
               post.registerData(username,password,nickname).enqueue(new Callback<IsRegister>() {
                   @Override
                   public void onResponse(Call<IsRegister> call, Response<IsRegister> response) {
                       Boolean flag=response.body().getFlag();
                       if(flag){
                           try {
                               mPresenter.getContract().responseRegisterResult(Constants.REGISTER_SUCCESS_CODE);
                           } catch (Exception e) {
                               e.printStackTrace();
                           }
                       }else{
                           String Msg=response.body().getMessage();
                           if(Msg.equals("注册失败，服务器网络异常")){
//                               mPresenter.getContract().responseRegisterResult(Constants.REGISTER_ERROR_NETWORK,username,password );
                           }else if(Msg.equals("已存在该用户名，注册失败")){
//                               mPresenter.getContract().responseRegisterResult(Constants.REGISTER_ERROR_USERNAME, username,password );
                           }
                       }
                   }

                   @Override
                   public void onFailure(Call<IsRegister> call, Throwable t) {
                       try {
                           mPresenter.getContract().responseRegisterResult(Constants.NETWORK_ERROR);
                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                   }
               });
           }

           @Override
           public void requestCheckCode(String userEmail) throws Exception {
               Log.d("==================",userEmail+"qq.com");
               RequestBody requestBody=new FormBody.Builder().add("userEmail","762795632@qq.com").build();
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
                               mPresenter.getContract().responseRegisterResult(Constants.SUCCESS_REGISTER_CODE);
                           }else{
                               mPresenter.getContract().responseRegisterResult(Constants.ERROR_REGISTER_CODE);
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
