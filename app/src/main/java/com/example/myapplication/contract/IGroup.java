package com.example.myapplication.contract;

import java.util.List;

/**
 * @Name：My Application
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/7/27 13:55
 */
public interface IGroup {
    public interface M{
        //请求获取组群信息
        void requestGroupData() throws Exception;
    }

    public interface VP{
        //请求获取组群信息
        void requestGroupData() ;

        //返回组群信息  因为还不确定 就先返回字符数组的集合
        void requestGroupDataResult(List<String> groupData);
    }
}
