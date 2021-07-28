package com.example.myapplication.Model;

import com.example.myapplication.Presenter.MyPagerPresenter;
import com.example.myapplication.basic.BaseModel;
import com.example.myapplication.contract.IMyPager;

/**
 * @Name： MyPagerModel
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/7/27 13:54
 */
public class MyPagerModel extends BaseModel<MyPagerPresenter, IMyPager.M> {

    /**
     * 根据传入的P层与P层绑定
     * @param mPresenter
     */
    public MyPagerModel(MyPagerPresenter mPresenter) {
        super(mPresenter);
    }

    @Override
    public IMyPager.M getContract() {
        return new IMyPager.M() {
            @Override
            public void requestMyData() throws Exception {

            }
        };
    }
}
