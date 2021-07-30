package com.example.myapplication.contract;

import com.example.myapplication.DataBean.MyPagerBean;

/**
 * @Name：My Application
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/7/27 13:55
 */
public interface IMyPager {
    public interface M{
        //请求获取用户个人信息数据
        void requestMyData(int ID,String jwt)throws Exception;
    }

    public interface VP{
        //请求获取用户个人信息数据
        void requestMyData(int ID,String jwt);
        //返回获取结果  用户的类 MyPagerBean
        void requestMyDataResult(MyPagerBean myData);
    }

}
