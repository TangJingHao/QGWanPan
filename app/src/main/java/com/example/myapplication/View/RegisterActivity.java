package com.example.myapplication.View;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Presenter.RegisterPresenter;
import com.example.myapplication.R;
import com.example.myapplication.basic.BaseActivity;
import com.example.myapplication.contract.IRegister;

/**
 * Created with Android studio
 *
 * @Author: EDGClearlove7
 * @Date: 2021/07/27/12:28
 * @Description:
 */
public class RegisterActivity extends BaseActivity<RegisterPresenter, IRegister.VP> {
    private EditText mRegisterUsernameEt;
    private EditText mRegisterPasswordEt;
    private EditText mRegisterNicknameEt;
    private Button mRegisterBackToLoginButton;
    private Button mRegisterUserButton;
    @Override
    public IRegister.VP getContract() {
       return null;
    }

    @Override
    public void initView() {
        mRegisterUserButton=this.findViewById(R.id.register_user_btn);
        mRegisterBackToLoginButton=this.findViewById(R.id.register_backToLogin_btn);
        mRegisterNicknameEt=this.findViewById(R.id.register_nickname_et);
        mRegisterPasswordEt=this.findViewById(R.id.register_password_et);
        mRegisterUsernameEt=this.findViewById(R.id.register_username_et);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        mRegisterBackToLoginButton.setOnClickListener(this);
        mRegisterUserButton.setOnClickListener(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    public RegisterPresenter getPresenterInstance() {
        return new RegisterPresenter();
    }

    @Override
    public void destroy() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register_backToLogin_btn:
                finish();
                break;
            case R.id.register_user_btn:
                String username=mRegisterUsernameEt.getText().toString();
                String password=mRegisterPasswordEt.getText().toString();
                String nickname=mRegisterNicknameEt.getText().toString().trim();//去掉空格
                if(username==null&&password!=null){
                    Toast.makeText(RegisterActivity.this,"账户不能为空",Toast.LENGTH_SHORT).show();
                }else if(password==null&&username!=null){
                    Toast.makeText(RegisterActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                }else if(password==null&&username==null){
                    Toast.makeText(RegisterActivity.this,"账号和密码不能为空",Toast.LENGTH_SHORT).show();
                }else if(username!=null&&password!=null){
                    if(nickname.length()==0||nickname.equals("")){
                        nickname="QG云域新用户";
                    }
//                    getContract().requestRegister(username,password,nickname);
                }
                break;

        }
    }
}
