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
        void requestRegister(String username,String password,String nickname,String userEmail,String checkCode) throws Exception;
        void requestCheckCode(String userEmail) throws Exception;
    }
    interface VP{
        //请求注册
        void requestRegister(String username,String password,String nickname,String userEmail,String checkCode);
        void requestCheckCode(String userEmail) throws Exception;
        //返回注册结果
        void responseRegisterResult(int registerStatusResult) throws Exception;
    }
}
