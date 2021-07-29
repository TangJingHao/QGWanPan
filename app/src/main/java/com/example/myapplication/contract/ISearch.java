package com.example.myapplication.contract;

import com.example.myapplication.DataBean.IsDeleteHistory;
import com.example.myapplication.DataBean.SearchHistoryBean;
import com.example.myapplication.DataBean.SearchResult;

import java.util.List;

/**
 * @Name：My Application
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/7/28 22:30
 */
public interface ISearch {
    public interface M{
        public void searchFile(String docname,int uid,String jwt) throws Exception;
        public void searchHistory(int uid, int num,String jwt) throws Exception;
        public void deleteHistory(int uid,String jwt) throws Exception;
    }

    public interface VP{
        public void searchFile(String docname,int uid,String jwt);
        public void searchFileResult(SearchResult searchFileData);

        public void searchHistory(int uid, int num,String jwt);
        public void searchHistoryResult(SearchHistoryBean searchHistoryResult);

        public void deleteHistory(int uid,String jwt);
        public void deleteHistoryResult(IsDeleteHistory isDeleteResult);

    }
}
