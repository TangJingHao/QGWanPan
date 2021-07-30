package com.example.myapplication.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Presenter.LoginPresenter;
import com.example.myapplication.Presenter.RegisterPresenter;
import com.example.myapplication.R;
import com.example.myapplication.basic.BaseFragment;
import com.example.myapplication.contract.IRegister;
import com.example.myapplication.util.Constants;
import com.example.myapplication.util.SimpleUtil;
import com.example.myapplication.util.TimeCount;

import org.jetbrains.annotations.NotNull;

/**
 * Created with Android studio
 *
 * @Author: EDGClearlove7
 * @Date: 2021/07/30/10:07
 * @Description:
 */
public class RegisterFragment extends BaseFragment<RegisterPresenter, IRegister.VP> {
    private EditText mRegisterUsernameEt;
    private EditText mRegisterPasswordEt;
    private EditText mRegisterUserEmailEt;
    private EditText mRegisterChekCodeEt;
    private EditText mRegisterUserEmailBackEt;
    private Button mPostCodeBtn;
    private Button mCheckPasswordBtn;
    private Button mRegisterBtn;
    @Override
    public IRegister.VP getContract() {
        return new IRegister.VP() {
            @Override
            public void requestRegister(String username, String password, String nickname, String userEmail, String checkCode) {

            }

            @Override
            public void requestCheckCode(String userEmail) throws Exception {
                mPresenter.getContract().requestCheckCode(userEmail);
            }

            @Override
            public void responseRegisterResult(int registerStatusResult) throws Exception {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(registerStatusResult== Constants.SUCCESS_REGISTER_CODE){
                            Toast.makeText(getContext(),"注册成功",Toast.LENGTH_SHORT).show();
                        }else if(registerStatusResult==Constants.ERROR_REGISTER_CODE){
                            Toast.makeText(getContext(),"注册失败，用户名已经被注册",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        };
    }

    @Override
    public void initView(View view) {
        mRegisterUsernameEt=view.findViewById(R.id.fragment_register_user_real_name_et);
        mRegisterUserEmailEt=view.findViewById(R.id.fragment_register_user_mailbox_et);
        mRegisterChekCodeEt=view.findViewById(R.id.fragment_register_verification_code_et);
        mRegisterPasswordEt=view.findViewById(R.id.fragment_register_password_et);
        mPostCodeBtn=view.findViewById(R.id.fragment_verification_code_btn);
        mCheckPasswordBtn=view.findViewById(R.id.fragment_password_btn);
        mRegisterBtn=view.findViewById(R.id.fragment_register_user_btn);
        mRegisterUserEmailBackEt=view.findViewById(R.id.fragment_register_user_mailbox_back_et);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        mCheckPasswordBtn.setOnClickListener(this);
        mPostCodeBtn.setOnClickListener(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_register;
    }

    @Override
    public RegisterPresenter getPresenterInstance() {
        return new RegisterPresenter();
    }

    @Override
    public void destroy() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.fragment_password_btn:
                if(mCheckPasswordBtn.getText().equals("显示密码")){
                    SimpleUtil.MingWen(mRegisterPasswordEt);
                    mCheckPasswordBtn.setText("隐藏密码");
                }else if(mCheckPasswordBtn.getText().equals("隐藏密码")){
                    SimpleUtil.AnWen(mRegisterPasswordEt);
                    mCheckPasswordBtn.setText("显示密码");
                }
                break;
            case R.id.fragment_verification_code_btn:
                String s=mRegisterUserEmailEt.getText().toString().trim();
                String back=mRegisterUserEmailBackEt.getText().toString().trim();
                String username=mRegisterUserEmailEt.getText().toString().trim();
                Log.d("================",s);
//                if(s.length()!=0&&username.length()!=0){
//                    TimeCount timeCount=new TimeCount(60000, 1000,mPostCodeBtn);
//                    timeCount.start();
//                    try {
//                        String request=mRegisterUserEmailEt.getText().toString()+"@"+mRegisterUserEmailBackEt.getText().toString();
//                        Log.d("==============",request);
//                        mPresenter.getContract().requestCheckCode(mRegisterUserEmailEt.getText().toString()+"@qq.com");
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
                try {
                    mPresenter.getContract().requestCheckCode("7627965632");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
