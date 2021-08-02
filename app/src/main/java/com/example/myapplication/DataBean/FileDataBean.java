package com.example.myapplication.DataBean;

/**
 * @Description:
 * @author:
 * @date: 2021年08月01日 10:13
 */
public class FileDataBean {

    private String id;
    private String uid;
    private String fid;
    private String foldername;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFoldername() {
        return foldername;
    }

    public void setFoldername(String foldername) {
        this.foldername = foldername;
    }

    @Override
    public String toString() {
        return "FileDataBean{" +
                "id='" + id + '\'' +
                ", uid='" + uid + '\'' +
                ", fid='" + fid + '\'' +
                ", foldername='" + foldername + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }

    private boolean isSelected = false;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
