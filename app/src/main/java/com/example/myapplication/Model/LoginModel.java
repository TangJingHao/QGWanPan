package com.example.myapplication.Model;

import android.util.Log;

import com.example.myapplication.Presenter.LoginPresenter;
import com.example.myapplication.basic.BaseModel;
import com.example.myapplication.contract.ILogin;

/**
 * @Name：My Application
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
                if("abc".equals(name)&&"123".equals(pwd)){
                    Log.d("Login1",name+"true");
                    mPresenter.getContract().responseLoginResult(1);

                }else {
                    Log.d("Login1",name+"false");
                    mPresenter.getContract().responseLoginResult(0);
                }
            }
        };
    }
}
