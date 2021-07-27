package com.example.myapplication.View;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Presenter.LoginPresenter;
import com.example.myapplication.R;
import com.example.myapplication.basic.BaseActivity;
import com.example.myapplication.contract.ILogin;

/**
 * @Name：My Application
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/7/27 9:30
 */
public class LoginActivity extends BaseActivity<LoginPresenter, ILogin.VP> {

    //控件只是例子 都可以改
    private EditText etName;
    private EditText etPwd;
    private Button btLogin;
    private final static String TAG = "LoginActivity";

    @Override
    public ILogin.VP getContract() {
        return new ILogin.VP() {
            @Override
            public void requestLogin(String name, String pwd) {
                mPresenter.getContract().requestLogin(name,pwd);
            }

            @Override
            public void responseLoginResult(int loginStatusResult) {
                //覆写这个函数传入的参数就是拿到的返回码
                //可以根据返回码进行UI的更新
                //下面只是例子
                //记得更新UI回到主线程
                Log.d(TAG,"result" +String.valueOf(loginStatusResult));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this,String.valueOf(loginStatusResult),Toast.LENGTH_SHORT).show();
                    }
                });

            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);
    }

//  初始化控件的函数
    @Override
    public void initView() {
        etName = findViewById(R.id.editTextNumberPassword);
        etPwd = findViewById(R.id.editTextTextPassword);
        btLogin = findViewById(R.id.button);
    }
//  初始化数据 这个登录好像还没用上
    @Override
    public void initData() {
    }

//  设置监听
    @Override
    public void initListener() {
        btLogin.setOnClickListener(this);
    }

//  传入布局的id会自动绑定
    @Override
    public int getContentViewId() {
        return R.layout.activity_login;
    }

//  要创建P层实例 与P层关联
    @Override
    public LoginPresenter getPresenterInstance() {
        return new LoginPresenter();
    }

    @Override
    public void destroy() {

    }

    @Override
    public void onClick(View v) {
        //点击事件的设置  因为只有一个控件就没写switch
        //调用 getContract().requestLogin(name, pwd);
        //即是向P层请求登录 传入用户名和密码
        String name = etName.getText().toString();
        String pwd = etPwd.getText().toString();
        Log.d(TAG,"点击"+name+pwd);
        getContract().requestLogin(name, pwd);
    }
}