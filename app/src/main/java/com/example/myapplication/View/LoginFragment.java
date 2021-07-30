package com.example.myapplication.View;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Presenter.LoginPresenter;
import com.example.myapplication.R;
import com.example.myapplication.basic.BaseFragment;
import com.example.myapplication.contract.ILogin;
import com.example.myapplication.util.Constants;

/**
 * Created with Android studio
 *
 * @Author: EDGClearlove7
 * @Date: 2021/07/30/10:49
 * @Description:
 */
public class LoginFragment extends BaseFragment<LoginPresenter, ILogin.VP> {
    private EditText mUsernameEt;
    private EditText mPasswordEt;
    private Button mLogin;
    @Override
    public ILogin.VP getContract() {
        return new ILogin.VP() {
            @Override
            public void requestLogin(String name, String pwd) {
                mPresenter.getContract().requestLogin(name,pwd);
            }

            @Override
            public void responseLoginResult(int loginStatusResult, int ID, String jwt) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(loginStatusResult== Constants.NETWORK_ERROR){
                            Toast.makeText(getContext(),"网络异常，请检查你的网络连接！",Toast.LENGTH_SHORT).show();
                        }else if(loginStatusResult==Constants.FAIL_LOGIN_USERNAME_CODE){
                            Toast.makeText(getContext(),"无该用户名，请检查并重试",Toast.LENGTH_SHORT).show();
                        }else if(loginStatusResult==Constants.FAIL_LOGIN_PASSWORD_CODE){
                            Toast.makeText(getContext(),"用户密码错误，请重试！",Toast.LENGTH_SHORT).show();
                        }else if(loginStatusResult==Constants.SUCCESS_LOGIN_CODE){
                            Intent intent=new Intent(getContext(),MainActivity.class);
                            intent.putExtra("jwt",jwt);
                            intent.putExtra("ID",ID);
                            startActivity(intent);
                        }
                    }
                });
            }
        };
    }

    @Override
    public void initView(View view) {
        mUsernameEt=view.findViewById(R.id.fragment_login_username_et);
        mPasswordEt=view.findViewById(R.id.fragment_login_password_et);
        mLogin=view.findViewById(R.id.fragment_login_btn);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        mLogin.setOnClickListener(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_login;
    }

    @Override
    public LoginPresenter getPresenterInstance() {
        return new LoginPresenter();
    }

    @Override
    public void destroy() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.fragment_login_btn:
                String username=mUsernameEt.getText().toString();
                String password=mPasswordEt.getText().toString();
                //判断用户输入处理
                if(username==null&&password!=null){
                    Toast.makeText(getContext(),"账户不能为空",Toast.LENGTH_SHORT).show();
                }else if(password==null&&username!=null){
                    Toast.makeText(getContext(),"密码不能为空",Toast.LENGTH_SHORT).show();
                }else if(password==null&&username==null){
                    Toast.makeText(getContext(),"账号和密码不能为空",Toast.LENGTH_SHORT).show();
                }else if(username!=null&&password!=null){
                    getContract().requestLogin(username,password);
                }
        }
    }
}
