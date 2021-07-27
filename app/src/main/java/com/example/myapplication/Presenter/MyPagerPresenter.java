package com.example.myapplication.Presenter;

import com.example.myapplication.DataBean.MyPagerBean;
import com.example.myapplication.Model.MyPagerModel;
import com.example.myapplication.View.MyPageFragment;
import com.example.myapplication.basic.BasePresenter;
import com.example.myapplication.contract.IMyPager;

/**
 * @Name： MyPagerPresenter
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/7/27 13:53
 */
public class MyPagerPresenter extends BasePresenter<MyPagerModel, MyPageFragment, IMyPager.VP> {
    @Override
    public MyPagerModel getModelInstance() {
        return new MyPagerModel(this);
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
}
