package com.example.myapplication.Model;

import com.example.myapplication.DataBean.IsDeleteHistory;
import com.example.myapplication.DataBean.SearchHistoryBean;
import com.example.myapplication.DataBean.SearchResult;
import com.example.myapplication.Presenter.SearchPresenter;
import com.example.myapplication.basic.BaseModel;
import com.example.myapplication.contract.ISearch;

import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.List;

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
            public void searchFile(String docname, int uid, String jwt) throws Exception {
                SearchResult searchResult = new SearchResult();
                searchResult.setMassage("拿到了");
                mPresenter.getContract().searchFileResult(searchResult);
            }

            @Override
            public void searchHistory(int uid, int num, String jwt) throws Exception {
                SearchHistoryBean historyBean = new SearchHistoryBean();
                List<SearchHistoryBean.DataBean> dataBeans= new ArrayList<>();
                SearchHistoryBean.DataBean data = new SearchHistoryBean.DataBean(1,"文件1",1);
                SearchHistoryBean.DataBean data2 = new SearchHistoryBean.DataBean(1,"文件2",1);
                SearchHistoryBean.DataBean data3 = new SearchHistoryBean.DataBean(1,"文件3",1);
                dataBeans.add(data);
                dataBeans.add(data2);
                dataBeans.add(data3);
                historyBean.setData(dataBeans);
                mPresenter.getContract().searchHistoryResult(historyBean);
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
