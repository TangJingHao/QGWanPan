package com.example.myapplication.View;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Presenter.RegisterPresenter;
import com.example.myapplication.R;
import com.example.myapplication.basic.BaseFragment;
import com.example.myapplication.contract.IRegister;
import com.example.myapplication.util.Constants;
import com.example.myapplication.util.SimpleUtil;
import com.example.myapplication.util.TimeCount;

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
    private int code=0;
    private String jwt;
    private String userEmail;

    @Override
    public IRegister.VP getContract() {
        return new IRegister.VP() {
            @Override
            public void requestRegister(String username, String password, String nickname, String userEmail, String checkCode, String jwt) {
                mPresenter.getContract().requestRegister(username,password,nickname,userEmail,checkCode, jwt);
            }

            @Override
            public void requestCheckCode(String userEmail) throws Exception {
                mPresenter.getContract().requestCheckCode(userEmail);
            }

            @Override
            public void responseRegisterCodeResult(int registerStatusResult,String data) throws Exception {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (registerStatusResult == Constants.SUCCESS_REGISTER_CODE) {
                            jwt=data;
                            Toast.makeText(getContext(), "验证码发送成功", Toast.LENGTH_SHORT).show();
                        } else if (registerStatusResult == Constants.ERROR_REGISTER_CODE) {
                            Toast.makeText(getContext(), "验证码发送失败，请检查邮箱地址", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void responseRegister(String username, String password, int code) throws Exception {
               getActivity().runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       if(code==Constants.SUCCESS_REGISTER_CODE){
                           Toast.makeText(getContext(),"恭喜你成功注册",Toast.LENGTH_SHORT).show();
                       }else if(code==Constants.REGISTER_ERROR_NETWORK){
                           Toast.makeText(getContext(),"注册失败，服务器网络异常",Toast.LENGTH_SHORT).show();
                       }else if(code==Constants.REGISTER_ERROR_USERNAME){
                           Toast.makeText(getContext(),"已存在该用户名，注册失败",Toast.LENGTH_SHORT).show();
                       }else if(code==Constants.NETWORK_ERROR){
                           Toast.makeText(getContext(),"retrofit2异常，请检查",Toast.LENGTH_SHORT).show();
                       }
                   }
               });
            }
        };
    }

    @Override
    public void initView(View view) {
        mRegisterUsernameEt = view.findViewById(R.id.fragment_register_user_real_name_et);
        mRegisterUserEmailEt = view.findViewById(R.id.fragment_register_users_mailbox_et);
        mRegisterChekCodeEt = view.findViewById(R.id.fragment_register_verification_code_et);
        mRegisterPasswordEt = view.findViewById(R.id.fragment_register_password_et);
        mPostCodeBtn = view.findViewById(R.id.fragment_verification_code_btn);
        mCheckPasswordBtn = view.findViewById(R.id.fragment_password_btn);
        mRegisterBtn = view.findViewById(R.id.fragment_register_user_btn);
        mRegisterUserEmailBackEt = view.findViewById(R.id.fragment_register_user_mailbox_back_et);
        SimpleUtil.AnWen(mRegisterPasswordEt);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        mCheckPasswordBtn.setOnClickListener(this);
        mPostCodeBtn.setOnClickListener(this);
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mRegisterChekCodeEt.getText().toString().trim().length()!=0&&code!=0){
                    String newUsername=mRegisterUsernameEt.getText().toString().trim();
                    String newUserPassword=mRegisterPasswordEt.getText().toString().trim();
                    String newUserNickname="QG云域新用户";
                    String newEmail=mRegisterUserEmailEt.getText().toString()+"@"+mRegisterUserEmailBackEt.getText().toString();
                    String newCheckCode=mRegisterChekCodeEt.getText().toString().trim();
                    mPresenter.getContract().requestRegister(newUsername,newUserPassword,newUserNickname,newEmail,newCheckCode, jwt);
                }
            }
        });
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
        switch (v.getId()) {
            case R.id.fragment_password_btn:
                if (mCheckPasswordBtn.getText().equals("显示密码")) {
                    SimpleUtil.MingWen(mRegisterPasswordEt);
                    mCheckPasswordBtn.setText("隐藏密码");
                } else if (mCheckPasswordBtn.getText().equals("隐藏密码")) {
                    SimpleUtil.AnWen(mRegisterPasswordEt);
                    mCheckPasswordBtn.setText("显示密码");
                }
                break;
            case R.id.fragment_verification_code_btn:
                String s = mRegisterUserEmailEt.getText().toString().trim();
                String back = mRegisterUserEmailBackEt.getText().toString().trim();
                String username = mRegisterUsernameEt.getText().toString().trim();
                if (s.length() != 0 && username.length() != 0 && back.length() != 0) {
                    TimeCount timeCount = new TimeCount(60000, 1000, mPostCodeBtn);
                    timeCount.start();
                    try {
                        mPresenter.getContract().requestCheckCode(mRegisterUserEmailEt.getText().toString() + "@" + mRegisterUserEmailBackEt.getText().toString());
                        code++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(getContext(),"请检查你的输入时候正确！",Toast.LENGTH_SHORT).show();
                }
                break;
//            case R.id.fragment_register_user_btn:
//                if(mRegisterChekCodeEt.getText().toString().trim().length()!=0&&code!=0){
//                    String newUsername=mRegisterUsernameEt.getText().toString().trim();
//                    String newUserPassword=mRegisterPasswordEt.getText().toString().trim();
//                    String newUserNickname="QG云域新用户";
//                    String newEmail=mRegisterUserEmailEt.getText().toString().trim()+"@"+mRegisterUserEmailBackEt.getText().toString().trim();
//                    String newCheckCode=mRegisterChekCodeEt.getText().toString().trim();
//                    mPresenter.getContract().requestRegister(newUsername,newUserPassword,newUserNickname,newEmail,newCheckCode);
//                }
//                break;
        }
    }
}
