package com.example.myapplication.Model;

import com.example.myapplication.DataBean.MyPagerBean;
import com.example.myapplication.DataBean.MyPagerBeanData;
import com.example.myapplication.Presenter.MyPagerPresenter;
import com.example.myapplication.basic.BaseCreator;
import com.example.myapplication.basic.BaseModel;
import com.example.myapplication.contract.IMyPager;
import com.example.myapplication.contract.IPost;
import com.example.myapplication.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
            public void requestMyData(int ID,String jwt) throws Exception {
                IPost post= BaseCreator.create(IPost.class);
                post.userLoginData(jwt,ID).enqueue(new Callback<MyPagerBean>() {
                    @Override
                    public void onResponse(Call<MyPagerBean> call, Response<MyPagerBean> response) {
                        //MyPagerBeanData data = response.body().getData();
                        MyPagerBean myPagerBean=response.body();
                        //myPagerBean.setData(data);
                        mPresenter.getContract().requestMyDataResult(myPagerBean);

                    }

                    @Override
                    public void onFailure(Call<MyPagerBean> call, Throwable t) {
                        MyPagerBean myPagerBean=new MyPagerBean();
                        //设置返回码为失败
                        myPagerBean.setFlag(false);
                        mPresenter.getContract().requestMyDataResult(myPagerBean);
                    }
                });
            }
        };
    }
}
