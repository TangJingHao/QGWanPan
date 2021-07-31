package com.example.myapplication.Model;

import com.example.myapplication.DataBean.IsDeleteHistory;
import com.example.myapplication.Presenter.SearchPresenter;
import com.example.myapplication.basic.BaseModel;
import com.example.myapplication.contract.ISearch;
import com.example.myapplication.util.Constants;
import com.example.myapplication.util.NetWorkUtil;

import okhttp3.FormBody;

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

    private final String TAG = "SearchModel";
    @Override
    public ISearch.M getContract() {
        return new ISearch.M() {
            @Override
            public void searchFile(String docname, int uid, String jwt) throws Exception {
                FormBody formBody = new FormBody.Builder().add("docname",docname).add("uid",String.valueOf(uid)).build();
                String response = NetWorkUtil.sendRequestOkHttp(formBody,Constants.SEARCH_FILE_URL,jwt,uid);
                mPresenter.getContract().searchFileResult(NetWorkUtil.jsonSearchResultExchange(response));
            }

            @Override
            public void searchHistory(int uid, int num, String jwt) throws Exception {
                FormBody formBody = new FormBody.Builder().add("uid",String.valueOf(uid))
                        .add("num",String.valueOf(num)).build();
                String response = NetWorkUtil.sendRequestOkHttp(formBody,Constants.SEARCH_HISTORY_URL,jwt,uid);
                mPresenter.getContract().searchHistoryResult(NetWorkUtil.jsonHistoryExchange(response));
            }

            @Override
            public void deleteHistory(int uid, String jwt) throws Exception {
                FormBody formBody = new FormBody.Builder().add("uid",String.valueOf(uid)).build();
                String response = NetWorkUtil.sendRequestOkHttp(formBody,Constants.DELETE_HISTORY_ONE_ALL,jwt,uid);
                mPresenter.getContract().deleteHistoryResult(NetWorkUtil.jsonDeleteExchange(response));
            }
        };
    }
}
