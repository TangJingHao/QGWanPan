package com.example.myapplication.Model;

import com.example.myapplication.DataBean.IsRegister;
import com.example.myapplication.Presenter.RegisterPresenter;
import com.example.myapplication.basic.BaseCreator;
import com.example.myapplication.basic.BaseModel;
import com.example.myapplication.contract.IPost;
import com.example.myapplication.contract.IRegister;
import com.example.myapplication.util.Constants;

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
           public void requestRegister(String username, String password,String nickname) throws Exception {
               IPost post= BaseCreator.create(IPost.class);
               post.registerData(username,password,nickname).enqueue(new Callback<IsRegister>() {
                   @Override
                   public void onResponse(Call<IsRegister> call, Response<IsRegister> response) {
                       Boolean flag=response.body().getFlag();
                       if(flag){
                           mPresenter.getContract().responseRegisterResult(Constants.REGISTER_SUCCESS_CODE,username ,password );
                       }else{
                           String Msg=response.body().getMessage();
                           if(Msg.equals("注册失败，服务器网络异常")){
                               mPresenter.getContract().responseRegisterResult(Constants.REGISTER_ERROR_NETWORK,username,password );
                           }else if(Msg.equals("已存在该用户名，注册失败")){
                               mPresenter.getContract().responseRegisterResult(Constants.REGISTER_ERROR_USERNAME, username,password );
                           }
                       }
                   }

                   @Override
                   public void onFailure(Call<IsRegister> call, Throwable t) {
                       mPresenter.getContract().responseRegisterResult(Constants.NETWORK_ERROR,username,password );
                   }
               });
           }
       };
    }
}
