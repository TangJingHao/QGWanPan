package com.example.myapplication.contract;

/**
 * @Name： ILogin
 * @Description： 登录界面接口契约
 * @Author：Suzy.Mo
 * @Date：2021/7/27 9:21
 */
public interface ILogin {
    //M层接口 拿到用户账户跟密码
    public interface M{
        void requestLogin(String name, String pwd) throws Exception;
    }


    public interface VP{
        //请求登录
        void requestLogin(String name, String pwd);
        //返回登录结果
        void responseLoginResult(int loginStatusResult,int ID);
    }
}
