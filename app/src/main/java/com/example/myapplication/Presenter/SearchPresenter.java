package com.example.myapplication.Presenter;

import android.util.Log;

import com.example.myapplication.DataBean.IsDeleteHistory;
import com.example.myapplication.DataBean.SearchHistoryBean;
import com.example.myapplication.DataBean.SearchResult;
import com.example.myapplication.Model.SearchModel;
import com.example.myapplication.View.SearchActivity;
import com.example.myapplication.basic.BasePresenter;
import com.example.myapplication.contract.ISearch;

import java.util.List;

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
            public void searchFile(String docname, int uid, String jwt) {
                new Thread(()->{
                    try {
                        Log.d("searchFile:",docname+String.valueOf(uid)+jwt);
                        mModel.getContract().searchFile(docname,uid,jwt);
                    } catch (Exception e) {
                        Log.d("searchFile:","请求失败");
                        e.printStackTrace();
                    }
                }).start();
            }

            @Override
            public void searchFileResult(SearchResult searchFileData) {
                mView.getContract().searchFileResult(searchFileData);
            }

            @Override
            public void searchHistory(int uid, int num, String jwt) {
                new Thread(()->{
                    try {
                        mModel.getContract().searchHistory(uid, num, jwt);
                        Log.d("searchHistory:",String.valueOf(uid)+String.valueOf(num)+jwt);
                    } catch (Exception e) {
                        Log.d("searchHistory:","请求搜索历史错误");
                        e.printStackTrace();
                    }
                }).start();
            }

            @Override
            public void searchHistoryResult(SearchHistoryBean searchHistoryResult) {
                mView.getContract().searchHistoryResult(searchHistoryResult);
            }

            @Override
            public void deleteHistory(int uid,String jwt) {
                    new Thread(()->{
                        try {
                            mModel.getContract().deleteHistory(uid,jwt);
                            Log.d("searchDeleteHistory:",String.valueOf(uid)+jwt);
                        } catch (Exception e) {
                            Log.d("searchDeleteHistory:","请求删除历史错误");
                            e.printStackTrace();
                        }
                    }).start();
            }

            @Override
            public void deleteHistoryResult(IsDeleteHistory isDeleteHistory) {
                if(isDeleteHistory!=null){
                    mView.getContract().deleteHistoryResult(isDeleteHistory);
                }
            }
        };

    }
}
