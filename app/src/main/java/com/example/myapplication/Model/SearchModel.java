package com.example.myapplication.Model;

import android.util.Log;

import com.example.myapplication.DataBean.IsDeleteHistory;
import com.example.myapplication.DataBean.IsRegister;
import com.example.myapplication.DataBean.MyPagerBean;
import com.example.myapplication.DataBean.SearchHistoryBean;
import com.example.myapplication.DataBean.SearchResult;
import com.example.myapplication.DataBean.UserDataBean;
import com.example.myapplication.Presenter.SearchPresenter;
import com.example.myapplication.basic.BaseCreator;
import com.example.myapplication.basic.BaseModel;
import com.example.myapplication.contract.IPost;
import com.example.myapplication.contract.ISearch;
import com.example.myapplication.util.Constants;
import com.example.myapplication.util.NetWorkUtil;

import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                FormBody formBody = new FormBody.Builder().add("docname",docname)
                        .add("uid",String.valueOf(uid)).build();
                String response = NetWorkUtil.sendRequestOkHttp(formBody,Constants.SEARCH_FILE_URL,jwt,uid);
                mPresenter.getContract().searchFileResult(NetWorkUtil.jsonExchange(response));
//                IPost post = BaseCreator.create(IPost.class);
//                Log.d(TAG,"searchFilename");
//                post.findDocs(docname,uid).enqueue(new Callback<SearchResult>() {
//                    @Override
//                    public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
//                        SearchResult searchResult = response.body();
//                        try {
//                            mPresenter.getContract().searchFileResult(searchResult);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<SearchResult> call, Throwable t) {
//                        mPresenter.getContract().searchFileResult(null);
//                    }
//                });
            }

            @Override
            public void searchHistory(int uid, int num, String jwt) throws Exception {

                //mPresenter.getContract().searchHistoryResult(historyBean);
            }

            @Override
            public void deleteHistory(int uid, String jwt) throws Exception {
                IsDeleteHistory isDeleteHistory = new IsDeleteHistory();
                isDeleteHistory.setFlag(true);
                mPresenter.getContract().deleteHistoryResult(isDeleteHistory);
            }
        };
    }
}
