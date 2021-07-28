package com.example.myapplication.View;

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
                mPresenter.getContract().requestMyData(ID,jwt);
            }

            @Override
            public void requestMyDataResult(MyPagerBean myData) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MyPagerBean myPagerBean=new MyPagerBean();
                        myPagerBean.setData(myData.getData());

                        if(myPagerBean.getData()==null){
                            Toast.makeText(getContext(),"发生未知错误，请重试!",Toast.LENGTH_SHORT).show();
                        }else{
                            mUserID.setText(myPagerBean.getData().getId());
                            mUsernameTv.setText(myPagerBean.getData().getUsername());
                            mUserNickname.setText(myPagerBean.getData().getNickname());
                            mPasswordTv.setText(myPagerBean.getData().getPassword());
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
    }

    @Override
    public void initData() {
        mPresenter.getContract().requestMyData(ID,jwt);
    }

    @Override
    public void initListener() {

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
}
