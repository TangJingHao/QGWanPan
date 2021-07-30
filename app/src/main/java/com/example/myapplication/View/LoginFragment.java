package com.example.myapplication.View;

import android.content.Intent;
import android.util.Log;
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

            }

            @Override
            public void responseLoginResult(int loginStatusResult, int ID, String jwt) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("MainActivity",loginStatusResult+ID+jwt);
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
        mUsernameEt=view.findViewById(R.id.fragment_my_user_username_tv);
        mPasswordEt=view.findViewById(R.id.fragment_my_user_password_tv);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

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

    }

    //    @Nullable
//    @org.jetbrains.annotations.Nullable
//    @Override
//    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_login,container,false);
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        mUsernameEt=getView().findViewById(R.id.fragment_my_user_username_tv);
//        mPasswordEt=getView().findViewById(R.id.fragment_my_user_password_tv);
//    }
}
