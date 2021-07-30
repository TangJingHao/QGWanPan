package com.example.myapplication.View;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.SearchHistoryAdapter;
import com.example.myapplication.DataBean.IsDeleteHistory;
import com.example.myapplication.DataBean.SearchHistoryBean;
import com.example.myapplication.DataBean.SearchResult;
import com.example.myapplication.Presenter.SearchPresenter;
import com.example.myapplication.R;
import com.example.myapplication.basic.BaseActivity;
import com.example.myapplication.contract.ISearch;
import com.example.myapplication.util.Constants;

import java.util.List;

/**
 * @Name：My Application
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/7/28 18:04
 */
public class SearchActivity extends BaseActivity<SearchPresenter, ISearch.VP> {

    private int ID;
    private String jwt;

    private RecyclerView historyRv;
    private SearchView mSearchView;
    private ProgressBar mProcessBar;
    private TextView resultTV;
    private ImageView deleteIv,backTv;

    private SearchHistoryAdapter searchHistoryAdapter;

    @Override
    public ISearch.VP getContract() {
        return new ISearch.VP() {
            @Override
            public void searchFile(String docname, int uid, String jwt) {
                if (mPresenter!=null){
                    mPresenter.getContract().searchFile(docname,uid,jwt);
                }
            }

            @Override
            public void searchFileResult(SearchResult searchFileData) {
                runOnUiThread(()->{
                    //返回主线程更新UI
                    Toast.makeText(SearchActivity.this,searchFileData.getMassage(),Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void searchHistory(int uid, int num, String jwt) {
                if(mPresenter!=null){
                    mPresenter.getContract().searchHistory(uid,num,jwt);
                }

            }

            @Override
            public void searchHistoryResult(SearchHistoryBean searchHistoryResult) {
                runOnUiThread(()->{
                    //返回主线程更新UI
                    searchHistoryAdapter = new SearchHistoryAdapter(searchHistoryResult);
                    historyRv.setAdapter(searchHistoryAdapter);
                    searchHistoryAdapter.notifyDataSetChanged();
                });
            }

            @Override
            public void deleteHistory(int uid,String jwt) {
                if (mPresenter!=null){
                    mPresenter.getContract().deleteHistory(uid, jwt);
                }

            }

            @Override
            public void deleteHistoryResult(IsDeleteHistory isDeleteHistory) {
                runOnUiThread(()->{
                    //返回主线程更新UI
                    Toast.makeText(SearchActivity.this,isDeleteHistory.getMessage(),Toast.LENGTH_SHORT).show();
                });
            }
        };
    }

    @Override
    public void initView() {
        this.ID=getIntent().getIntExtra("ID",-1);//接受用户的id
        this.jwt = getIntent().getStringExtra("jwt");
        resultTV = findViewById(R.id.search_rescult_tv);
        historyRv = findViewById(R.id.search_history_rv);
        mSearchView = findViewById(R.id.search_searchView);
        mProcessBar = findViewById(R.id.search_progress_bar);
        deleteIv = findViewById(R.id.search_history_delete_iv);
        backTv = findViewById(R.id.search_back);
        mProcessBar.setVisibility(View.INVISIBLE);
        resultTV.setVisibility(View.INVISIBLE);
        //进行页面初始化
        initHistoryView();

        //点击搜索按钮时的监听
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //提交搜索内容时
            @Override
            public boolean onQueryTextSubmit(String query) {
                mProcessBar.setVisibility(View.VISIBLE);
                historyRv.setVisibility(View.INVISIBLE);
                resultTV.setVisibility(View.INVISIBLE);
                getContract().searchFile(query,ID,jwt);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void initHistoryView() {
        getContract().searchHistory(ID,Constants.SEARCH_HISTORY_NUM,jwt);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        historyRv.setLayoutManager(gridLayoutManager);
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
                getContract().deleteHistory(ID,jwt);
            }
        }
    }
}
