package com.example.myapplication.View;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import android.view.View;

import com.example.myapplication.DataBean.MyPagerBean;
import com.example.myapplication.Presenter.HomePresenter;
import com.example.myapplication.R;
import com.example.myapplication.basic.BaseCreator;
import com.example.myapplication.basic.BaseFragment;
import com.example.myapplication.contract.IHome;
import com.example.myapplication.contract.IPost;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends BaseFragment<HomePresenter, IHome.VP> {
    private ProgressBar mProgressBar;
    private Button mCheckUploadBtn;
    private TextView mUsedTv;
    private ListView mListView;
    private int ID;
    private String jwt;
    private CardView mCardView;
    private int percentage=0;
    public HomeFragment(int ID,String jwt) {
        this.ID=ID;
        this.jwt=jwt;
    }

    @Override
    public IHome.VP getContract() {
        return new IHome.VP() {
            @Override
            public void requestBaseData(int ID, String jwt) {

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

            @Override
            public void responseBaseData(MyPagerBean myPagerBean) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(!myPagerBean.getFlag()){
                            Toast.makeText(getContext(),"发生未知错误，请重试!",Toast.LENGTH_SHORT).show();
                        }else{
                            double useSpace=Double.valueOf(String.format("%.2f",myPagerBean.getData().getSpace()/(1024*1024)));//用户剩余可用空间
                            percentage= (int) ((1-useSpace)*100);
                            mUsedTv.setText(useSpace+"GB");
                            mProgressBar.setProgress(percentage);
                        }
                    }
                });
            }
        };
    }

    @Override
    public void initView(View view) {
        mUsedTv=view.findViewById(R.id.used_tv);
        mProgressBar=view.findViewById(R.id.home_page_progress_bar);
        mCheckUploadBtn=view.findViewById(R.id.check_upload_btn);
        mCardView=view.findViewById(R.id.home_page_search_view);
        mListView=view.findViewById(R.id.file_lv);
    }

    @Override
    public void initData() {
//        mPresenter.getContract().requestBaseData(ID,jwt);
        IPost post= BaseCreator.create(IPost.class);
        post.userLoginData(jwt, ID, ID).enqueue(new Callback<MyPagerBean>() {
            @Override
            public void onResponse(Call<MyPagerBean> call, Response<MyPagerBean> response) {
                MyPagerBean myPagerBean=response.body();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(!myPagerBean.getFlag()){
                            Toast.makeText(getContext(),"发生未知错误，请重试!",Toast.LENGTH_SHORT).show();
                        }else{
                            double useSpace=Double.valueOf(String.format("%.2f",myPagerBean.getData().getSpace()/(1024*1024)));//用户剩余可用空间
                            percentage= (int) ((1-useSpace)*100);
                            mUsedTv.setText(useSpace+"GB");
                            mProgressBar.setProgress(percentage);
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<MyPagerBean> call, Throwable t) {
                MyPagerBean myPagerBean=new MyPagerBean();
                Log.d("==================","shibaile");
                //设置返回码为失败
                myPagerBean.setFlag(false);
            }
        });

    }

    @Override
    public void initListener() {
        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(),SearchActivity.class);
                startActivity(intent);
            }
        });
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
