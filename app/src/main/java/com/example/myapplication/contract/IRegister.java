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
        void requestRegister(String username,String password,String nickname,String userEmail,String checkCode,String jwt) throws Exception;
        void requestCheckCode(String userEmail) throws Exception;
    }
    interface VP{
        //请求注册
        void requestRegister(String username,String password,String nickname,String userEmail,String checkCode,String jwt);
        void requestCheckCode(String userEmail) throws Exception;
        //返回注册结果
        void responseRegisterCodeResult(int registerStatusResult,String jwt) throws Exception;
        void responseRegister(String username,String password,int code) throws Exception;
    }
}
