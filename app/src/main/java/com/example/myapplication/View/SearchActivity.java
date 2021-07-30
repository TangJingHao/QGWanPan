package com.example.myapplication.View;

import android.view.View;

import com.example.myapplication.DataBean.SearchHistoryBean;
import com.example.myapplication.DataBean.SearchResult;
import com.example.myapplication.Presenter.SearchPresenter;
import com.example.myapplication.R;
import com.example.myapplication.basic.BaseActivity;
import com.example.myapplication.basic.BasePresenter;
import com.example.myapplication.contract.ISearch;

/**
 * @Name：My Application
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/7/28 18:04
 */
public class SearchActivity extends BaseActivity<SearchPresenter, ISearch.VP> {

    private int ID;


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
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

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

    }
}
