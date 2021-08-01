package com.example.myapplication.DataBase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @Name：My Application
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/8/1 13:06
 */
@Entity
public class FileDownDetail {

    @PrimaryKey(autoGenerate = true)//标记id为主键，让系统自动生成
    private int id;

    private int fid;//文件id
    private int uid;//用户id
    private String docname;//文件名称
    private String time;//创建时间

    public FileDownDetail(int fid, int uid, String docname, String time) {
        this.fid = fid;
        this.uid = uid;
        this.docname = docname;
        this.time = time;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getDocname() {
        return docname;
    }

    public void setDocname(String docname) {
        this.docname = docname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
