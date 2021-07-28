package com.example.myapplication.View;

import android.view.View;

import com.example.myapplication.DataBean.MyPagerBean;
import com.example.myapplication.Presenter.MyPagerPresenter;
import com.example.myapplication.R;
import com.example.myapplication.basic.BaseFragment;
import com.example.myapplication.contract.IMyPager;

public class MyPageFragment extends BaseFragment<MyPagerPresenter, IMyPager.VP> {
    private int ID;
    private String jwt;

    public MyPageFragment(int ID,String jwt) {
        this.ID = ID;
        this.jwt=jwt;
    }

    @Override
    public IMyPager.VP getContract() {
        return new IMyPager.VP() {
            @Override
            public void requestMyData() {

            }

            @Override
            public void requestMyDataResult(MyPagerBean myData) {

            }
        };
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

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
