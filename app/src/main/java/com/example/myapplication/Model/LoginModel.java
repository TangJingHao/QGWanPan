package com.example.myapplication.Model;

import com.example.myapplication.DataBean.UserDataBean;
import com.example.myapplication.Presenter.LoginPresenter;
import com.example.myapplication.basic.BaseModel;
import com.example.myapplication.contract.ILogin;
import com.example.myapplication.http.IHttpClient;
import com.example.myapplication.http.IRequest;
import com.example.myapplication.http.IResponse;
import com.example.myapplication.http.impl.OkHttpClientImpl;
import com.example.myapplication.http.impl.RequestImpl;
import com.example.myapplication.util.Constants;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

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
//                IPost post= BaseCreator.create(IPost.class);
//                post.loginData(name,pwd).enqueue(new Callback<UserDataBean>() {
//                    @Override
//                    public void onResponse(Call<UserDataBean> call, Response<UserDataBean> response) {
//                        UserDataBean userDataBean = response.body();
//                        Boolean flag=response.body().getFlag();//登录状态
//                        if(flag){
//                            int ID = userDataBean.getData().getUser().getId();
//                            String jwt = userDataBean.getData().getJwt();
//                            mPresenter.getContract().responseLoginResult(Constants.SUCCESS_LOGIN_CODE,ID,jwt);
//                        }else{
//                            String message=response.body().getMessage();
//                            if(message.equals("无该用户名，登录失败")){
//                                mPresenter.getContract().responseLoginResult(Constants.FAIL_LOGIN_USERNAME_CODE,Constants.ERROR_ID,Constants.ERROR_JWT);
//                            }else if(message.equals("用户密码错误，登录失败")){
//                                mPresenter.getContract().responseLoginResult(Constants.FAIL_LOGIN_PASSWORD_CODE,Constants.ERROR_ID,Constants.ERROR_JWT);
//                            }
//                        }
//                    }
//                    @Override
//                    public void onFailure(Call<UserDataBean> call, Throwable t) {
//                        mPresenter.getContract().responseLoginResult(Constants.NETWORK_ERROR, Constants.ERROR_ID,Constants.ERROR_JWT);
//                    }
//                });
                /**
                 * 开启子线程接受数据
                 */
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        IRequest request=new RequestImpl("http://39.98.41.126:31109/"+"user/login?username="+name+"&password="+pwd);
                        IHttpClient httpClient=new OkHttpClientImpl();
                        IResponse response=httpClient.get(request);
                        try {
                            JSONObject jsonObject=new JSONObject(response.getData());
                            boolean flag = jsonObject.getBoolean("flag");
                            if(flag){
                                Gson gson=new Gson();
                                UserDataBean userDataBean=gson.fromJson(response.getData(),UserDataBean.class);
                                int ID = userDataBean.getData().getUser().getId();
                                String jwt = userDataBean.getData().getJwt();
                                mPresenter.getContract().responseLoginResult(Constants.SUCCESS_LOGIN_CODE,ID,jwt);
                            }else{
                                String message=jsonObject.getString("message");
                                if(message.equals("无该用户名，登录失败")){
                                    mPresenter.getContract().responseLoginResult(Constants.FAIL_LOGIN_USERNAME_CODE,Constants.ERROR_ID,Constants.ERROR_JWT);
                                }else if(message.equals("用户密码错误，登录失败")){
                                    mPresenter.getContract().responseLoginResult(Constants.FAIL_LOGIN_PASSWORD_CODE,Constants.ERROR_ID,Constants.ERROR_JWT);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                }
            };
        }
    }

