package com.example.myapplication.View;

import android.util.Log;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
                            Log.d("================",jwt);
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
                    String username=mRegisterUsernameEt.getText().toString().trim();
                    String password=mRegisterPasswordEt.getText().toString().trim();
                    String nickname="QG云域新用户";
                    String userEmail=mRegisterUserEmailEt.getText().toString()+"@"+mRegisterUserEmailBackEt.getText().toString();
                    String checkCode=mRegisterChekCodeEt.getText().toString().trim();
//                    mPresenter.getContract().requestRegister(newUsername,newUserPassword,newUserNickname,newEmail,newCheckCode, jwt);
                    RequestBody requestBody=new FormBody.Builder().add("username",username).
                            add("password",password).add("nickname",nickname).add("userEmail",userEmail)
                            .add("checkCode",checkCode)
                            .build();
                    Request request=new Request.Builder().addHeader("Authorization",jwt).url("http://39.98.41.126:31109/user/register").post(requestBody).build();
                    OkHttpClient okHttpClient=new OkHttpClient();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Response response = okHttpClient.newCall(request).execute();
                                String responseData = response.body().string();
                                Log.d("===========",responseData);
                                JSONObject jsonObject=new JSONObject(responseData);
                                Boolean flag=jsonObject.getBoolean("flag");
                                String bug=jsonObject.getString("message");
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (flag){
                                            Toast.makeText(getContext(),"恭喜你成功注册",Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(getContext(),bug,Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
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
