package com.example.myapplication.Presenter;

import com.example.myapplication.DataBean.SearchHistoryBean;
import com.example.myapplication.DataBean.SearchResult;
import com.example.myapplication.Model.SearchModel;
import com.example.myapplication.View.SearchActivity;
import com.example.myapplication.basic.BasePresenter;
import com.example.myapplication.contract.ISearch;

/**
 * @Name：SearchPresenter
 * @Description：
 * @Author：  Suzy.Mo
 * @Date： 2021/7/28 22:30
 */
public class SearchPresenter extends BasePresenter<SearchModel, SearchActivity, ISearch.VP> {

    @Override
    public SearchModel getModelInstance() {
        return new SearchModel(this);
    }

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
}
