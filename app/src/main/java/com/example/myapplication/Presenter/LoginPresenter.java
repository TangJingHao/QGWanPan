package com.example.myapplication.Presenter;

import com.example.myapplication.Model.LoginModel;
import com.example.myapplication.View.LoginActivity;
import com.example.myapplication.basic.BasePresenter;
import com.example.myapplication.contract.ILogin;

/**
 * @Name： LoginPresenter
 * @Description：
 * @Author： Suzy.Mo
 * @Date： 2021/7/27 9:30
 */
public class LoginPresenter extends BasePresenter<LoginModel,LoginActivity, ILogin.VP> {

    private final String TAG = "LoginPresenter";

    @Override
    public LoginModel getModelInstance() {
        //直接返回M层实例即是绑定了M层
        return new LoginModel(this);
    }

    /**
     * 要返回ILogin.VP接口 就new一个顺便实现接口
     * @return ILogin.VP
     */
    @Override
    public ILogin.VP getContract() {
        return new ILogin.VP() {
            @Override
            public void requestLogin(String name, String pwd) {
                //开子线程可以在这里开始开或者在M层开
                //接收请求的信息进行处理
                //直接在try里面改就行
                //调用mModel.getContract().requestLogin(name, pwd);
                //通知M层去请求登录
                try {
                    mModel.getContract().requestLogin(name, pwd);
                } catch (Exception e) {
                    e.printStackTrace();
                    //做异常处理
                    //保存日记等等
                }
            }
            @Override
            public void responseLoginResult(int loginStatusResult, int ID) {
                //解析数据或者把请求码传给View
                mView.getContract().responseLoginResult(loginStatusResult,ID);
            }
        };
    }
}
