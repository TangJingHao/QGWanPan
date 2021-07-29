package com.example.myapplication.Model;

import android.util.Log;

import com.example.myapplication.Presenter.MyPagerPresenter;
import com.example.myapplication.basic.BaseCreator;
import com.example.myapplication.basic.BaseModel;
import com.example.myapplication.contract.IMyPager;
import com.example.myapplication.contract.IPost;

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
//                post.userLoginData(jwt,ID).enqueue(new Callback<MyPagerBean>() {
//                    @Override
//                    public void onResponse(Call<MyPagerBean> call, Response<MyPagerBean> response) {
//                        Boolean flag = response.body().getFlag();
//                        if(flag){
//                            MyPagerBeanData data = response.body().getData();
//                            MyPagerBean myPagerBean=new MyPagerBean();
//                            myPagerBean.setData(data);
//                            mPresenter.getContract().requestMyDataResult(myPagerBean);
//                        }else{
//                            String message = response.body().getMessage();
//                            Log.d("========",message);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<MyPagerBean> call, Throwable t) {
//                        MyPagerBean myPagerBean=new MyPagerBean();
//                        myPagerBean.setData(null);
//                        mPresenter.getContract().requestMyDataResult(myPagerBean);
//                        t.printStackTrace();
//                    }
//                });
                post.userLoginData(jwt,ID).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.d("================",response.body());
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        };
    }
}
