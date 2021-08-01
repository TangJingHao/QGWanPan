package com.example.myapplication.Event;

import com.example.myapplication.contract.IFile;

/**
 * @Description: 文件夹长按事件
 * @author: Hx
 * @date: 2021年08月01日 15:10
 */
public class FileLongClickEvent {

    private final String Msg;

    public FileLongClickEvent (String Msg){
        this.Msg = Msg;
    }

    public String getMsg() {
        return Msg;
    }
}
