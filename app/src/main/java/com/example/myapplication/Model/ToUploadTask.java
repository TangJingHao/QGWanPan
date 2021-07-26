package com.example.myapplication.Model;

/**
 * 上传任务的bean类
 */
public class ToUploadTask {
    private String md5;//md5加密验证文件的唯一性
    private String filepath;//文件在客户端的路径
    private long skipSize;

    public ToUploadTask(String md5, String filepath, long skipSize) {
        this.md5 = md5;
        this.filepath = filepath;
        this.skipSize = skipSize;
    }

    public ToUploadTask() {
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public long getSkipSize() {
        return skipSize;
    }

    public void setSkipSize(long skipSize) {
        this.skipSize = skipSize;
    }
}
