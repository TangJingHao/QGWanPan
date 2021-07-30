package com.example.myapplication.View;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DataBean.MyPagerBean;
import com.example.myapplication.Presenter.MyPagerPresenter;
import com.example.myapplication.R;
import com.example.myapplication.basic.BaseFragment;
import com.example.myapplication.bean.CircleImageView;
import com.example.myapplication.contract.IMyPager;
import com.example.myapplication.util.Constants;

public class MyPageFragment extends BaseFragment<MyPagerPresenter, IMyPager.VP> {
    private int ID;
    private Button mUpdateUserInformationBtn;//修改用户信息按钮
    private Button mQuitLoginBtn;//退出登录按钮
    private String jwt;
    private ProgressBar mProgressBar;
    private TextView mCurrentTv;//当前使用云盘大小
    private TextView mMaxTv;//云盘最大容量
    private TextView mPercentage;//百分比
    private TextView mUsernameTv;//用户名
    private TextView mPasswordTv;//密码
    private CircleImageView mMyIcon;//头像
    private TextView mUserID;//用户ID
    private TextView mUserNickname;//用户昵称

    public MyPageFragment(int ID,String jwt) {
        this.ID = ID;
        this.jwt=jwt;
    }

    @Override
    public IMyPager.VP getContract() {
        return new IMyPager.VP() {
            @Override
            public void requestMyData(int ID,String jwt) {
                new Thread(()->{
                    mPresenter.getContract().requestMyData(ID,jwt);
                }).start();

            }

            @Override
            public void requestMyDataResult(MyPagerBean myData) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //改了一下  根据返回的flag来判断进行更改  返回为空的话会崩
                        if(!myData.getFlag()){
                            Toast.makeText(getContext(),"发生未知错误，请重试!",Toast.LENGTH_SHORT).show();
                        }else{
                            Log.d("test",myData.getData().getNickname()+myData.getData().getId()+myData.getData().getUsername());
                            double useSpace=(myData.getData().getSpace()/(1024*1024));//用户剩余可用空间
                            double UserCurrentSpace=Constants.USER_TOTAL_SPACE-useSpace;//用户已经用的空间
                            double percentage=(UserCurrentSpace/ Constants.USER_TOTAL_SPACE)*100;
                            mUserID.setText(myData.getData().getId().toString());
                            mUsernameTv.setText(myData.getData().getUsername());
                            mUserNickname.setText(myData.getData().getNickname());
                            mPasswordTv.setText(myData.getData().getPassword());
                            mCurrentTv.setText(UserCurrentSpace+"");
                            mMaxTv.setText("G/1G");
                            mPercentage.setText(percentage+"%");
                            mProgressBar.setProgress((int) (mProgressBar.getProgress()+percentage));
                        }
                    }
                });
            }
        };
    }

    @Override
    public void initView(View view) {
        mCurrentTv=view.findViewById(R.id.my_cloud_disk_current_tv);
        mMaxTv=view.findViewById(R.id.my_cloud_disk_max_tv);
        mPercentage=view.findViewById(R.id.my_cloud_disk_percentage);
        mUsernameTv=view.findViewById(R.id.fragment_my_user_username_tv);
        mPasswordTv=view.findViewById(R.id.fragment_my_user_password_tv);
        mProgressBar=view.findViewById(R.id.fragment_my_progressbar);
        mUserID=view.findViewById(R.id.user_id_tv);
        mUserNickname=view.findViewById(R.id.user_nickname_tv);
        mMyIcon=view.findViewById(R.id.myIcon_iv);
        mQuitLoginBtn=view.findViewById(R.id.quit_login_btn);
        mUpdateUserInformationBtn=view.findViewById(R.id.update_user_information_btn);
    }

    @Override
    public void initData() {
        mPresenter.getContract().requestMyData(ID,jwt);
    }

    @Override
    public void initListener() {
        mQuitLoginBtn.setOnClickListener(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_my;
    }

    @Override
    public MyPagerPresenter getPresenterInstance() {
        return new MyPagerPresenter();
    }

    @Override
    public void destroy() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.quit_login_btn:
                Intent intent=new Intent(getContext(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;

        }
    }
}
