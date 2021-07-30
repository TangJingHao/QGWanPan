package com.example.myapplication.Model;

import com.example.myapplication.Presenter.SearchPresenter;
import com.example.myapplication.basic.BaseModel;
import com.example.myapplication.contract.ISearch;

/**
 * @Name：My Application
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/7/28 22:30
 */
public class SearchModel extends BaseModel<SearchPresenter, ISearch.M> {
    /**
     * 根据传入的P层与P层绑定
     *
     * @param mPresenter
     */
    public SearchModel(SearchPresenter mPresenter) {
        super(mPresenter);
    }

    @Override
    public ISearch.M getContract() {
        return new ISearch.M() {
            @Override
            public void searchFile(String docname, int uid) throws Exception {

            }

            @Override
            public void searchHistory(int uid, int num) throws Exception {

            }

            @Override
            public void deleteHistory(int uid) throws Exception {

            }
        };
    }
}
