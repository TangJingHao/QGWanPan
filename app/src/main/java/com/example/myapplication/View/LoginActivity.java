package com.example.myapplication.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Presenter.LoginPresenter;
import com.example.myapplication.R;
import com.example.myapplication.basic.BaseActivity;
import com.example.myapplication.contract.ILogin;
import com.example.myapplication.util.Constants;
import com.example.myapplication.util.PermissionUtils;

import org.jetbrains.annotations.NotNull;

/**
 * @Name：My Application
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/7/27 9:30
 */
public class LoginActivity extends BaseActivity<LoginPresenter, ILogin.VP> {

    //控件只是例子 都可以改
    private EditText mUserNameEt;//用户名
    private EditText mPasswordEt;//密码
    private Button mLoginBtn;//登录按钮
    private CheckBox mRememberCb;//复选框
    private Button mRegisterBtn;//注册用户按钮
    private SharedPreferences.Editor mSaveLoginData;
    private SharedPreferences mPref;
    private final static String TAG = "LoginActivity";

    @Override
    public ILogin.VP getContract() {
        return new ILogin.VP() {
            @Override
            public void requestLogin(String name, String pwd) {
                mPresenter.getContract().requestLogin(name,pwd);
            }

            @Override
            public void responseLoginResult(int loginStatusResult, int ID,String jwt) {
                //覆写这个函数传入的参数就是拿到的返回码
                //可以根据返回码进行UI的更新
                //下面只是例子
                //记得更新UI回到主线程
//                Log.d(TAG,"result" +String.valueOf(loginStatusResult));
//                runOnUiThread(() -> Toast.makeText(LoginActivity.this,String.valueOf(loginStatusResult),Toast.LENGTH_SHORT).show());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(loginStatusResult== Constants.NETWORK_ERROR){
                            Toast.makeText(LoginActivity.this,"网络异常，请检查你的网络连接！",Toast.LENGTH_SHORT).show();
                        }else if(loginStatusResult==Constants.FAIL_LOGIN_USERNAME_CODE){
                            Toast.makeText(LoginActivity.this,"无该用户名，请检查并重试",Toast.LENGTH_SHORT).show();
                        }else if(loginStatusResult==Constants.FAIL_LOGIN_PASSWORD_CODE){
                            Toast.makeText(LoginActivity.this,"用户密码错误，请重试！",Toast.LENGTH_SHORT).show();
                        }else if(loginStatusResult==Constants.SUCCESS_LOGIN_CODE){
                            saveLoginData();
                            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                            intent.putExtra("jwt",jwt);
                            intent.putExtra("ID",ID);
                            startActivity(intent);
                        }
                    }
                });

            }
        };
    }

    private void saveLoginData() {
        if(mRememberCb.isChecked()){
            mSaveLoginData=mPref.edit();
            mSaveLoginData.putBoolean("remember_login",true);
            mSaveLoginData.putString("username",mUserNameEt.getText().toString());
            mSaveLoginData.putString("password",mPasswordEt.getText().toString());
        }else{
            mSaveLoginData.clear();
        }
        mSaveLoginData.apply();
    }

    //  初始化控件的函数
    @Override
    public void initView() {
        initBeginView();
        PermissionCheck();
    }

    /**
     * 检查权限方法
     */
    private void PermissionCheck() {
        PermissionUtils.verifyStoragePermissions(LoginActivity.this);
        if (!PermissionUtils.isConn(LoginActivity.this)) {
            PermissionUtils.setNetworkMethod(LoginActivity.this);
        }
    }

    /**
     * 初始化界面方法(实现记住密码功能)
     */
    private void initBeginView() {
        mUserNameEt = this.findViewById(R.id.username_et);
        mPasswordEt = this.findViewById(R.id.password_et);
        mRememberCb = this.findViewById(R.id.remember_cb);
        mLoginBtn = this.findViewById(R.id.login_btn);
        mRegisterBtn=this.findViewById(R.id.register_btn);
        mPref= PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);//获取数据
        Boolean isRemember=mPref.getBoolean("remember_login",false);
        //若用户点击了记住密码功能
        if (isRemember) {
            String username = mPref.getString("username", "");
            String password = mPref.getString("password", "");
            mUserNameEt.setText(username);
            mPasswordEt.setText(password);
            mRememberCb.setChecked(true);
        }
    }

    //  初始化数据 这个登录好像还没用上
    @Override
    public void initData() {

    }

//  设置监听
    @Override
    public void initListener() {
        mRegisterBtn.setOnClickListener(this);
        mLoginBtn.setOnClickListener(this);
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
//        String name = etName.getText().toString();
//        String pwd = etPwd.getText().toString();
//        Log.d(TAG,"点击"+name+pwd);
//        getContract().requestLogin(name, pwd);
        switch (v.getId()){
            case R.id.login_btn:
                mSaveLoginData=mPref.edit();
                String username=mUserNameEt.getText().toString();
                String password=mPasswordEt.getText().toString();
                //判断用户输入处理
                if(username==null&&password!=null){
                    Toast.makeText(LoginActivity.this,"账户不能为空",Toast.LENGTH_SHORT).show();
                }else if(password==null&&username!=null){
                    Toast.makeText(LoginActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                }else if(password==null&&username==null){
                    Toast.makeText(LoginActivity.this,"账号和密码不能为空",Toast.LENGTH_SHORT).show();
                }else if(username!=null&&password!=null){
                    getContract().requestLogin(username,password);
                }
                break;
            case R.id.register_btn:
                Intent intent=new Intent(this,RegisterActivity.class);
                startActivityForResult(intent,Constants.LOGIN_TO_REGISTER_CODE);
                break;
        }

    }

    /**
     * 检查权限
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0) {
                for (int result : grantResults) {
                    if (result != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "你必须同意所有权限才可以使用本程序!", Toast.LENGTH_SHORT).show();
                        finish();//关闭当前程序
                        return;
                    }
                }
            } else {
                Toast.makeText(this, "发生未知错误，请重试！", Toast.LENGTH_SHORT).show();
                finish();
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Constants.LOGIN_TO_REGISTER_CODE){
            if(resultCode == Constants.REGISTER_TO_LOGIN_CODE){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this,"恭喜你！注册成功!",Toast.LENGTH_SHORT).show();
                        String username=data.getStringExtra("username");
                        String password=data.getStringExtra("password");
                        mUserNameEt.setText(username);
                        mPasswordEt.setText(password);
                        mRememberCb.setChecked(true);
                    }
                });
            }
        }
    }

}