package com.example.myapplication.contract;

import com.example.myapplication.DataBean.FileDataBean;

import java.util.List;

/**
 * @Name： IFile
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/7/27 13:10
 */
public interface IFile {
    public interface M{
        //找到初始化页面的文件夹数据
        void getFileData(int id) throws Exception;

        //上传文件
        void uploadFile(String path) throws Exception;

        //新建文件
        void newFile(String path) throws Exception;

        //搜索文件
        void searchFile(String fileName)throws Exception;

    }

    public interface VP{

        //找到初始化页面的文件夹数据
        void getFileData();

        //返回默认文件夹
        void getFileDataResult(List<FileDataBean> data);

        //上传文件
        void uploadFile(String path) ;

        //返回请求结果
        void uploadFileResult(int resultCode);

        //新建文件
        void newFile(String path);

        void newFileResult(int resultCode);

        //搜索文件
        void searchFile(String fileName);

        void searchFileResult(List<String> searchData);
    }
}
