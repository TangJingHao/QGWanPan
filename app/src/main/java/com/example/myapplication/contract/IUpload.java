package com.example.myapplication.contract;

/**
 * @Name：My Application
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/7/26 11:21
 */
public interface IUpload {

    public interface MUpload{
        void uploadFile(String path) throws Exception;
    }

    public interface VPUpload{
        void uploadFile(String path);
        void uploadFileResult(int resultNum);
    }
}
