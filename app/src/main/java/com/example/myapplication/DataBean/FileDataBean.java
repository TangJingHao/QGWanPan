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
    private String gid;
    private String docName;
    private String docType;
    private String status;
    private String own;
    private String gro;
    private String oth;
    private String date;
    private String lastDate;

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

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOwn() {
        return own;
    }

    public void setOwn(String own) {
        this.own = own;
    }

    public String getGro() {
        return gro;
    }

    public void setGro(String gro) {
        this.gro = gro;
    }

    public String getOth() {
        return oth;
    }

    public void setOth(String oth) {
        this.oth = oth;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    @Override
    public String toString() {
        return "Databean{" +
                "id='" + id + '\'' +
                ", uid='" + uid + '\'' +
                ", fid='" + fid + '\'' +
                ", gid='" + gid + '\'' +
                ", docName='" + docName + '\'' +
                ", docType='" + docType + '\'' +
                ", status='" + status + '\'' +
                ", own='" + own + '\'' +
                ", gro='" + gro + '\'' +
                ", oth='" + oth + '\'' +
                ", date='" + date + '\'' +
                ", lastDate='" + lastDate + '\'' +
                '}';
    }
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
