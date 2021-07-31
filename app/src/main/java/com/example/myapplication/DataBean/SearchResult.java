package com.example.myapplication.DataBean;

import java.util.List;

/**
 * @Name：My Application
 * @Description：
 * @Author：Suzy.Mo
 * @Date：2021/7/28 22:49
 */
public class SearchResult {


    private Boolean flag;
    private List<DataBean> data;
    private String message;

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        private Integer id;
        private Integer uid;
        private Integer fid;
        private Object gid;
        private String docname;
        private String doctype;
        private Object upstatus;
        private Object downstatus;
        private Integer own;
        private Integer gro;
        private Integer oth;
        private Long date;
        private Long lastdate;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getUid() {
            return uid;
        }

        public void setUid(Integer uid) {
            this.uid = uid;
        }

        public Integer getFid() {
            return fid;
        }

        public void setFid(Integer fid) {
            this.fid = fid;
        }

        public Object getGid() {
            return gid;
        }

        public void setGid(Object gid) {
            this.gid = gid;
        }

        public String getDocname() {
            return docname;
        }

        public void setDocname(String docname) {
            this.docname = docname;
        }

        public String getDoctype() {
            return doctype;
        }

        public void setDoctype(String doctype) {
            this.doctype = doctype;
        }

        public Object getUpstatus() {
            return upstatus;
        }

        public void setUpstatus(Object upstatus) {
            this.upstatus = upstatus;
        }

        public Object getDownstatus() {
            return downstatus;
        }

        public void setDownstatus(Object downstatus) {
            this.downstatus = downstatus;
        }

        public Integer getOwn() {
            return own;
        }

        public void setOwn(Integer own) {
            this.own = own;
        }

        public Integer getGro() {
            return gro;
        }

        public void setGro(Integer gro) {
            this.gro = gro;
        }

        public Integer getOth() {
            return oth;
        }

        public void setOth(Integer oth) {
            this.oth = oth;
        }

        public Long getDate() {
            return date;
        }

        public void setDate(Long date) {
            this.date = date;
        }

        public Long getLastdate() {
            return lastdate;
        }

        public void setLastdate(Long lastdate) {
            this.lastdate = lastdate;
        }

        @Override
        public String toString() {
            return  "文件详情为："+"\n\n"+
                    "文件名为：" + docname + '\n' +
                    "文件类型为" + doctype + '\n'
                    //", upstatus=" + upstatus +
                    //", downstatus=" + downstatus +
                    //", date=" + date +
                    //", lastdate=" + lastdate +
                    ;
        }

    }


}
