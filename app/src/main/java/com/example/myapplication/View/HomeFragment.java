package com.example.myapplication.View;

import android.view.View;

import com.example.myapplication.Presenter.HomePresenter;
import com.example.myapplication.R;
import com.example.myapplication.basic.BaseFragment;
import com.example.myapplication.contract.IHome;

import java.util.List;

public class HomeFragment extends BaseFragment<HomePresenter, IHome.VP> {
    private int ID;
    public HomeFragment(int ID) {
        this.ID=ID;
    }

    @Override
    public IHome.VP getContract() {
        return new IHome.VP() {
            @Override
            public void requestSizeData() {

            }

            @Override
            public void requestSizeDataResult(String all, String current) {

            }

            @Override
            public void requestRecentData() {

            }

            @Override
            public void requestRecentDataResult(List<String> recentData) {

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
        return R.layout.fragment_home;
    }

    @Override
    public HomePresenter getPresenterInstance() {
        return new HomePresenter();
    }

    @Override
    public void destroy() {

    }
}
