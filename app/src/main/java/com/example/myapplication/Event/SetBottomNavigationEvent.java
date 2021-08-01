package com.example.myapplication.Event;

/**
 * @Description: 文件多选控制
 * @author: Hx
 * @date: 2021年08月01日 15:29
 */
public class SetBottomNavigationEvent {

    private boolean isCheckable;

    public SetBottomNavigationEvent(boolean isCheckable){
        this.isCheckable = isCheckable;
    }

    public boolean IsCheckable(){
        return isCheckable;
    }

}
