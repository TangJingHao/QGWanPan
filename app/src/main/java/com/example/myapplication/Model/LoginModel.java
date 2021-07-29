package com.example.myapplication.Model;


import com.example.myapplication.DataBean.User;
import com.example.myapplication.DataBean.UserData;
import com.example.myapplication.Presenter.LoginPresenter;
import com.example.myapplication.basic.BaseCreator;
import com.example.myapplication.basic.BaseModel;
import com.example.myapplication.contract.ILogin;
import com.example.myapplication.contract.IPost;
import com.example.myapplication.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * @Name： LoginModel
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/7/27 9:29
 */
public class LoginModel extends BaseModel<LoginPresenter, ILogin.M> {

    public LoginModel(LoginPresenter mPresenter) {
        super(mPresenter);
    }

    @Override
    public ILogin.M getContract() {
        return new ILogin.M() {
            @Override
            public void requestLogin(String name, String pwd) throws Exception {
                //请求服务器登录接口，然后拿到服务器返回JSON数据
                //下面这个判断的具逻辑可以改 这只是一个例子
                //调用mPresenter.getContract().responseLoginResult(1);即可进行回传
//                if("abc".equals(name)&&"123".equals(pwd)){
//                    Log.d("Login1",name+"true");
//                    mPresenter.getContract().responseLoginResult(1);
//
//                }else {
//                    Log.d("Login1",name+"false");
//                    mPresenter.getContract().responseLoginResult(0);
//                }
                IPost post= BaseCreator.create(IPost.class);
                post.loginData(name,pwd).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Boolean flag=response.body().getFlag();//登录状态
                        if(flag){
                            UserData data=response.body().getData();//这部分内容存入数据库
                            int ID=data.getUser().getId();
                            String jwt=data.getJwt();
                            mPresenter.getContract().responseLoginResult(Constants.SUCCESS_LOGIN_CODE,ID,jwt);
                        }else{
                            String message=response.body().getMessage();
                            if(message.equals("无该用户名，登录失败")){
                                mPresenter.getContract().responseLoginResult(Constants.FAIL_LOGIN_USERNAME_CODE,Constants.ERROR_ID,Constants.ERROR_JWT);
                            }else if(message.equals("用户密码错误，登录失败")){
                                mPresenter.getContract().responseLoginResult(Constants.FAIL_LOGIN_PASSWORD_CODE,Constants.ERROR_ID,Constants.ERROR_JWT);
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        mPresenter.getContract().responseLoginResult(Constants.NETWORK_ERROR, Constants.ERROR_ID,Constants.ERROR_JWT);
                    }
                });
            }
        };
    }
}
