package com.example.myapplication.Presenter;

import com.example.myapplication.Model.HomeModel;
import com.example.myapplication.basic.BasePresenter;
import com.example.myapplication.contract.IHome;

import java.util.List;

/**
 * @Name： HomePresenter
 * @Description：
 * @Author： Suzy.Mo
 * @Date： 2021/7/27 13:52
 */
public class HomePresenter extends BasePresenter<HomeModel,HomePresenter, IHome.VP> {
    @Override
    public HomeModel getModelInstance() {
        return new HomeModel(this);
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
}
