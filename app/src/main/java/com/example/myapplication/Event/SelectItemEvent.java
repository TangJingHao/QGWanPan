package com.example.myapplication.Event;

/**
 * @Description: 监听多选事件
 * @author: Hx
 * @date: 2021年08月01日 16:49
 */
public class SelectItemEvent {
    private String SelectedNum;
    public SelectItemEvent(String SelectNum){
        this.SelectedNum = SelectNum;
    }
    public String GetSelectedNum(){
        return SelectedNum;
    }
}
