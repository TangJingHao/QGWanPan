package com.example.myapplication.contract;

import com.example.myapplication.DataBean.MyPagerBean;

import java.util.List;

/**
 * @Name：My Application
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/7/27 13:55
 */
public interface IHome {
    public interface M{
        //初始化页面需要的云盘数据信息
        void requestSizeData(int ID,String jwt)throws Exception;
        //最近文件列表的数据
        void requestRecentData()throws Exception;
    }
    public interface VP{
        //云盘数据信息  返回云盘大小及当前所使用的内存
        void requestBaseData(int ID,String jwt);
        void requestSizeDataResult(String all,String current);
        //列表信息及返回  返回文件夹名称的集合
        void requestRecentData();
        void requestRecentDataResult(List<String> recentData);
        void responseBaseData(MyPagerBean myPagerBean);//返回数据
    }

}
