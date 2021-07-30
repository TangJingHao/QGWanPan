package com.example.myapplication.util;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.widget.EditText;

/**
 * Created with Android studio
 *
 * @Author: EDGClearlove7
 * @Date: 2021/07/30/17:28
 * @Description:
 */
public class SimpleUtil {

    public static void MingWen(EditText editText){
        //设置明文显示
        HideReturnsTransformationMethod method = HideReturnsTransformationMethod.getInstance();
        editText.setTransformationMethod(method);
    }
    public static void AnWen(EditText editText){
        //设置密文显示
        TransformationMethod method = PasswordTransformationMethod.getInstance();
        editText.setTransformationMethod(method);
    }

}
