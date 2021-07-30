package com.example.myapplication.View;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {
    /**
     * 单例模式编写
     */
    private ActivityCollector(){}//定义一个内部实例
    private static ActivityCollector instance=new ActivityCollector();
    public static ActivityCollector getInstance(){
        return instance;
    }

    /**
     * Activity的增删(注意每个activity都继承这个BaseActivity)
     */
    public static List<Activity> activities=new ArrayList<>();
    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }
    public static void finishAll(){
        for (Activity activity : activities) {
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
        activities.clear();
    }
}
