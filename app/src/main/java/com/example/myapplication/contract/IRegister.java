package com.example.myapplication.contract;

/**
 * Created with Android studio
 *
 * @Author: EDGClearlove7
 * @Date: 2021/07/27/12:32
 * @Description:
 */
public interface IRegister {
    interface M{
        void requestRegister(String username,String password,String nickname) throws Exception;
    }
    interface VP{
        //请求注册
        void requestRegister(String username,String password,String nickname);
        //返回注册结果
        void responseRegisterResult(int registerStatusResult,String username,String password);
    }
}
