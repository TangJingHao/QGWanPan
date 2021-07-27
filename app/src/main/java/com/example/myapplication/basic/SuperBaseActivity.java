package com.example.myapplication.basic;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.View.ActivityCollector;

/**
 * @Name： SuperBaseActivity
 * @Description： 仅用于管理Activity 显示在哪个活动中
 * @Author：Suzy.Mo
 * @Date：2021/7/27 8:46
 */
public class SuperBaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载布局 加入活动管理器
        ActivityCollector.addActivity(this);
        Log.d("正在运行",getClass().getSimpleName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
