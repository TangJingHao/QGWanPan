package com.example.myapplication.Model;

import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.myapplication.DataBean.MyPagerBean;
import com.example.myapplication.Presenter.HomePresenter;
import com.example.myapplication.basic.BaseCreator;
import com.example.myapplication.basic.BaseModel;
import com.example.myapplication.contract.IHome;
import com.example.myapplication.contract.IPost;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
            public void requestSizeData(int ID, String jwt) throws Exception {
                IPost post= BaseCreator.create(IPost.class);
                post.userLoginData(jwt, ID, ID).enqueue(new Callback<MyPagerBean>() {
                    @Override
                    public void onResponse(Call<MyPagerBean> call, Response<MyPagerBean> response) {
                        MyPagerBean myPagerBean=response.body();
                        mPresenter.getContract().responseBaseData(myPagerBean);
                    }

                    @Override
                    public void onFailure(Call<MyPagerBean> call, Throwable t) {
                        MyPagerBean myPagerBean=new MyPagerBean();
                        Log.d("==================","shibaile");
                        //设置返回码为失败
                        myPagerBean.setFlag(false);
                        mPresenter.getContract().responseBaseData(myPagerBean);
                    }
                });
            }

            @Override
            public void requestRecentData() throws Exception {

            }
        };
    }
}
