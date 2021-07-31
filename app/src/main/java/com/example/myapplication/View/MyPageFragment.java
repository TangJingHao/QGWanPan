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
    private String jwt;
    private Button mUpdateUserInformationBtn;//修改用户信息按钮
    private Button mMyGroupBtn;
    private Button mMyMemberBtn;
    private Button mRelateUsBtn;
    private ProgressBar mProgressBar;
    private TextView mCurrentTv;//当前使用云盘大小
    private TextView mUserPermissionTv;
    private TextView mMaxTv;//云盘最大容量
    private CircleImageView mMyIcon;//头像
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
                            double useSpace=Double.valueOf(String.format("%.2f",myData.getData().getSpace()/(1024*1024)));//用户剩余可用空间
                            int percentage= (int) ((1-useSpace)*100);
                            mUserNickname.setText(myData.getData().getNickname());
                            mCurrentTv.setText(useSpace+"GB");
                            mProgressBar.setProgress(mProgressBar.getProgress()+percentage);
                        }
                    }
                });
            }
        };
    }

    @Override
    public void initView(View view) {
        mCurrentTv=view.findViewById(R.id.fragment_my_info_my_rest);
        mMaxTv=view.findViewById(R.id.fragment_my_info_my_max);
        mProgressBar=view.findViewById(R.id.fragment_my_info_my_progress);
        mUserNickname=view.findViewById(R.id.fragment_my_info_nickname_tv);
        mUserPermissionTv=view.findViewById(R.id.fragment_my_info_my_permission);
        mMyIcon=view.findViewById(R.id.fragment_my_info_my_icon);
        mUpdateUserInformationBtn=view.findViewById(R.id.edit_btn);
        mMyGroupBtn=view.findViewById(R.id.group_by_btn);
        mMyMemberBtn=view.findViewById(R.id.member_btn);
        mRelateUsBtn=view.findViewById(R.id.relate_us_btn);
    }

    @Override
    public void initData() {
        mPresenter.getContract().requestMyData(ID,jwt);
    }

    @Override
    public void initListener() {
        mMyGroupBtn.setOnClickListener(this);
        mMyMemberBtn.setOnClickListener(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_my_information;
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

        }
    }
}
