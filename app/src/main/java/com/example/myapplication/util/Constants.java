package com.example.myapplication.util;

/**
 * 所有请求码或者数据库之类的常量进行封装
 */
public class Constants {
    public static String ServerURL="http://39.98.41.126:31109/";//服务器端地址
    public static String uploadURL="";//上传文件的接口URL
    public static int SUCCESS_CODE=1;//成功码
    public static int FAIL_CODE=0;//失败码
    public static int ERROR_ID=0;//登录失败的默认ID
    public static String ERROR_JWT="";
    public static int NETWORK_ERROR=0;//网络错误码
    public static int SUCCESS_LOGIN_CODE=1;//登录成功码
    public static int FAIL_LOGIN_PASSWORD_CODE=2;//用户密码错误
    public static int FAIL_LOGIN_USERNAME_CODE=3;//用户名不存在
    public static int LOGIN_TO_REGISTER_CODE=1;//请求码登录到注册
    public static int BACK_TO_LOGIN_CODE=1;//有账号点击返回登录界面的码
    public static int REGISTER_TO_LOGIN_CODE=2;//注册账户返回登录界面
    public static int REGISTER_SUCCESS_CODE=1;//注册成功码
    public static int REGISTER_ERROR_NETWORK=2;//注册服务器异常
    public static int REGISTER_ERROR_USERNAME=3;//注册重复用户名
    public static double USER_TOTAL_SPACE=1.0;//用户总云盘空间（1G）



    public static int SUCCESS_REGISTER_CODE=1;//成功发送
    public static int ERROR_REGISTER_CODE=0;//失败发送
}
