package com.example.myapplication.Event;

/**
 * @Description:
 * @author: Hx
 * @date: 2021年08月02日 14:01
 */
public class FileCreate {
    private String Msg;
    public FileCreate(String Msg){
        this.Msg = Msg;
    }
    public String GetMsg(){
        return Msg;
    }
}
