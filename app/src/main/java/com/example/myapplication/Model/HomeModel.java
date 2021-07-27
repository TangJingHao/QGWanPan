package com.example.myapplication.Model;

import com.example.myapplication.Presenter.HomePresenter;
import com.example.myapplication.basic.BaseModel;
import com.example.myapplication.contract.IHome;

/**
 * @Name： HomeModel
 * @Description：
 * @Author： Suzy.Mo
 * @Date： 2021/7/27 13:54
 */
public class HomeModel extends BaseModel<HomePresenter, IHome.M> {

    /**
     * 根据传入的P层与P层绑定
     * @param mPresenter
     */
    public HomeModel(HomePresenter mPresenter) {
        super(mPresenter);
    }

    @Override
    public IHome.M getContract() {
        return new IHome.M() {
            @Override
            public void requestSizeData() throws Exception {

            }

            @Override
            public void requestRecentData() throws Exception {

            }
        };
    }
}
