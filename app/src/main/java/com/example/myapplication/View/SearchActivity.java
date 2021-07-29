package com.example.myapplication.View;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DataBean.SearchHistoryBean;
import com.example.myapplication.DataBean.SearchResult;
import com.example.myapplication.Presenter.SearchPresenter;
import com.example.myapplication.R;
import com.example.myapplication.basic.BaseActivity;
import com.example.myapplication.contract.ISearch;

import java.util.List;

/**
 * @Name：My Application
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/7/28 18:04
 */
public class SearchActivity extends BaseActivity<SearchPresenter, ISearch.VP> {

    private int ID;
    private List<SearchHistoryBean> searchHistoryBeans;//搜索历史的数据
    private SearchResult searchDoc;//搜索文件的数据

    private RecyclerView historyRv;
    private SearchView mSearchView;
    private ProgressBar mProcessBar;
    private TextView resultTV;
    private ImageView deleteIv,backTv;

    @Override
    public ISearch.VP getContract() {
        return new ISearch.VP() {
            @Override
            public void searchFile(String docname, int uid) {

            }

            @Override
            public void searchFileResult(SearchResult searchFileData) {

            }

            @Override
            public void searchHistory(int uid, int num) {

            }

            @Override
            public void searchHistoryResult(SearchHistoryBean searchHistoryResult) {

            }

            @Override
            public void deleteHistory(int uid, int num) {

            }

            @Override
            public void deleteHistoryResult(SearchHistoryBean searchHistoryResult) {

            }
        };
    }

    @Override
    public void initView() {
        this.ID=getIntent().getIntExtra("ID",-1);//接受用户的id
        resultTV = findViewById(R.id.search_rescult_tv);
        historyRv = findViewById(R.id.search_history_rv);
        mSearchView = findViewById(R.id.search_searchView);
        mProcessBar = findViewById(R.id.search_progress_bar);
        deleteIv = findViewById(R.id.search_history_delete_iv);
        backTv = findViewById(R.id.search_back);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        backTv.setOnClickListener(this);
        deleteIv.setOnClickListener(this);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_search;
    }

    @Override
    public SearchPresenter getPresenterInstance() {
        return new SearchPresenter();
    }

    @Override
    public void destroy() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_back:
                finish();
                break;
            case R.id.search_history_delete_iv:
            {
                mPresenter.getContract().deleteHistory(ID,20);
            }
        }
    }
}
