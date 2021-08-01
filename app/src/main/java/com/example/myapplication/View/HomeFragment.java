package com.example.myapplication.View;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import android.view.View;

import com.example.myapplication.Presenter.HomePresenter;
import com.example.myapplication.R;
import com.example.myapplication.basic.BaseFragment;
import com.example.myapplication.contract.IHome;

import java.util.List;

public class HomeFragment extends BaseFragment<HomePresenter, IHome.VP> {
    private ProgressBar mProgressBar;
    private Button mCheckUploadBtn;
    private TextView mUsedTv;
    private ListView mListView;
    private int ID;
    private String jwt;
    private CardView mCardView;
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
