package com.example.myapplication.contract;

import com.example.myapplication.DataBean.SearchHistoryBean;
import com.example.myapplication.DataBean.SearchResult;

/**
 * @Name：My Application
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/7/28 22:30
 */
public interface ISearch {
    public interface M{
        public void searchFile(String docname,int uid) throws Exception;
        public void searchHistory(int uid, int num) throws Exception;
        public void deleteHistory(int uid) throws Exception;
    }

    public interface VP{
        public void searchFile(String docname,int uid);
        public void searchFileResult(SearchResult searchFileData);

        public void searchHistory(int uid, int num);
        public void searchHistoryResult(SearchHistoryBean searchHistoryResult);

        public void deleteHistory(int uid, int num);
        public void deleteHistoryResult(SearchHistoryBean searchHistoryResult);

    }
}
