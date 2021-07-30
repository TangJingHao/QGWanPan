package com.example.myapplication.contract;

/**
 * @Name：My Application
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/7/26 11:16
 */
public interface IDownload {
    public interface MDownload{
        void downloadFile(String path) throws Exception;
    }

    public interface VPDownload{
        void downloadFile(String path);
        void downloadFileResult(int resultNum);
    }
}
